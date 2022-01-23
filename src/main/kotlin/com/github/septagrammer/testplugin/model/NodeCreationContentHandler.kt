package com.github.septagrammer.testplugin.model

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.model.interfaces.BasicNode
import com.github.septagrammer.testplugin.model.implementation.BasicNodeImpl
import com.github.septagrammer.testplugin.model.implementation.RootNodeImpl
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class NodeCreationContentHandler: DefaultHandler() {

    var node: AbstractNode? = null

    @Throws(SAXException::class)
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if (qName == node?.tag?.tagName && node !is RootNodeImpl){
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
        val s = String(ch!!, start, length).trim { it <= ' ' }
        if (node is BasicNode){
            (node as BasicNodeImpl).value = s
        }
    }
}