package com.github.septagrammer.testplugin

import java.util.*
import javax.swing.tree.TreeNode
import kotlin.collections.ArrayList

class TreeItem<String>(var tag: String): TreeNode {
    var parent: TreeItem<String>? = null
    var value: String? = null
    var id: String? = null
    var title: String? = null
    private val myChildren: MutableList<TreeItem<String>> = ArrayList()

    val children: List<TreeItem<String>>
        get() = myChildren

    fun addChild(child: TreeItem<String>) {
        child.parent = this
        myChildren.add(child)
    }

    override fun getChildAt(childIndex: Int): TreeNode {
        return children!![childIndex]
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