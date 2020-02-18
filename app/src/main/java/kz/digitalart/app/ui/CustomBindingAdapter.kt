package kz.digitalart.app.ui

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import kz.digitalart.app.R
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.ui.custom_views.AudioPlayer

@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, url: String?) {
    Glide
        .with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_image)
        .into(imageView)
}

@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, exhibitModel: ExhibitModel) {
    if (exhibitModel.images.isNullOrEmpty()) {
        Glide
            .with(imageView.context)
            .load(R.drawable.ic_image)
            .into(imageView)
    } else {
        Glide
            .with(imageView.context)
            .load(exhibitModel.images[0].url)
            .placeholder(R.drawable.ic_image)
            .into(imageView)
    }
}

@BindingAdapter("set_url")
fun setUrl(audioPlayer: AudioPlayer, url: String?) {
    if (url == null) {
        audioPlayer.visibility = View.GONE
    } else {
        audioPlayer.setURL(url)
    }
}

@BindingAdapter("set_html")
fun setHtml(textView: TextView, text: String?) {
    if (text != null) {
        textView.text = Html.fromHtml(text)
    }
}

@BindingAdapter("is_refreshing")
fun isRefreshing(swipeRefreshLayout: SwipeRefreshLayout, boolean: Boolean) {
    swipeRefreshLayout.isRefreshing = boolean
}