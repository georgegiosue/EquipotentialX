object Build {

    private object Versions {

        const val androidBuildToolsVersion = "7.0.3"
    }

    const val androidBuildTools = "com.android.tools.build:gradle:${Versions.androidBuildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.Version.hilt_version}"
}