package com.github.septagrammer.testplugin.utils

import com.github.septagrammer.testplugin.model.implementation.BasicNodeImpl
import com.github.septagrammer.testplugin.model.implementation.NodeRefImpl
import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.utils.data.DataLoader
import com.github.septagrammer.testplugin.utils.data.DataParser
import java.io.ByteArrayInputStream
import java.io.InputStream

class Resolver() {

    fun resolve(root: AbstractNode): AbstractNode {
        for (i in 0 until root.childCount) {
            var current = root.getChildAt(i)
            if (current is NodeRefImpl) {
                val ref = resolveRef(current)
                root.setChildAt(i, ref!!)
                current = root.getChildAt(i)
            }
            resolve(current as AbstractNode)
        }
        return root
    }

    private fun resolveRef(node: NodeRefImpl): BasicNodeImpl? {
        val id: String? = node.id
        val src: String? = node.src
        val data: String? = src?.let { DataLoader().load(it) }!!
        val istr: InputStream = ByteArrayInputStream(data?.toByteArray(charset("UTF-8")))
        val refSource = DataParser().parse(istr)
        return find(id!!, refSource!!)
    }

    private fun find(id: String, refSource: AbstractNode): BasicNodeImpl? {
        var res: BasicNodeImpl? = null
        refSource.children?.forEach {
            if (it is BasicNodeImpl && it.id == id) {
                res = it
            }
            val temp = find(id, it)
            if (temp != null) {
                res = temp
            }

        }
        return res
    }
}