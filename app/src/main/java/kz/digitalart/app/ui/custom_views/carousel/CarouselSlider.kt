package kz.digitalart.app.ui.custom_views.carousel

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.azoft.carousellayoutmanager.DefaultChildSelectionListener
import kotlinx.android.synthetic.main.view_carousel_slider.view.*
import kz.digitalart.app.R
import java.util.*

class CarouselSlider : LinearLayout {

    private val adapter: SliderAdapter by lazy {
        SliderAdapter(
            arrayListOf()
        )
    }

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    )
            : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    )
            : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        inflater?.inflate(R.layout.view_carousel_slider, this)

        val layoutManager = CarouselLayoutManager(
            CarouselLayoutManager.HORIZONTAL
        )
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
        layoutManager.maxVisibleItems = 2

        list_horizontal.layoutManager = layoutManager
        // we expect only fixed sized item for now
        list_horizontal.setHasFixedSize(true)

        list_horizontal.adapter = adapter
        // enable center post scrolling
        list_horizontal.addOnScrollListener(CenterScrollListener())
        // enable center post touching on item and item click listener
        DefaultChildSelectionListener.initCenterItemListener({ recyclerView, carouselLayoutManager, v ->
            val position = recyclerView.getChildLayoutPosition(v)
            val msg = String.format(Locale.US, "Item %1\$d was clicked", position)
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }, list_horizontal, layoutManager)
    }

    fun setItems(items: List<String>) {
        adapter.add(items)
    }
}