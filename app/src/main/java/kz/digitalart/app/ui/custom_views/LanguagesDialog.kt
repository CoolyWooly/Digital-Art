package kz.digitalart.app.ui.custom_views

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_languages_dialog.view.*
import kz.digitalart.app.R

class LanguagesDialog : LinearLayout, View.OnClickListener {

    private var onItemClickListener: OnItemClickListener? = null

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int,
            defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        inflater?.inflate(R.layout.view_languages_dialog, this)
        rv_settings_change_order.setOnClickListener(this)
        rv_settings_add_card.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.rv_settings_change_order -> {
                onItemClickListener?.onItemClick()
                slideUp()
            }
            R.id.rv_settings_add_card -> {
                onItemClickListener?.onItemClick()
                slideUp()
            }
        }
    }

    fun init() {
        ll_root.animate().translationY(-400F).setDuration(0).start()
    }

    fun slideUp() {
        ll_root.animate().translationY((ll_root.height * (-1)).toFloat()).setDuration(300).start()
    }

    fun slideDown() {
        ll_root.animate().translationY(0f).setDuration(300).start()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick()
    }
}