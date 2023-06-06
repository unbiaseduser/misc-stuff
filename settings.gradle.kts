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
    }
}

rootProject.name = "Miscellaneous Stuff"
include(":bottom-sheet-alert-dialog")
include(":custom-action-mode")
include(":material-spinner")
include(":conflict-resolver")
