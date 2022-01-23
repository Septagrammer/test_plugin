package com.github.septagrammer.testplugin.utils.data

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.model.NodeCreationContentHandler
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory

class DataParser {
    fun parse(data: InputStream): AbstractNode? {
        val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
        val reader: XMLReader = parserFactory.newSAXParser().xmlReader
        val contentHandler = NodeCreationContentHandler()
        reader.contentHandler = contentHandler
        reader.parse(InputSource(data))

        return contentHandler.node
    }
}