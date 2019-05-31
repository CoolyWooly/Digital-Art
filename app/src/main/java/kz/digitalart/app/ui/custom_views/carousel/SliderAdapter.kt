package kz.digitalart.app.ui.custom_views.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kz.digitalart.app.BR.item
import kz.digitalart.app.databinding.ItemSliderBinding
import kz.digitalart.app.ui.DataBindingViewHolder

class SliderAdapter(
        private var items: ArrayList<String> = arrayListOf()
) : RecyclerView.Adapter<SliderAdapter.SimpleHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding = ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding)
        : DataBindingViewHolder<String>(dataBinding) {
        override fun onBind(t: String): Unit = with(t) {
            dataBinding.setVariable(item, t)
        }
    }

    fun add(list: List<String>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}