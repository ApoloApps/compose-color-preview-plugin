@file:Suppress("UnstableApiUsage")
pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://cache-redirector.jetbrains.com/plugins.gradle.org")
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("deps") {
        from(files("gradle/deps.versions.toml"))
    }
    repositories{
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://www.jetbrains.com/intellij-repository/snapshots")
        maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
    }
}

rootProject.name = "compose-color-preview"

include(
    ":plugin",
    ":sample",
)

project(":plugin").name = "compose-color-preview-plugin"