package ru.atom.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin implements Plugin<Project> {
    @Override
    void apply (Project project) {
        def map = ["description": "Hello task", "group": "atom"]
        project.task(map, "hello") {
            println "Hello task!"

            doLast {
                println "Execution phase, Action1"
            }

            doFirst {
                println "Execution phase, Action2"
            }

            println "description: ${description}, group: ${group}, actions: ${actions.size()}"
        }
    }
}
