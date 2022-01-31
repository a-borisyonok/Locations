package by.seka.locations.ui.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream


class ImageHelper(

) {

    private fun getBitmapFromURI(context: Context, uri: Uri): Bitmap? {

        try {
            val input: InputStream =
                context.contentResolver.openInputStream(uri) ?: return null
            return BitmapFactory.decodeStream(input)
        } catch (e: FileNotFoundException) {
        }
        return null
    }

    fun saveBitmapIntoStorage(context: Context, uri: Uri): String {
        val bitmap = getBitmapFromURI(context, uri)
        Log.i("bitmap", bitmap.toString())
        val fileDir = "${context.filesDir}"
        val fileName = "${ uri.toString().substringAfter("document/") }${System.currentTimeMillis()}"
        val file = File(fileDir, fileName)
        try {
            val fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
         return file.absolutePath
    }

    fun deleteFile(filepath: String){
        val file = File(filepath)
        file.delete()
    }
}