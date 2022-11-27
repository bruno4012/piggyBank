package com.example.piggybank


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.piggybank.databinding.ActivityAddBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class AddActivity : AppCompatActivity() {
    private var pictureIV: ImageView? = null;
    lateinit var photofile: File;
    lateinit var currentPhotoPath: String;
    private val PICTURE_FROM_CAMERA: Int = 1;

    lateinit var db: DataBaseHandler
    lateinit var user: userInfo
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pictureIV = findViewById(R.id.prodImg);
        pictureIV!!.setOnClickListener {
            takePicture()
        }

        val context = this
        var db = DataBaseHandler(context)
        val myBtn: Button = findViewById(R.id.saveButton)
        val myProd: EditText = findViewById(R.id.prodName)
        val myPrice: EditText = findViewById(R.id.prodPrice)
        val myData: EditText = findViewById(R.id.prodDate)
        initView()
        myBtn.setOnClickListener {
            if (myProd.text.toString().length > 0 &&
                myPrice.text.toString().toInt() > 0 &&
                myData.text.toString().length > 0
            ) {
                var user = userInfo(myProd.text.toString(), myPrice.text.toString().toInt(), myData.text.toString())

                db.addData(user)
                val intent = Intent(this, OpenPig::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, "Fill all the blank spaces", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initView(){
        db = DataBaseHandler(this)
//        taskAdapter = TaskAdapter(this)
        binding.saveButton.setOnClickListener{
            val prod = binding.prodName.text.toString()
            val price = binding.prodPrice.text.toString().toInt()
            val date = binding.prodDate.text.toString()

            user= userInfo(prod, price, date)



        }
        // Set on Click Listener for the show button which will ring user to the Main Activity
//        binding.showButton.setOnClickListener{
//            startActivity(Intent(this,MainActivity::class.java))
//        }
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