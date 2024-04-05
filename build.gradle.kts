fun prop(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.3"
    // https://plugins.jetbrains.com/docs/intellij/using-kotlin.html#kotlin-standard-library
    id("org.jetbrains.kotlin.jvm") version "1.7.0"
}

group = "ru.ztrap.plugin.idea"
version = "0.0.2-223"

repositories {
    mavenCentral()
}

// See https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.3")
    type.set("IC")

    plugins.set(
        listOf(
            "com.intellij.java",
            "org.jetbrains.kotlin",
        ),
    )
}

val jvmVersion = JavaVersion.VERSION_17.toString()

tasks {
    buildSearchableOptions {
        enabled = false
    }

    // Set the JVM compatibility versions
    compileJava {
        sourceCompatibility = jvmVersion
        targetCompatibility = jvmVersion
    }

    compileKotlin {
        kotlinOptions.jvmTarget = jvmVersion
    }

    patchPluginXml {
        version.set(project.version.toString())
        sinceBuild.set("223")
        untilBuild.set("231.*")
    }

    signPlugin {
        certificateChainFile.set(file(prop("publishing.plugin.key.chain")))
        privateKeyFile.set(file(prop("publishing.plugin.key")))
        password.set(prop("publishing.plugin.password"))
    }

    publishPlugin {
        token.set(prop("publishing.plugin.token"))
    }
}
