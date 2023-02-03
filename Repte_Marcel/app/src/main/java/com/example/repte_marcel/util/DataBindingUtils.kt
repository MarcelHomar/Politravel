package util

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.repte_marcel.R


@BindingAdapter("loadImageFromFilesDir")
fun ImageView.loadImageFromFilesDir(imageName : String){
    val path = context.filesDir.absolutePath.toString() + "/img/" + imageName + ".jpg"
    val bitmap = BitmapFactory.decodeFile(path)
    this.setImageBitmap(bitmap)
}

@BindingAdapter("loadImageFromDrawable")
fun ImageView.loadImageFromDrawable(imageName: String){
    when(imageName){
        "Car" -> {
            this.setImageResource(R.drawable.ic_baseline_directions_car_24)
        }
        "Airplane" -> {
            this.setImageResource(R.drawable.plane)
        }
        "Boat" -> {
            this.setImageResource(R.drawable.ic_baseline_directions_boat_24)
        }
        "Bus" -> {
            this.setImageResource(R.drawable.ic_baseline_directions_bus_24)
        }
        "Train" -> {
            this.setImageResource(R.drawable.ic_baseline_directions_subway_24)
        } else -> {
            this.setImageResource(R.drawable.ic_baseline_directions_bus_24)
        }
    }

}
