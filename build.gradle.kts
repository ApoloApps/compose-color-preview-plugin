plugins {
    alias(deps.plugins.intellij) apply false
    alias(deps.plugins.kotlin.forPlugin) apply false
    alias(deps.plugins.compose) apply false
    alias(deps.plugins.kotlin.forSample) apply false
    id ("dev.bmac.intellij.plugin-uploader") version "1.3.5"
}
tasks {
    register("updateLocalPluginXml", dev.bmac.gradle.intellij.UpdateXmlTask::class.java) {
        updateFile.set(file("updatePlugins.xml"))
        pluginName.set("PluginName")
        downloadUrl.set(project.extra["pluginDownloadUrl"].toString())
        pluginId.set(project.childProjects["plugin"]?.group.toString())
        version.set(project.childProjects["plugin"]?.version.toString())
        pluginDescription.set("Plugin Description")
        changeNotes.set("Updated to ${project.childProjects["plugin"]?.version.toString()}")
        sinceBuild.set(deps.versions.idea.code.min.get())
        untilBuild.set(deps.versions.idea.code.max.get())
    }
}


