package kz.digitalart.app.ui.liked.details

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LikedDetailsViewModel @Inject constructor() : ViewModel() {
    private val TAG = this::class.java.simpleName

    companion object {
        val ACTION_LANGUAGE = "ACTION_LANGUAGE"
        val ACTION_LIKED = "ACTION_LIKED"
        val ACTION_ABOUT = "ACTION_ABOUT"
    }

    val action: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun onClickLanguage(view: View) {
        action.value = ACTION_LANGUAGE
    }

    fun onClickLiked(view: View) {
        action.value = ACTION_LIKED
    }

    fun onClickAbout(view: View) {
        action.value = ACTION_ABOUT
    }
}