package by.seka.locations.ui

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.seka.locations.R
import by.seka.locations.ui.adapters.locations.LocationViewHolder.Companion.PICK_IMAGE_MULTIPLE
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var columnIndex: Int = 0
        var imageEncoded: String? = null
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
                // Get the Image from data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val imagesEncodedList = ArrayList<String>()
                if (data.data != null) {
                    val mImageUri = data.data

                    // Get the cursor
                    val cursor: Cursor? = contentResolver.query(
                        mImageUri!!,
                        filePathColumn, null, null, null
                    )
                    // Move to first row
                    cursor?.moveToFirst()
                    columnIndex = cursor?.getColumnIndex(filePathColumn[0]) ?: 0
                    imageEncoded = cursor?.getString(columnIndex)
                    cursor?.close()
                } else {
                    if (data.clipData != null) {
                        val mClipData = data.clipData
                        val mArrayUri = mutableListOf<Uri>()
                        for (i in 0 until mClipData!!.itemCount) {
                            val item = mClipData.getItemAt(i)
                            val uri = item.uri
                            mArrayUri.add(uri)
                            // Get the cursor
                            val cursor: Cursor? =
                                contentResolver.query(uri, filePathColumn, null, null, null)
                            // Move to first row
                            if (cursor != null) {
                                cursor.moveToFirst()
                            }
                            columnIndex= cursor?.getColumnIndex(filePathColumn[0]) ?: 0
                            imageEncoded = cursor?.getString(columnIndex)
                            if (imageEncoded != null) {
                                imagesEncodedList.add(imageEncoded)
                            }
                            cursor?.close()
                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size)
                    }
                }
            } else {
                Toast.makeText(
                    this, "You haven't picked Image",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                .show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}