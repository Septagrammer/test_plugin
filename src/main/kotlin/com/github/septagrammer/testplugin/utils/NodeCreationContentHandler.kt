package com.github.septagrammer.testplugin.utils

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.model.interfaces.BasicNode
import com.github.septagrammer.testplugin.model.implementation.BasicNodeImpl
import com.github.septagrammer.testplugin.model.implementation.RootNodeImpl
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class NodeCreationContentHandler: DefaultHandler() {

    var item: AbstractNode? = RootNodeImpl()

    @Throws(SAXException::class)
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if (qName == item!!.tag.tagName){
            item = item?.parent
        }
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String?, localName: String?, qName: String, attributes: Attributes?) {
        val item = NodeFactory.createNode(qName, attributes)
        item?.let { this.item?.addChild(it) }
        this.item = item
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        val s = String(ch!!, start, length).trim { it <= ' ' }
        if (item is BasicNode){
            (item as BasicNodeImpl).value = s
        }
    }
}