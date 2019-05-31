package kz.digitalart.app.ui

import androidx.databinding.BindingAdapter
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import com.github.siyamed.shapeimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kz.digitalart.app.ui.custom_views.AudioPlayer


@BindingAdapter("image_url")
fun loadImage(imageView: RoundedImageView, url: String?) {
    val ph1 = "https://netstorage-nur.akamaized.net/images/04699b9aacc5c63a.jpg?impolicy=common-image&imwidth=700"

    Picasso.with(imageView.context)
            .load(url)
            .into(imageView)
}

@BindingAdapter("image_urls")
fun loadImages(sliderLayout: SliderLayout, urls: ArrayList<String>?) {
    val ph = "https://unosquare.github.io/swan/swan-logo-256.png"
    val ph2 = "https://netstorage-nur.akamaized.net/images/04699b9aacc5c63a.jpg?impolicy=common-image&imwidth=700"
    sliderLayout.stopAutoCycle()
    sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack)

    val defaultSliderView = DefaultSliderView(sliderLayout.context)
    defaultSliderView.image(ph)
    sliderLayout.addSlider(defaultSliderView)

    val defaultSliderView2 = DefaultSliderView(sliderLayout.context)
    defaultSliderView2.image(ph2)
    sliderLayout.addSlider(defaultSliderView2)
}

@BindingAdapter("set_url")
fun setUrl(audioPlayer: AudioPlayer, url: String) {
    audioPlayer.setURL(url)
}