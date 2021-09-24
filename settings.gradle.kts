@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ApolloAndroidClient"

include(":app")

val modulesDir = File("modules")
include(":graphql")
project(":graphql").projectDir = File(modulesDir, "graphql")

