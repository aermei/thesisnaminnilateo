package com.example.glbviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.filament.utils.ModelViewer
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.ByteBuffer

class MainActivity : AppCompatActivity() {

    private lateinit var modelViewer: ModelViewer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ModelViewer to render the .glb file
        modelViewer = ModelViewer(surfaceView)

        // Load the GLB file from assets
        loadModel("model.glb")
    }

    private fun loadModel(filename: String) {
        // Access the GLB file in assets and load it into the ModelViewer
        assets.open(filename).use { inputStream ->
            val buffer = inputStream.readBytes()
            val glb = ByteBuffer.wrap(buffer)

            modelViewer.loadModel(glb) // Load the .glb file
            modelViewer.scene.addModel(modelViewer.root) // Add it to the Scene
        }
    }

    override fun onResume() {
        super.onResume()
        modelViewer.resume() // Resume rendering
    }

    override fun onPause() {
        super.onPause()
        modelViewer.pause() // Pause rendering
    }
}
