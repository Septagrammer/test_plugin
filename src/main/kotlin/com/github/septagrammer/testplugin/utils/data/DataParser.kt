package com.github.septagrammer.testplugin.utils.data

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.utils.NodeCreationContentHandler
import com.github.septagrammer.testplugin.utils.Resolver
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import java.io.InputStream
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class DataParser {
    fun parse(data: InputStream): AbstractNode? {
        val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
        val parser: SAXParser = parserFactory.newSAXParser()
        val reader: XMLReader = parser.xmlReader
        val contentHandler = NodeCreationContentHandler()
        reader.contentHandler = contentHandler
        reader.parse(InputSource(data))
        val item = contentHandler.item?.children?.get(0)
        return contentHandler.item
    }
}