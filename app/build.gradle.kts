plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.studye"
    compileSdk = 35

    // Obtendo as variáveis de ambiente do gradle.properties
    val key: String = project.properties["supabaseKey"]?.toString() ?: ""
    val url: String = project.properties["supabaseUrl"]?.toString() ?: ""

    defaultConfig {
        applicationId = "com.app.studye"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Adicionando as variáveis ao BuildConfig
        buildConfigField("String", "SUPABASE_KEY", "\"$key\"")
        buildConfigField("String", "SUPABASE_URL", "\"$url\"")

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AndroidX
    implementation(libs.bundles.androidX)
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    debugImplementation(libs.compose.tooling)
    implementation(libs.bundles.ui)

    // Jetpack Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation("io.coil-kt.coil3:coil-compose:3.0.2")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.2")


    // Supabase
    implementation("io.github.jan-tennert.supabase:postgrest-kt:3.0.1")
    implementation("io.github.jan-tennert.supabase:auth-kt:3.0.1")
    implementation("io.github.jan-tennert.supabase:realtime-kt:3.0.1")
    implementation("io.github.jan-tennert.supabase:storage-kt:3.0.1")


    implementation("io.ktor:ktor-client-android:3.0.1")
    implementation("io.ktor:ktor-client-cio:3.0.1")
    implementation ("io.ktor:ktor-client-core:3.0.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.github.PhilJay:MPAndroidChart:3.1.0")
    implementation ("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}
