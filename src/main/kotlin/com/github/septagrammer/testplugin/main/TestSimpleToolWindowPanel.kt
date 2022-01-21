package com.github.septagrammer.testplugin.main

import com.github.septagrammer.testplugin.DataLoader
import com.github.septagrammer.testplugin.TreeItemCreationContentHandler
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.ColoredTreeCellRenderer
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.treeStructure.Tree
import com.intellij.util.ui.JBUI
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.swing.JTree
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory


class TestSimpleToolWindowPanel: SimpleToolWindowPanel(true, true) {
    private val tree = Tree()

    init {
        tree.cellRenderer = object : ColoredTreeCellRenderer() {
            override fun customizeCellRenderer(
                tree: JTree,
                value: Any?,
                selected: Boolean,
                expanded: Boolean,
                leaf: Boolean,
                row: Int,
                hasFocus: Boolean
            ) {
                TODO("Not yet implemented")
            }

        }
        val data = DataLoader().load("sample-file.xml")!!
        val istr: InputStream = ByteArrayInputStream(data.toByteArray(charset("UTF-8")))
/*        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val input = ByteArrayInputStream(data.toByteArray(charset("UTF-8")))
        val doc: Document = builder.parse(input)
        doc.documentElement.normalize()
        val s = doc.documentElement.nodeName
        val c = doc.childNodes
        for (i in 0..1) {
            print(c.item(i).nodeName)
            val tg = c.item(i).childNodes
            for (j in 0..10) {
                print(tg.item(j).nodeName)
                print(tg.item(j).nodeValue)
                print(tg.item(j).attributes)
            }
        }*/


        val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
        val parser: SAXParser = parserFactory.newSAXParser()
        val reader: XMLReader = parser.xmlReader
        val contentHandler = TreeItemCreationContentHandler()
        reader.contentHandler = contentHandler
        reader.parse(InputSource(istr))
        val item = contentHandler.item.children[0]
        val ch = item.children
        //contentHandler.item.children.

        /*val docBuilderFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder: DocumentBuilder = docBuilderFactory.newDocumentBuilder()
        val document: Document = docBuilder.parse(istr)
        val firstChild: Node = document.getFirstChild()*/





        //tree.addRightClickActions(volumeInspect, generateMountOption)

        toolbar = JBUI.Panels.simplePanel(ActionManager.getInstance().run {
            createActionToolbar("XMLToolbar", TestActionGroup(tree), true)
        }.component)

        setContent(ScrollPaneFactory.createScrollPane(tree))
    }

}