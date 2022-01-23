package com.github.septagrammer.testplugin.model.implementation

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.model.interfaces.BasicNode
import com.github.septagrammer.testplugin.utils.Tags

class BasicNodeImpl(override val id: String?, override val title: String?, tag: Tags) : AbstractNode(tag), BasicNode {
    override var value: String? = null

    override fun buildStringToShow(): String{
        var result: String = "<" + tag.tagName + " " + id?: ""
        if (!title.isNullOrEmpty()){
            result +=" title="
            result += title?: ""
        }
        result += ">"
        if (!value.isNullOrEmpty()){
            result.plus(value?: "")
            //result += value?: ""
        }

        return result
    }
}