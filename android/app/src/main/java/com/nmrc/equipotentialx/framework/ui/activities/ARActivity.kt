package com.nmrc.equipotentialx.framework.ui.activities

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentOnAttachListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.google.android.filament.ColorGrading
import com.google.ar.core.*
import com.google.ar.sceneform.*
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.BaseArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.ActivityArcoreBinding
import com.nmrc.equipotentialx.di.ARModule.providerModelRenderable
import com.nmrc.equipotentialx.di.ARModule.providerViewRenderable
import com.nmrc.equipotentialx.util.Permissions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@AndroidEntryPoint
class ARActivity : AppCompatActivity(),
    FragmentOnAttachListener,
    BaseArFragment.OnTapArPlaneListener,
    BaseArFragment.OnSessionConfigurationListener,
    ArFragment.OnViewCreatedListener {

    private lateinit var binding: ActivityArcoreBinding
    private lateinit var arFragment: ArFragment
    private lateinit var model: Renderable
    private lateinit var viewRenderable: ViewRenderable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArcoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Permissions.Camera(this) {  denied ->

            if (denied) finish()
        }

        initFragmentAr()

        lifecycleScope.launch {
            loadModel("modelo.glb")
        }
    }

    private fun initFragmentAr() {
        supportFragmentManager.addFragmentOnAttachListener(this)

        if (Sceneform.isSupported(this)) {
            supportFragmentManager.beginTransaction()
                .add(R.id.arFragment, ArFragment::class.java, null)
                .commit()
        }
    }

    override fun onViewCreated(arSceneView: ArSceneView?) {
        arSceneView?.renderer?.let { renderer ->
            renderer.filamentView.colorGrading = ColorGrading.Builder()
                .toneMapping(ColorGrading.ToneMapping.FILMIC)
                .build(EngineInstance.getEngine().filamentEngine)
        }

        arSceneView?.cameraStream?.depthOcclusionMode =
            CameraStream.DepthOcclusionMode.DEPTH_OCCLUSION_ENABLED
    }

    override fun onTapPlane(hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent?) {
        val anchorNode = hitResult.createAnchor()
            .setUp(hitResult, arFragment.arSceneView.scene)

        val transformableNode = TransformableNode(arFragment.transformationSystem)
            .setUp(anchorNode, model)

        Node().setUp(transformableNode, viewRenderable)
    }

    override fun onAttachFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        arFragment = fragment as ArFragment
        arFragment.setUp()
    }

    override fun onSessionConfiguration(session: Session?, config: Config?) {

        if (session!!.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
            config!!.depthMode = Config.DepthMode.AUTOMATIC
        }

        config!!.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
    }

    private fun Anchor.setUp(
        hitResult: HitResult,
        scene: Scene?
    ) = run {
        hitResult.createAnchor()
        AnchorNode(this).apply {
            parent = scene
        }
    }

    private fun ArFragment.setUp() = apply {
        setOnTapArPlaneListener(this@ARActivity)
        setOnViewCreatedListener(this@ARActivity)
        setOnSessionConfigurationListener(this@ARActivity)
    }

    private fun TransformableNode.setUp(
        anchorNode: NodeParent?,
        renderable: Renderable
    ) = apply {
        parent = anchorNode
        setRenderable(renderable).animate(true).start()
        localScale = Vector3(0.0001f, 0.0001f, 0.0f)
        select()
    }

    private fun Node.setUp(
        transformableNode: TransformableNode,
        viewRenderable: ViewRenderable
    ) = apply {
        parent = transformableNode
        isEnabled = false
        localPosition = Vector3(0.0f, 1.0f, 0.0f)
        renderable = viewRenderable
        isEnabled = true
    }

    private suspend fun loadModel(model: String) {
        val weakActivity = WeakReference(this)
        providerModelRenderable(model)
            .thenAccept { finalModel ->
                val activity = weakActivity.get()
                activity?.model = finalModel
            }
            .exceptionally {
                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show()
                null
            }
        providerViewRenderable(R.layout.support_ar)
            .thenAccept { viewRenderable ->
                val activity = weakActivity.get()
                activity?.viewRenderable = viewRenderable
            }
            .exceptionally {
                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show()
                null
            }
    }
}

