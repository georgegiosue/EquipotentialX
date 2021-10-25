object Compose {

    const val version = "1.0.4"

    private object Versions {

        const val activity_compose = "1.3.1"
        const val navigation_compose = "2.4.0-alpha10"
    }

    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val material = "androidx.compose.material:material:$version"
    const val animation = "androidx.compose.animation:animation:$version"
    const val ui = "androidx.compose.ui:ui:$version"
    const val tooling = "androidx.compose.ui:ui-tooling:${version}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation_compose}"
    const val icons = "androidx.compose.material:material-icons-extended:$version"
    const val uiTestJUnit4 = "androidx.compose.ui:ui-test-junit4:$version"
}