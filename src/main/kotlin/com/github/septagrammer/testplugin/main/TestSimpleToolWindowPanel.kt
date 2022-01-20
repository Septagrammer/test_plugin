package com.github.septagrammer.testplugin.main

import com.github.septagrammer.testplugin.DataLoader
import com.github.septagrammer.testplugin.XMLParser
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.ColoredTreeCellRenderer
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.treeStructure.Tree
import com.intellij.util.ui.JBUI
import javax.swing.JTree

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
        val a = XMLParser.parse(data)
        //tree.addRightClickActions(volumeInspect, generateMountOption)

        toolbar = JBUI.Panels.simplePanel(ActionManager.getInstance().run {
            createActionToolbar("XMLToolbar", TestActionGroup(tree), true)
        }.component)

        setContent(ScrollPaneFactory.createScrollPane(tree))
    }

}