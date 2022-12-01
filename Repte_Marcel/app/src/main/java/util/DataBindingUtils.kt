package util

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter



@BindingAdapter("loadImageFromFilesDir")
fun ImageView.loadImageFromFilesDir(imageName : String){
    val path = context.filesDir.absolutePath.toString() + "/img/" + imageName + ".jpg"
    val bitmap = BitmapFactory.decodeFile(path)
    this.setImageBitmap(bitmap)
}

