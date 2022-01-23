package com.github.septagrammer.testplugin.ui

import com.github.septagrammer.testplugin.model.NodeFactory
import com.github.septagrammer.testplugin.model.implementation.BasicNodeImpl
import com.github.septagrammer.testplugin.model.implementation.RootNodeImpl
import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.utils.Tags
import com.github.septagrammer.testplugin.utils.data.DataLoader
import com.github.septagrammer.testplugin.utils.data.DataParser
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.ColoredTreeCellRenderer
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.TreeSpeedSearch
import com.intellij.ui.treeStructure.Tree
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.swing.JTree
import com.intellij.util.ui.JBUI
import javax.swing.tree.DefaultTreeModel


class TestSimpleToolWindowPanel : SimpleToolWindowPanel(true, true) {
    private val tree = createTree()

    init {
        //tree.addRightClickActions(volumeInspect, generateMountOption)

        toolbar = JBUI.Panels.simplePanel(ActionManager.getInstance().run {
            createActionToolbar("XMLToolbar", TestActionGroup(tree), true)
        }.component)

        setContent(ScrollPaneFactory.createScrollPane(tree))
    }

    private fun createTree(): Tree {
        val src: String = "sample-file.xml"

        val resolved = DataParser().parse(src)
        val tree = Tree()

        tree.apply {
            TreeSpeedSearch(this)
            isLargeModel = true
            setShowsRootHandles(true)
        }
        tree.model = DefaultTreeModel(resolved)
        tree.setCellRenderer(object : ColoredTreeCellRenderer() {
            override fun customizeCellRenderer(
                tree: JTree,
                value: Any?,
                selected: Boolean,
                expanded: Boolean,
                leaf: Boolean,
                row: Int,
                hasFocus: Boolean
            ) {
                var node: AbstractNode = value as? AbstractNode ?: return
                append(node.buildStringToShow())
            }
        })

        return tree
    }
}