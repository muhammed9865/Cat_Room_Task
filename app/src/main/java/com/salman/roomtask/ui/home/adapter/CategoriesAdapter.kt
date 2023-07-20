package com.salman.roomtask.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salman.roomtask.R
import com.salman.roomtask.model.Category

class CategoriesAdapter(private val list: List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {

        fun onItemClicked(category: Category)

    }

    fun setItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.select_category_item, parent, false), mListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTitle.text = list[position].title
    }

    inner class ViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){
        val categoryTitle: TextView = itemView.findViewById(R.id.category_title)

        init {
            itemView.setOnClickListener {
                listener.onItemClicked(list[adapterPosition])
            }
        }
    }
}
