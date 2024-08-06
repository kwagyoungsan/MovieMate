import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.moviemate"
    compileSdk = 34

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }

    val properties = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "local.properties")))
    }
    // Key 정보가 없을 경우 ""를 입력해주도록하면 빌드 에러를 방지할 수 있다.
    val apiKey = properties["API_KEY"] ?: "\"\""

    defaultConfig {
        applicationId = "com.example.moviemate"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "API_KEY", "$apiKey")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4") // ViewModel KTX
    implementation ("com.google.code.gson:gson:2.10.1")  // Gson
    implementation ("androidx.appcompat:appcompat:1.7.0") // LiveData

    // retrofit2
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    implementation ("com.squareup.okhttp3:okhttp")
    implementation ("com.squareup.okhttp3:logging-interceptor")
}