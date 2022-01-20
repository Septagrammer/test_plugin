package com.github.septagrammer.testplugin.modelimplementation

import com.fasterxml.jackson.annotation.JsonTypeName
import com.github.septagrammer.testplugin.model.NodeRef

@JsonTypeName("nodeRef")
class JacksonNodeRef(override val src: String, override val id: String) : NodeRef {

}