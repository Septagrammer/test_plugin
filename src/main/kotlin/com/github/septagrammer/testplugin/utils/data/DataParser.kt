package com.github.septagrammer.testplugin.utils.data

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.model.NodeFactory
import com.github.septagrammer.testplugin.model.implementation.BasicNodeImpl
import com.github.septagrammer.testplugin.model.implementation.NodeRefImpl
import com.github.septagrammer.testplugin.model.implementation.RootNodeImpl
import com.github.septagrammer.testplugin.model.interfaces.BasicNode
import com.jetbrains.rd.util.addUnique
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import org.xml.sax.XMLReader
import org.xml.sax.helpers.DefaultHandler
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory

class DataParser {

    private val resolver = Resolver(this)

    fun parse(src: String): AbstractNode? {
        val data = DataLoader().load(src)!!
        val istr: InputStream = ByteArrayInputStream(data.toByteArray(charset("UTF-8")))
        val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
        val reader: XMLReader = parserFactory.newSAXParser().xmlReader
        val contentHandler = NodeCreationContentHandler()
        reader.contentHandler = contentHandler
        reader.parse(InputSource(istr))
        return contentHandler.node?.also { resolver.resolve(it, src) }
    }

    private class NodeCreationContentHandler : DefaultHandler() {

        var node: AbstractNode? = null

        @Throws(SAXException::class)
        override fun endElement(uri: String?, localName: String?, qName: String?) {
            if (qName == node?.tag?.tagName && node !is RootNodeImpl) {
                node = node?.parent
            }
        }

        @Throws(SAXException::class)
        override fun startElement(uri: String?, localName: String?, qName: String, attributes: Attributes?) {
            val node = NodeFactory.createNode(qName, attributes)
            node?.let { this.node?.addChild(it) }
            this.node = node
        }

        @Throws(SAXException::class)
        override fun characters(ch: CharArray?, start: Int, length: Int) {
            val s = ch?.let { String(it, start, length) }?.trim { it <= ' ' }
            if (node is BasicNode) {
                (node as BasicNodeImpl).value = s
            }
        }
    }

    private class Resolver(private val parser: DataParser) {

        private val cache = mutableMapOf<Pair<String, String>, BasicNodeImpl?>()

        fun resolve(root: AbstractNode, src: String): AbstractNode? {
            for (i in 0 until root.childCount) {
                var current = root.getChildAt(i)
                if (current is NodeRefImpl) {
                    val ref = resolveRef(current) ?: continue
                    root.setChildAt(i, ref)
                    current = root.getChildAt(i)
                }
                resolve(current as AbstractNode, src)
                if (current is BasicNodeImpl){
                    cache[Pair(src, current.id?: "")] = current
                }

            }

            return root
        }

        private fun resolveRef(node: NodeRefImpl): BasicNodeImpl? {
            val id: String = node.id ?: return null
            val src: String = node.src ?: return null
            var key = Pair(src, id)
            if (!cache.containsKey(key)){
                val refSource = parser.parse(src) ?: return null
                val res = find(id, refSource)
                if (res != null){
                    cache[key] = res
                }
                return find(id, refSource)
            } else {
                return cache[key]
            }
        }

        private fun find(id: String, refSource: AbstractNode?): BasicNodeImpl? {
            var result: BasicNodeImpl? = null
            refSource?.getChildren()?.forEach {
                if (it is BasicNodeImpl && it.id == id) {
                    result = it
                }
                val tempResult = find(id, it)
                if (tempResult != null) {
                    result = tempResult
                }
            }

            return result
        }
    }
}