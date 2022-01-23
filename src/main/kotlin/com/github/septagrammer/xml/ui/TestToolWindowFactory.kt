package com.github.septagrammer.xml.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content

class TestToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val defaultWindow = TestSimpleToolWindowPanel()
        val contentManager = toolWindow.contentManager
        val content: Content = contentManager.factory.createContent(defaultWindow, null, false)

        toolWindow.contentManager.addContent(content)
    }

    override fun isApplicable(project: Project): Boolean = true
}