plugins {
    id("java-library")
    kotlin("jvm")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("lib") {
            groupId = "com.sixtyninefourtwenty.misc-stuff"
            artifactId = "conflict-resolver"
            version = "1.0"
            from(components["java"])

            pom {
                name.set("conflict-resolver")
                description.set("Helper class to help resolve dataset conflicts in apps")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("unbiaseduser")
                        name.set("Dang Quang Trung")
                        email.set("quangtrung02hn16@gmail.com")
                        url.set("https://github.com/unbiaseduser")
                    }
                }
            }
        }
    }
}

kotlin {
    jvmToolchain(8)
}

java {
    withSourcesJar()
}
