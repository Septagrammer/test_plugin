package com.github.septagrammer.xml.model.interfaces

import com.github.septagrammer.xml.utils.Tags
import java.util.*
import javax.swing.tree.TreeNode
import kotlin.collections.ArrayList

abstract class AbstractNode(val tag: Tags) : TreeNode {
    private val children = ArrayList<AbstractNode>()
    var parent: AbstractNode? = null

    fun addChild(child: AbstractNode) {
        child.parent = this
        children.add(child)
    }

    fun getChildren(): List<AbstractNode> {
        return children.toList()
    }

    override fun getChildAt(childIndex: Int): TreeNode {
        return children[childIndex]
    }

    fun setChildAt(childIndex: Int, child: AbstractNode) {
        children[childIndex] = child
        child.parent = this
    }

    override fun getChildCount(): Int {
        return children.size
    }

    override fun getParent(): TreeNode? {
        return parent
    }

    override fun getIndex(node: TreeNode?): Int {
        return children.indexOf(node)
    }

    override fun getAllowsChildren(): Boolean {
        return true
    }

    override fun isLeaf(): Boolean {
        return children.isEmpty()
    }

    override fun children(): Enumeration<out TreeNode> {
        return Collections.enumeration(children)
    }

    abstract fun buildStringToShow(): String
}