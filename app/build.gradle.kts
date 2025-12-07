import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")

}

android {
    namespace = "com.example.jetpackcomposeui"
    /*compileSdk {
        version = release(36)
    }*/
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.jetpackcomposeui"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.6.10" // 1.5.14
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.media3.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.runtime.livedata)


    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")
    implementation ("com.google.dagger:hilt-android:2.51")
    kapt ("com.google.dagger:hilt-android-compiler:2.51")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("androidx.navigation:navigation-compose:2.9.6")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.33.2-alpha")
   // implementation("me.saket.swipe:swipe:1.3.0")

    implementation("io.github.kevinnzou:compose-swipebox:1.4.0")
    implementation("io.coil-kt:coil-compose:2.6.0")  // image loaded


    /*implementation("androidx.media3:media3-ui-compose:1.4.1")
    implementation ("androidx.media3:media3-exoplayer:1.4.1")
    implementation ("androidx.media3:media3-ui:1.4.1")
    implementation("androidx.media3:media3-datasource-okhttp:1.4.1")*/

    // Jetpack Compose VideoPlayer API (VideoPlayer + rememberVideoPlayerState)
   /* implementation("androidx.media3:media3-ui-compose:1.5.0-alpha01")
    implementation("androidx.media3:media3-exoplayer:1.5.0-alpha01")
    implementation("androidx.media3:media3-datasource-okhttp:1.5.0-alpha01")*/


    // Media3 Compose VideoPlayer API
  //  implementation("androidx.media3:media3-ui-compose:1.5.0-alpha01")
// ExoPlayer core
   // implementation("androidx.media3:media3-exoplayer:1.5.0-alpha01")

    implementation("androidx.media3:media3-exoplayer:1.5.0")
    implementation("androidx.media3:media3-ui:1.5.0")
// Optional: OkHttp for caching/network
    implementation("androidx.media3:media3-datasource-okhttp:1.5.0")
    //implementation("androidx.media3:media3-datasource-okhttp:1.5.0-alpha01")
}
