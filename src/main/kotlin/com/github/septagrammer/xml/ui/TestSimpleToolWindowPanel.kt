package com.github.septagrammer.xml.ui

import com.github.septagrammer.xml.model.interfaces.AbstractNode
import com.github.septagrammer.xml.utils.data.DataParser
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.ColoredTreeCellRenderer
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.TreeSpeedSearch
import com.intellij.ui.treeStructure.Tree
import javax.swing.JTree
import javax.swing.tree.DefaultTreeModel


class TestSimpleToolWindowPanel : SimpleToolWindowPanel(true, true) {
    private val tree = createTree()

    init {
        setContent(ScrollPaneFactory.createScrollPane(tree))
    }

    private fun createTree(): Tree {
        val src = "sample-file.xml"

        val rootNode = DataParser().parse(src)
        val tree = Tree().apply {
            TreeSpeedSearch(this)
            isLargeModel = true
            setShowsRootHandles(true)
        }
        tree.model = DefaultTreeModel(rootNode)
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
                val node: AbstractNode = value as? AbstractNode ?: return
                append(node.buildStringToShow())
            }
        })

        return tree
    }
}