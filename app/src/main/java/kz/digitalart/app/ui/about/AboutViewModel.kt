package kz.digitalart.app.ui.about

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.digitalart.app.R
import kz.digitalart.app.domain.model.About
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {
    private val TAG = this::class.java.simpleName
    val data: MutableLiveData<About> by lazy { MutableLiveData<About>() }

    init {
        data.value = About(context.getString(R.string.about_description))
    }
}