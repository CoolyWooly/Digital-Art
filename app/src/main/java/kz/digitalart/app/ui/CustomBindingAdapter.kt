package kz.digitalart.app.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import kz.digitalart.app.R
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.ui.custom_views.AudioPlayer

@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, url: String?) {
    Picasso.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_image)
        .into(imageView)
}

@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, exhibit: Exhibit) {
    if (exhibit.photos.isNullOrEmpty()) {
        Picasso.with(imageView.context)
            .load(R.drawable.ic_image)
            .into(imageView)
        return
    }
    Picasso.with(imageView.context)
        .load(exhibit.photos[0])
        .placeholder(R.drawable.ic_image)
        .into(imageView)
}

@BindingAdapter("set_url")
fun setUrl(audioPlayer: AudioPlayer, url: String?) {
    if (url == null) {
        audioPlayer.visibility = View.GONE
    } else {
        audioPlayer.setURL(url)
    }
}