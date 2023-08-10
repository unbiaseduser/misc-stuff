plugins {
    id("java-library")
    kotlin("jvm")
}

ext {
    set("artifact_id", "conflict-resolver")
    set("version_name", "1.0")
    set("description_str", "Helper class to help resolve dataset conflicts in apps")
    set("is_android_library", false)
}

apply(from = "../publish.gradle")

kotlin {
    jvmToolchain(17)
}

java {
    withSourcesJar()
}
