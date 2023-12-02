import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("java")
    kotlin("jvm") version "1.9.21"
    application
}

group = property("group")!!
version = property("version")!!

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation("io.github.monun:kommand-api:3.1.7")

    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

kotlin {
    jvmToolchain(17)
}

val plugin_main = property("plugin_main")
tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }

    create<Jar>("paperJar") {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        archiveVersion.set("")

        from(sourceSets["main"].output)
        doLast {
            val dir = File("D:\\minecraft\\papermc\\plugins")
            file("build/libs/${rootProject.name}.jar").copyTo(dir, true)
        }
    }

    jar{
        archiveBaseName.set(rootProject.name)
        destinationDirectory.set(file("D:\\minecraft\\papermc\\plugins"))
        manifest {
            attributes ("${plugin_main}")
        }
    }
}