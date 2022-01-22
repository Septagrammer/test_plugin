package com.github.septagrammer.testplugin

import com.github.septagrammer.testplugin.model.Tags.*
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class TreeItemCreationContentHandler: DefaultHandler() {

    var item = TreeItem("")
    var nodeA = false
    var nodeB = false
    var nodeRef = false


    @Throws(SAXException::class)
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        // finish this node by going back to the parent
        if (qName.equals(item.tag)){
            item = item.parent!!
        }
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String?, localName: String?, qName: String, attributes: Attributes?) {
        val item = TreeItem(qName)
        this.item.addChild(item)
        this.item = item
        if (qName == NODEA.tagName){
            nodeA = true
            item.id = attributes?.getValue("id")
            item.title = attributes?.getValue("title")
        } else if (qName == NODEB.tagName){
            nodeB = true
            item.id = attributes?.getValue("id")
            item.title = attributes?.getValue("title")
        }
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        val s = String(ch!!, start, length).trim { it <= ' ' }
        if (nodeA){
            item.value = s
            nodeA = false
        } else if (nodeB){
            item.value = s
            nodeB = false
        } else if (!s.isEmpty()) {
            item.addChild(TreeItem(s))
        }
    }
}