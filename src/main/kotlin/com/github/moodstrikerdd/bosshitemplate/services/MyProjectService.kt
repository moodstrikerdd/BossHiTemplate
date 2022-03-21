package com.github.moodstrikerdd.bosshitemplate.services

import com.intellij.openapi.project.Project
import com.github.moodstrikerdd.bosshitemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
