package developer.abdulahad.valyuta_kurs

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley

fun loadImage(imageView: ImageView, url: String, context: Context) {
    val image = ImageRequest(
        url,
        {
            imageView.setImageBitmap(it)
        },
        0, 0,
        ImageView.ScaleType.CENTER,
        Bitmap.Config.ARGB_8888,
        {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
    )
    Volley.newRequestQueue(context).add(image)
}