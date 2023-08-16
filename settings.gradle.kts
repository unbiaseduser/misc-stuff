pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "Miscellaneous Stuff"
include(":bottom-sheet-alert-dialog")
include(":custom-action-mode")
include(":material-spinner")
include(":conflict-resolver")
include(":kotlin-java-compat")
include(":base-fragments")
include(":theming")
include(":custom-preferences")
include(":stuff")
include(":stuff-java")
