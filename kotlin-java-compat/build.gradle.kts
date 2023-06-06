plugins {
    id("java-library")
    kotlin("jvm")
}

ext {
    set("artifact_id", "kotlin-java-compat")
    set("version_name", "1.0")
    set("description_str", "Bunch of stuff to make Kotlin-Java interop less painful.")
    set("is_android_library", false)
}

apply(from = "../publish.gradle")

tasks.named("compileKotlin", org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask::class.java) {
    compilerOptions {
        freeCompilerArgs.add("-Xjvm-default=all")
    }
}

kotlin {
    jvmToolchain(8)
}

java {
    withSourcesJar()
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
}
