object Deps {

    object Versions {
        const val minSdk = 26
        const val targetSdk = 30
        const val buildTools = "30.0.2"
        const val kotlin = "1.5.30"
        const val apollo = "2.5.9"
        const val spek = "2.0.17"
    }

    object Gradle {
        const val build = "com.android.tools.build:gradle:7.0.2"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val versionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.39.0"
        const val androidJunit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.7.1.1"
    }

    object Plugin {
        const val application = "com.android.application"
        const val kotlin = "kotlin-android"
        const val versions = "com.github.ben-manes.versions"
        const val apollo = "com.apollographql.apollo"
        const val androidJunit5 = "de.mannodermaus.android-junit5"
    }

    object Lib {
        object Kotlin {
            const val stdLibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
            const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        }

        object AndroidX {
            const val core = "androidx.core:core-ktx:1.6.0"
            const val appCompat = "androidx.appcompat:appcompat:1.3.1"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"
        }

        const val material = "com.google.android.material:material:1.4.0"

        object Apollo {
            const val runtime = "com.apollographql.apollo:apollo-runtime:${Versions.apollo}"
            const val cache = "com.apollographql.apollo:apollo-normalized-cache-sqlite:${Versions.apollo}"
            const val coroutines = "com.apollographql.apollo:apollo-coroutines-support:${Versions.apollo}"
            const val android = "com.apollographql.apollo:apollo-android-support:${Versions.apollo}"
            const val api = "com.apollographql.apollo:apollo-api:${Versions.apollo}"
        }

        object Spek {
            const val dsl = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
            const val runner = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
        }

        const val mockk = "io.mockk:mockk:1.12.0"
    }
}