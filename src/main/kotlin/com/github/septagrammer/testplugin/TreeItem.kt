package com.github.septagrammer.testplugin

class TreeItem<String>(var tag: String) {
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

    fun addChildAfter(child: TreeItem<String>, after: TreeItem<String>) {
        child.parent = this
        var idx = -1
        for (i in myChildren.indices) {
            val item = myChildren[i]
            if (item == after) {
                idx = i
                break
            }
        }
        if (idx == -1) {
            myChildren.add(child)
        } else {
            myChildren.add(idx, child)
        }
    }
}