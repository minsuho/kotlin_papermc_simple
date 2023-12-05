import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("java")
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = property("group")!!
version = property("version")!!
api-version = property("api-version")!!

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    // implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("xyz.icetang.lib:kommand-api:3.1.8")

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
    withType<JavaCompile>().configureEach {
        options.release = 17
    }
    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
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