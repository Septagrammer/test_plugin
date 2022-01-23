package com.github.septagrammer.xml.model.implementation

import com.github.septagrammer.xml.model.interfaces.AbstractNode
import com.github.septagrammer.xml.utils.Tags

class RootNodeImpl: AbstractNode(Tags.ROOT){
    override fun buildStringToShow(): String {
        return "<" + tag.tagName + ">"
    }
}