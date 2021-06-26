package com.nmrc.equipotentialx.view.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentOnAttachListener
import com.google.android.filament.ColorGrading
import com.google.ar.core.Config
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.core.Session
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.ArSceneView
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Sceneform
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.BaseArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.ActivityArcoreBinding
import java.lang.ref.WeakReference

class ARCoreActivity : AppCompatActivity(),
    FragmentOnAttachListener,
    BaseArFragment.OnTapArPlaneListener,
    BaseArFragment.OnSessionConfigurationListener,
    ArFragment.OnViewCreatedListener {

    private lateinit var binding: ActivityArcoreBinding
    private var arFragment: ArFragment? = null
    private var model: Renderable? = null
    private var viewRenderable: ViewRenderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArcoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.addFragmentOnAttachListener(this)

        if (savedInstanceState == null) {
            if (Sceneform.isSupported(this)) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.arFragment, ArFragment::class.java, null)
                    .commit()
            }
        }

        loadModels()
    }

    override fun onViewCreated(arFragment: ArFragment?, arSceneView: ArSceneView) {
        val renderer = arSceneView.renderer

        if (renderer != null) {
            renderer.filamentView.colorGrading = ColorGrading.Builder()
                .toneMapping(ColorGrading.ToneMapping.FILMIC)
                .build(EngineInstance.getEngine().filamentEngine)
        }

        arSceneView.cameraStream.depthOcclusionMode =
            CameraStream.DepthOcclusionMode.DEPTH_OCCLUSION_ENABLED
    }

    override fun onTapPlane(hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent?) {
        if (model == null || viewRenderable == null) {
            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
            return
        }

        val anchor = hitResult.createAnchor()
        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(arFragment!!.arSceneView.scene)

        val model = TransformableNode(arFragment!!.transformationSystem)
        model.setParent(anchorNode)
        model.setRenderable(this.model)
            .animate(true).start()
        model.localScale = Vector3(0.0001f,0.0001f, 0.0f)
        model.select()

        val titleNode = Node()
        titleNode.setParent(model)
        titleNode.isEnabled = false
        titleNode.localPosition = Vector3(0.0f, 1.0f, 0.0f)
        titleNode.renderable = viewRenderable
        titleNode.isEnabled = true
    }

    override fun onAttachFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        if (fragment.id == R.id.arFragment) {
            arFragment = fragment as ArFragment
            arFragment!!.setOnTapArPlaneListener(this)
            arFragment!!.setOnViewCreatedListener(this)
            arFragment!!.setOnSessionConfigurationListener(this)
        }
    }

    override fun onSessionConfiguration(session: Session?, config: Config?) {

        if (session!!.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
            config!!.depthMode = Config.DepthMode.AUTOMATIC
        }

        config!!.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
    }

    fun loadModels() {
        val weakActivity = WeakReference(this)
        ModelRenderable.builder()
            .setSource(this, Uri.parse("pruebacerna.glb"))
            .setIsFilamentGltf(true)
            .setAsyncLoadEnabled(true)
            .build()
            .thenAccept { model: ModelRenderable? ->
                val activity = weakActivity.get()
                if (activity != null) {
                    activity.model = model
                }
            }
            .exceptionally { throwable: Throwable? ->
                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show()
                null
            }
        ViewRenderable.builder()
            .setView(this, R.layout.view_model_title)
            .build()
            .thenAccept { viewRenderable: ViewRenderable? ->
                val activity = weakActivity.get()
                if (activity != null) {
                    activity.viewRenderable = viewRenderable
                }
            }
            .exceptionally { throwable: Throwable? ->
                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show()
                null
            }
    }
}