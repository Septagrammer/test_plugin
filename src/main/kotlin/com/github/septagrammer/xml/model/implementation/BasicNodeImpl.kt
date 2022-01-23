package com.github.septagrammer.xml.model.implementation

import com.github.septagrammer.xml.model.interfaces.AbstractNode
import com.github.septagrammer.xml.model.interfaces.BasicNode
import com.github.septagrammer.xml.utils.Tags

class BasicNodeImpl(override val id: String?, override val title: String?, tag: Tags) : AbstractNode(tag), BasicNode {
    override var value: String? = null

    override fun buildStringToShow(): String{
        var result: String = "<" + tag.tagName + " " + (id ?: "")
        if (!title.isNullOrEmpty()){
            result +=" title="
            result += title
        }
        result += ">"
        if (!value.isNullOrEmpty()){
            result += (value?: "")
        }

        return result
    }
}