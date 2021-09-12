plugins {
    id(Deps.Plugin.application)
    id(Deps.Plugin.kotlin)
    id(Deps.Plugin.versions)
    id(Deps.Plugin.apollo).version(Deps.Versions.apollo)
}

android {
    compileSdk = Deps.Versions.targetSdk

    defaultConfig {
        applicationId = "jp.sadashi.sample.apollo.client"
        minSdk = Deps.Versions.minSdk
        targetSdk = Deps.Versions.targetSdk
        buildToolsVersion = Deps.Versions.buildTools
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

    implementation(Deps.Lib.Apollo.runtime)
    implementation(Deps.Lib.Apollo.cache)
    implementation(Deps.Lib.Apollo.coroutines)
    implementation(Deps.Lib.Apollo.android)
    implementation(Deps.Lib.Apollo.api)
}