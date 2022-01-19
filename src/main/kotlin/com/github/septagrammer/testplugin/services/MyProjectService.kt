package com.github.septagrammer.testplugin.services

import com.intellij.openapi.project.Project
import com.github.septagrammer.testplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
