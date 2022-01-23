package com.github.septagrammer.testplugin.model.implementation

import com.github.septagrammer.testplugin.model.interfaces.AbstractNode
import com.github.septagrammer.testplugin.utils.Tags

class RootNodeImpl: AbstractNode(Tags.ROOT){
    override fun buildStringToShow(): String {
        return "<" + tag.tagName + ">"
    }
}