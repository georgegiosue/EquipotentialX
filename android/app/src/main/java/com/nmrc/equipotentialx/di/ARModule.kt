package com.nmrc.equipotentialx.di

import android.app.Activity
import android.net.Uri
import androidx.annotation.LayoutRes
import com.google.android.filament.ColorGrading
import com.google.ar.sceneform.rendering.EngineInstance
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
object ARModule {

    @Singleton
    @Provides
    fun providerColorGrading() =
        ColorGrading.Builder()
        .toneMapping(ColorGrading.ToneMapping.FILMIC)
        .build(EngineInstance.getEngine().filamentEngine)

    @Singleton
    @Provides
    fun Activity.providerModelRenderable(model: String) =
        ModelRenderable.builder()
        .setSource(this, Uri.parse(model))
        .setIsFilamentGltf(true)
        .setAsyncLoadEnabled(true)
        .build()

    @Singleton
    @Provides
    fun Activity.providerViewRenderable(@LayoutRes resource:  Int) =
        ViewRenderable.builder()
        .setView(this, resource)
        .build()

    @Provides
    fun providerTransformableNode(arFragment: ArFragment) =
        TransformableNode(arFragment.transformationSystem)
}