package kz.digitalart.app.ui.liked.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kz.digitalart.app.BR.item
import kz.digitalart.app.databinding.ItemExhibitBinding
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.ui.DataBindingViewHolder

class LikedAdapter(
    private var items: ArrayList<ExhibitModel> = arrayListOf(),
    private var listener: OnExhibitClickListener
) : RecyclerView.Adapter<LikedAdapter.SimpleHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding = ItemExhibitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding) :
        DataBindingViewHolder<ExhibitModel>(dataBinding) {
        override fun onBind(t: ExhibitModel): Unit = with(t) {
            dataBinding.setVariable(item, t)
            dataBinding.root.setOnClickListener {
                listener.onExhibitClick(t)
            }
        }
    }

    fun add(list: List<ExhibitModel>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    interface OnExhibitClickListener {
        fun onExhibitClick(exhibitModel: ExhibitModel)
    }
}