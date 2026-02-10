import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "2.1.0" // Use the version matching your Kotlin version
    //room
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosArm64()
    iosSimulatorArm64()
//
//    jvm()
//
//    js {
//        browser()
//    }
//
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser()
//    }

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            val koinVersion = "4.1.1"
            // Koin Core with Wasm support
            implementation("io.insert-koin:koin-core:$koinVersion")
            // Koin Compose for Multiplatform (supports Wasm)
            implementation("io.insert-koin:koin-compose:$koinVersion")

            // Coroutines
            val coroutinesVersion = "1.8.1"
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

            // Ktor
            val ktorVersion = "3.0.0"
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            //local storage
            val settingsVersion = "1.2.0"
            implementation("com.russhwolf:multiplatform-settings:$settingsVersion")
            implementation("com.russhwolf:multiplatform-settings-no-arg:$settingsVersion")
            //room
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-okhttp:3.0.0")
        }

    }
}

android {
    namespace = "com.example.shared_kmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

dependencies {
    //android
    add("kspAndroid", libs.androidx.room.compiler)
    //ios
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
//    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}
//room
room {
    schemaDirectory("$projectDir/schemas")
}