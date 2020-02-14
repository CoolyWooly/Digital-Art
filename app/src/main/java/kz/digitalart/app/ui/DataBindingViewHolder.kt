package kz.digitalart.app.ui

import androidx.databinding.ViewDataBinding


abstract class DataBindingViewHolder<T> (
    val dataBinding: ViewDataBinding
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(dataBinding.root) {

    abstract fun onBind(t: T)
}

