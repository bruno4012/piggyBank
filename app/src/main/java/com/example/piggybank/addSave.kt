package com.example.piggybank

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class addSave : AppCompatActivity() {
    private var pictureIV: ImageView? = null;
    lateinit var photofile: File;
    lateinit var currentPhotoPath: String;
    private val PICTURE_FROM_CAMERA: Int = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_save)
        pictureIV = findViewById(R.id.imageView3);
        pictureIV!!.setOnClickListener {
            takePicture()
        }

    }


    private fun takePicture() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photofile = createImageFile()
        val uri =
            FileProvider.getUriForFile(this, "com.example.retrofittest.fileprovider", photofile)
        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(pictureIntent, PICTURE_FROM_CAMERA)
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {

            if (requestCode == PICTURE_FROM_CAMERA) {
                val uri = FileProvider.getUriForFile(
                    this,
                    "com.example.retrofittest.fileprovider",
                    photofile
                )
                pictureIV!!.setImageURI(uri)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

