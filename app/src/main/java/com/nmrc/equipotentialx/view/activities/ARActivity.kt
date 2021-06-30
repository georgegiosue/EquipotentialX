package com.nmrc.equipotentialx.view.activities

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentOnAttachListener
import androidx.navigation.navArgs
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
import com.nmrc.equipotentialx.model.ECharge
import java.lang.ref.WeakReference

class ARActivity : AppCompatActivity(),
    FragmentOnAttachListener,
    BaseArFragment.OnTapArPlaneListener,
    BaseArFragment.OnSessionConfigurationListener,
    ArFragment.OnViewCreatedListener {

    private lateinit var binding: ActivityArcoreBinding
    private lateinit var arFragment: ArFragment
    private lateinit var model: Renderable
    private lateinit var viewRenderable: ViewRenderable
    private val args by navArgs<ARActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArcoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissions(arrayOf(Manifest.permission.CAMERA), 16)

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Access to the camera was denied", Toast.LENGTH_LONG).show()
            finish()
        }

        supportFragmentManager.addFragmentOnAttachListener(this)

        if (savedInstanceState == null) {
            if (Sceneform.isSupported(this)) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.arFragment, ArFragment::class.java, null)
                    .commit()
            }
        }

        loadModels(
            loadCharges(recoverChargers())
        )
    }

    private fun recoverChargers(): ArrayList<ECharge> {
        val lastIndex = args.chargers.lastIndex
        ArrayList<ECharge>().apply {
            add(args.chargers[lastIndex-1])
            add(args.chargers.last())
        }.also {
            return it
        }
    }

    private fun loadCharges(arg: ArrayList<ECharge>): String {
        val q1 = arg[0]
        val q2 = arg[1]
        var select = ""

        if (q1.value == 10 && q2.value == 10)
            select = "layers_model.glb"

        if (q1.value == 1 && q1.dx == -2 && q2.value == 1 && q2.dx == 2)
            select = "1-212.glb"

        if (q1.value == 1 && q1.dx == -2 && q2.value == 5 && q2.dx == 2)
            select = "1-252.glb"

        if (q1.value == 2 && q1.dx == -2 && q2.value == 2 && q2.dx == 2)
            select = "2-222.glb"

        if (q1.value == 2 && q1.dx == -2 && q2.value == 5 && q2.dx == 2)
            select = "2-252.glb"

        if (q1.value == 4 && q1.dx == -3 && q2.value == 4 && q2.dx == 3)
            select = "4-343.glb"

        if (q1.value == 5 && q1.dx == -2 && q2.value == 5 && q2.dx == 2)
            select = "5-252.glb"

        if (q1.value == 5 && q1.dx == -3 && q2.value == 5 && q2.dx == 3)
            select = "5-353.glb"

        if (q1.value == -1 && q1.dx == -2 && q2.value == -1 && q2.dx == 2)
            select = "-1-2-12.glb"

        if (q1.value == -1 && q1.dx == -3 && q2.value == -1 && q2.dx == 3)
            select = "-1-3-13.glb"

        if (q1.value == -1 && q1.dx == -2 && q2.value == 5 && q2.dx == 2)
            select = "-1-252.glb"

        if (q1.value == -2 && q1.dx == -2 && q2.value == -2 && q2.dx == 2)
            select = "-2-2-22.glb"

        if (q1.value == -2 && q1.dx == -3 && q2.value == -2 && q2.dx == 3)
            select = "-2-3-23.glb"

        if (q1.value == -2 && q1.dx == -2 && q2.value == 5 && q2.dx == 2)
            select = "-2-252.glb"

        if (q1.value == -5 && q1.dx == -2 && q2.value == -5 && q2.dx == -2)
            select = "-5-2-5-2.glb"

        if (q1.value == -5 && q1.dx == -2 && q2.value == -5 && q2.dx == 2)
            select = "-5-2-52.glb"

        if (q1.value == -5 && q1.dx == -3 && q2.value == -5 && q2.dx == 3)
            select = "-5-3-53.glb"

        if (q1.value == -5 && q1.dx == -5 && q2.value == -5 && q2.dx == -5)
            select = "-5-5-5-5.glb"

        if (q1.value == -5 && q1.dx == -2 && q2.value == 5 && q2.dx == 2)
            select = "-5-252.glb"
        return select
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
        val anchor = hitResult.createAnchor()
        val anchorNode = AnchorNode(anchor).apply {
            setParent(arFragment.arSceneView.scene)
        }

        val model = TransformableNode(arFragment.transformationSystem).apply {
            setParent(anchorNode)
            setRenderable(model).animate(true).start()
            localScale = Vector3(0.0001f,0.0001f, 0.0f)
            select()
        }

        val titleNode = Node()
        titleNode.apply {
            setParent(model)
            isEnabled = false
            localPosition = Vector3(0.0f, 1.0f, 0.0f)
            renderable = viewRenderable
            isEnabled = true
        }
    }

    override fun onAttachFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        if (fragment.id == R.id.arFragment) {
            arFragment = fragment as ArFragment
            arFragment.let{ ARfragment ->
                ARfragment.apply {
                    setOnTapArPlaneListener(this@ARActivity)
                    setOnViewCreatedListener(this@ARActivity)
                    setOnSessionConfigurationListener(this@ARActivity)
                }
            }
        }
    }

    override fun onSessionConfiguration(session: Session?, config: Config?) {

        if (session!!.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
            config!!.depthMode = Config.DepthMode.AUTOMATIC
        }

        config!!.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
    }

    fun loadModels(arg: String) {
        val weakActivity = WeakReference(this)
        ModelRenderable.builder()
            .setSource(this, Uri.parse(arg))
            .setIsFilamentGltf(true)
            .setAsyncLoadEnabled(true)
            .build()
            .thenAccept { model: ModelRenderable? ->
                val activity = weakActivity.get()
                if (activity != null)
                    activity.model = model!!
            }
            .exceptionally {
                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show()
                null
            }
        ViewRenderable.builder()
            .setView(this, R.layout.view_model_title)
            .build()
            .thenAccept { viewRenderable ->
                val activity = weakActivity.get()
                if (activity != null)
                    activity.viewRenderable = viewRenderable
            }
            .exceptionally {
                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show()
                null
            }
    }
}