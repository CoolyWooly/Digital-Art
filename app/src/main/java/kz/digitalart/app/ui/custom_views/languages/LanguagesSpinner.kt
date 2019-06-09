package kz.digitalart.app.ui.custom_views.languages

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.view_language_spinner.view.*
import kz.digitalart.app.R


class LanguagesSpinner : ConstraintLayout {

    var onLangItemClickListener: OnLangItemClickListener? = null
    private var droppedDown = false
    private val data = mutableListOf("RU", "KAZ", "ENG")

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    )
            : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_language_spinner, this)
        setTexts()
        tv_selected_lang.setOnClickListener {
            if (droppedDown) {
                slideUp()
            } else {
                slideDown()
            }
            droppedDown = !droppedDown
        }
        rv_first_choice.setOnClickListener {
            slideUp()
            droppedDown = false
            val first = data[0]
            val selected = data[1]
            data[0] = selected
            data[1] = first
            setTexts()
            onLangItemClickListener?.langItemClick(getLangCode(data[0]))
        }
        rv_second_choice.setOnClickListener {
            slideUp()
            droppedDown = false
            val first = data[0]
            val selected = data[2]
            data[0] = selected
            data[2] = first
            setTexts()
            onLangItemClickListener?.langItemClick(getLangCode(data[0]))
        }
        init()
    }

    private fun setTexts() {
        tv_selected_lang.text = data[0]
        tv_first_choice.text = data[1]
        tv_second_choice.text = data[2]
    }

    fun init() {
        ll_dropdown.animate().translationY(-400F).setDuration(0).start()
    }

    fun slideUp() {
        tv_selected_lang.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_chevron_down, 0)
        ll_dropdown.animate().translationY(-400f).setDuration(300).start()
    }

    fun slideDown() {
        tv_selected_lang.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_chevron_up, 0)
        ll_dropdown.animate().translationY(0f).setDuration(300).start()
    }

    fun setLanguage(langCode: String) {
        when (langCode) {
            "ru" -> {

            }
            "kk" -> {
                droppedDown = false
                val first = data[0]
                val selected = data[1]
                data[0] = selected
                data[1] = first
                setTexts()
            }
            "en" -> {
                droppedDown = false
                val first = data[0]
                val selected = data[2]
                data[0] = selected
                data[2] = first
                setTexts()
            }
        }
    }

    private fun getLangCode(lang: String): String {
        return when (lang) {
            "RU" -> {
                "ru"
            }
            "KAZ" -> {
                "kk"
            }
            "ENG" -> {
                "en"
            }
            else -> {
                ""
            }
        }
    }
}
