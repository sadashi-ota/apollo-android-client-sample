plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.github.ben-manes.versions")
}

android {
    compileSdk = Deps.Versions.targetSdk

    defaultConfig {
        applicationId = "jp.sadashi.sample.apollo.client"
        minSdk = Deps.Versions.minSdk
        targetSdk = Deps.Versions.targetSdk
        buildToolsVersion = "30.0.2"
        versionCode = 1
        versionName = "0.0.1"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Deps.Lib.AndroidX.core)
    implementation(Deps.Lib.AndroidX.appCompat)
    implementation(Deps.Lib.AndroidX.constraintLayout)
    implementation(Deps.Lib.material)
}