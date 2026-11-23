plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.hrsthrt74.jump"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.hrsthrt74.jump"
        minSdk = 31
        targetSdk = 37
        versionCode = 3
        versionName = "1.0"
    }

    buildTypes {
        debug {
            // 这个 app 不需要调试，直接按发布标准压缩
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
        }
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

// 工程是纯 Java，不需要 AGP 内置 Kotlin 悄悄塞进来的 kotlin-stdlib（2.4MB）
configurations.matching { it.name.endsWith("RuntimeClasspath") }.configureEach {
    exclude(group = "org.jetbrains.kotlin")
}

tasks.configureEach {
    if (name.startsWith("lintVital")) enabled = false
}
