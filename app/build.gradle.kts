plugins {
    id(Deps.Plugin.application)
    id(Deps.Plugin.kotlin)
    id(Deps.Plugin.kapt)
    id(Deps.Plugin.navigation)
    id(Deps.Plugin.versions)
    id(Deps.Plugin.daggerHilt)
    id(Deps.Plugin.androidJunit5)
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

    sourceSets.forEach {
        it.java.srcDirs("src/$it.name/kotlin")
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        junitPlatform {
            filters {
                includeEngines("spek2")
            }
            jacocoOptions {
                html.enabled = true
                xml.enabled = true
                csv.enabled = false
                excludedClasses.addAll(
                    listOf(
                        "**/di/*.class",
                        "**/extensions/*.class",
                        "**/ui/**/*.class",
                        "**/utility/*.class"
                    )
                )
            }
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Deps.Lib.Kotlin.stdLib)
    implementation(Deps.Lib.Kotlin.Coroutines.core)
    implementation(Deps.Lib.Kotlin.Coroutines.android)

    implementation(Deps.Lib.AndroidX.core)
    implementation(Deps.Lib.AndroidX.appCompat)
    implementation(Deps.Lib.AndroidX.constraintLayout)
    implementation(Deps.Lib.AndroidX.recyclerView)
    implementation(Deps.Lib.material)

    implementation(Deps.Lib.AndroidX.LifeCycle.viewModel)
    implementation(Deps.Lib.AndroidX.LifeCycle.liveData)
    implementation(Deps.Lib.AndroidX.LifeCycle.runtime)
    implementation(Deps.Lib.AndroidX.LifeCycle.savedState)
    implementation(Deps.Lib.AndroidX.LifeCycle.java8)
    kapt(Deps.Lib.AndroidX.LifeCycle.compiler)

    implementation(Deps.Lib.AndroidX.Navigation.fragment)
    implementation(Deps.Lib.AndroidX.Navigation.ui)
    implementation(Deps.Lib.AndroidX.Navigation.hilt)

    implementation(Deps.Lib.coil)
    implementation(Deps.Lib.logcat)

    implementation(Deps.Lib.Apollo.runtime)
    implementation(Deps.Lib.Apollo.cache)
    implementation(Deps.Lib.Apollo.coroutines)
    implementation(Deps.Lib.Apollo.android)
    implementation(Deps.Lib.Apollo.api)

    implementation(Deps.Lib.DaggerHilt.core)
    kapt(Deps.Lib.DaggerHilt.compiler)

    testImplementation(Deps.Lib.Kotlin.reflect)
    testImplementation(Deps.Lib.Spek.dsl)
    testImplementation(Deps.Lib.Spek.runner)

    testImplementation(Deps.Lib.mockk)

    implementation(project(mapOf("path" to ":graphql")))
}