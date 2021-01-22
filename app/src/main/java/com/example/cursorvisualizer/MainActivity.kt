package com.example.cursorvisualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.cursorvisualizer.databinding.ActivityMainBinding
import com.example.cursorvisualizermodule.CursorVisualRvAdt
import com.example.cursorvisualizermodule.CursorVisualizer
import com.example.mediacontentresolverlibrary.MediaContentResolver

class MainActivity : AppCompatActivity() {
    var cursorVisualizer = CursorVisualizer.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0x01)
        }

        val mediaContentResolver = MediaContentResolver.newInstance(this)

        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media.DATA
        )
        val cursor = contentResolver.query(uri, /*projection*/null, null, null, null)


        cursorVisualizer.attachRecyclerView(viewBinding.rvCursor)
        cursor?.let {
            cursorVisualizer.setCursor(it)
            cursorVisualizer.showDialog(this, it)
            it.close()
        }
    }
}