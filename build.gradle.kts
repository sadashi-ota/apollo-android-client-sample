// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply(plugin = "com.github.ben-manes.versions")

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Deps.Gradle.build)
        classpath(Deps.Gradle.kotlin)
        classpath(Deps.Gradle.versionsPlugin)
    }
}

tasks.register("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}
