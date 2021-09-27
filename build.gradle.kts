// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Deps.Gradle.build)
        classpath(Deps.Gradle.kotlin)
        classpath(Deps.Gradle.navigation)
        classpath(Deps.Gradle.daggerHilt)
        classpath(Deps.Gradle.versionsPlugin)
        classpath(Deps.Gradle.androidJunit5)
    }
}

apply(plugin = Deps.Plugin.versions)

tasks.register("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}
