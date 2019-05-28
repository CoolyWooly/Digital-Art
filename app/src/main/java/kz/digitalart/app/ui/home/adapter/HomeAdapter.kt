package kz.digitalart.app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kz.digitalart.app.BR.item
import kz.digitalart.app.databinding.ItemExhibitBinding
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.ui.DataBindingViewHolder

class HomeAdapter(
        private var items: ArrayList<Exhibit> = arrayListOf(),
        private var listener: OnExhibitClickListener
) : RecyclerView.Adapter<HomeAdapter.SimpleHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding = ItemExhibitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding)
        : DataBindingViewHolder<Exhibit>(dataBinding) {
        override fun onBind(t: Exhibit): Unit = with(t) {
            dataBinding.setVariable(item, t)
            dataBinding.root.setOnClickListener {
                listener.onExhibitClick(t)
            }
        }
    }

    fun add(list: List<Exhibit>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    interface OnExhibitClickListener {
        fun onExhibitClick(exhibit: Exhibit)
    }
}