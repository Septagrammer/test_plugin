package com.github.septagrammer.testplugin.ui

import com.github.septagrammer.testplugin.utils.Resolver
import com.github.septagrammer.testplugin.utils.data.DataLoader
import com.github.septagrammer.testplugin.utils.data.DataParser
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.ColoredTreeCellRenderer
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.treeStructure.Tree
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.swing.JTree
import com.intellij.util.ui.JBUI


class TestSimpleToolWindowPanel : SimpleToolWindowPanel(true, true) {
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
                print("")
            }
        }

        //tree.addRightClickActions(volumeInspect, generateMountOption)

        toolbar = JBUI.Panels.simplePanel(ActionManager.getInstance().run {
            createActionToolbar("XMLToolbar", TestActionGroup(tree), true)
        }.component)

        setContent(ScrollPaneFactory.createScrollPane(tree))
    }

    private fun createTree(): Tree {
        val data = DataLoader().load("sample-file.xml")!!
        val istr: InputStream = ByteArrayInputStream(data.toByteArray(charset("UTF-8")))
        val resolved = Resolver().resolve(DataParser().parse(istr)!!)
        return Tree(resolved)
    }
}