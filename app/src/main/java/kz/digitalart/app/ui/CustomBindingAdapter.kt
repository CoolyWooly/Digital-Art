package kz.digitalart.app.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.ui.custom_views.AudioPlayer

@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, url: String?) {
    Picasso.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, exhibit: Exhibit) {
    if (exhibit.photos.isNullOrEmpty()) return
    Picasso.with(imageView.context)
        .load(exhibit.photos[0])
        .into(imageView)
}

@BindingAdapter("set_url")
fun setUrl(audioPlayer: AudioPlayer, url: String) {
    audioPlayer.setURL(url)
}