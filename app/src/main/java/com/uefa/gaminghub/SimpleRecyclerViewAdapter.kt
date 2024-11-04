package com.uefa.gaminghub

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class SimpleRecyclerViewAdapter<VB: ViewBinding, Data: Any> (
    private val inflate: Inflater<VB>,
    private val items: List<Data>,
    private val onBind: (position:Int, rowBinding: VB, Data) -> Unit,
    private val itemInit: ((SimpleRecyclerViewAdapter<VB, Data>.SimpleViewHolder) -> Unit)? = null
) : RecyclerView.Adapter<SimpleRecyclerViewAdapter<VB,Data>.SimpleViewHolder>()  {

     private val _itemList = items.toMutableList()
    private val itemList:List<Data> get() = _itemList

    private fun getItem(position: Int) = itemList.get(position)

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(itemList:List<Data>){
        this._itemList.clear()
        this._itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class SimpleViewHolder(private val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemInit?.invoke(this)
        }

        fun getBinding(): VB {
            return binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val binding = inflate.invoke(LayoutInflater.from(parent.context), parent, false)
        return SimpleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        onBind.invoke(position, holder.getBinding(), getItem(position))
    }
}