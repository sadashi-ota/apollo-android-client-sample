plugins {
    id(Deps.Plugin.library)
    id(Deps.Plugin.kotlin)
    id(Deps.Plugin.kapt)
    id(Deps.Plugin.apollo).version(Deps.Versions.apollo)
}


android {
    compileSdk = Deps.Versions.targetSdk

    defaultConfig {
        minSdk = Deps.Versions.minSdk
        targetSdk = Deps.Versions.targetSdk
        buildToolsVersion = Deps.Versions.buildTools
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

    sourceSets.forEach {
        it.java.srcDirs("src/$it.name/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

apollo {
    generateKotlinModels.set(true)
    customTypeMapping.set(mapOf(
        "URI" to "String",
        "Date" to "java.util.Date"
    ))
}

dependencies {
    implementation(Deps.Lib.Kotlin.stdLib)

    implementation(Deps.Lib.Apollo.runtime)
    implementation(Deps.Lib.Apollo.cache)
    implementation(Deps.Lib.Apollo.coroutines)
    implementation(Deps.Lib.Apollo.android)
    implementation(Deps.Lib.Apollo.api)
}