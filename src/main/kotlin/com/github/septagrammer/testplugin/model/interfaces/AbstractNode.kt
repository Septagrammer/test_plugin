package com.github.septagrammer.testplugin.model.interfaces

import java.util.*
import javax.swing.tree.TreeNode
import kotlin.collections.ArrayList

abstract class AbstractNode: TreeNode {
    var children: MutableList<AbstractNode>? = ArrayList()
    var parent: AbstractNode? = null
    var tag: Tags = Tags.NODEREF

    fun addChild(child: AbstractNode) {
        child.parent = this
        children!!.add(child)
    }

    override fun getChildAt(childIndex: Int): TreeNode {
        return children!![childIndex]
    }

    fun setChildAt(childIndex: Int, child: AbstractNode){
        children?.set(childIndex, child)
    }

    override fun getChildCount(): Int {
        return children!!.size
    }

    override fun getParent(): TreeNode {
        return parent!!
    }

    override fun getIndex(node: TreeNode?): Int {
        return children!!.indexOf(node)
    }

    override fun getAllowsChildren(): Boolean {
        return true
    }

    override fun isLeaf(): Boolean {
        return true
    }

    override fun children(): Enumeration<out TreeNode> {
        return Collections.enumeration(children)
    }
}