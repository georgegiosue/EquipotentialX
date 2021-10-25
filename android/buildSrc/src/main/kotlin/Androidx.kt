object Androidx {

    private object Versions {

        const val core_version = "1.6.0"
        const val app_compat_version = "1.3.1"
        const val lifecycle_version = "2.4.0-rc01"
        const val legacy_version = "1.0.0"
        const val constraint_layout_version = "2.1.1"
        const val junit_android_version = "1.1.3"
        const val espresso_core_version = "3.4.0"
    }

    const val core = "androidx.core:core-ktx:${Versions.core_version}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat_version}"
    const val lifecycle_viewmodel_compose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle_version}"
    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_version}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    const val junit = "androidx.test.ext:junit:${Versions.junit_android_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"
}