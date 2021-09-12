object Deps {

    object Versions {
        const val minSdk = 26
        const val targetSdk = 30
        const val kotlin = "1.5.30"
    }

    object Gradle {
        const val build = "com.android.tools.build:gradle:7.0.2"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val versionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.39.0"
    }

    object Lib {
        object AndroidX {
            const val core = "androidx.core:core-ktx:1.6.0"
            const val appCompat = "androidx.appcompat:appcompat:1.3.1"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"
        }

        const val material = "com.google.android.material:material:1.4.0"
    }
}