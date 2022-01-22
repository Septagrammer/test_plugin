package com.github.septagrammer.testplugin.ui

import com.github.septagrammer.testplugin.NodeCreationContentHandler
import com.github.septagrammer.testplugin.utils.DataLoader
import com.github.septagrammer.testplugin.TreeItemCreationContentHandler
import com.github.septagrammer.testplugin.utils.XMLParser
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.ColoredTreeCellRenderer
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.treeStructure.Tree
import com.intellij.util.ui.JBUI
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.swing.JTree
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory


class TestSimpleToolWindowPanel: SimpleToolWindowPanel(true, true) {
    private val tree = createTree()

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

        //tree.addRightClickActions(volumeInspect, generateMountOption)

        toolbar = JBUI.Panels.simplePanel(ActionManager.getInstance().run {
            createActionToolbar("XMLToolbar", TestActionGroup(tree), true)
        }.component)

        setContent(ScrollPaneFactory.createScrollPane(tree))
    }

    private fun createTree(): Tree{
        val data = DataLoader().load("sample-file.xml")!!
        val istr: InputStream = ByteArrayInputStream(data.toByteArray(charset("UTF-8")))
        return XMLParser.parse(istr)
    }

}