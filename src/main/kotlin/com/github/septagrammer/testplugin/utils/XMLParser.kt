package com.github.septagrammer.testplugin.utils

import com.github.septagrammer.testplugin.NodeCreationContentHandler
import com.intellij.ui.treeStructure.Tree
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import java.io.InputStream
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

object XMLParser {

    fun parse(data: InputStream): Tree{
        val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
        val parser: SAXParser = parserFactory.newSAXParser()
        val reader: XMLReader = parser.xmlReader
        val contentHandler = NodeCreationContentHandler()
        reader.contentHandler = contentHandler
        reader.parse(InputSource(data))
        val item = contentHandler.item?.children?.get(0)
        return Tree(contentHandler.item)
    }

}