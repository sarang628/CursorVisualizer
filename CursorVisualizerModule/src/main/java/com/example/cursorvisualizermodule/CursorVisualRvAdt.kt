package com.example.cursorvisualizermodule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cursorvisualizermodule.databinding.ItemColumnCountBinding

class CursorVisualRvAdt : RecyclerView.Adapter<CursorVusialVH>() {
    private var cursors = ArrayList<CursorData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursorVusialVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_column_count, parent, false)
        return CursorVusialVH(view)
    }

    override fun onBindViewHolder(holder: CursorVusialVH, position: Int) {
        holder.setCursorData(cursors[position])
    }

    override fun getItemCount(): Int {
        return cursors.size
    }

    fun setCursors(cursors: ArrayList<CursorData>) {
        this.cursors = cursors
        notifyDataSetChanged()
    }
}

class CursorVusialVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemColumnCountBinding = ItemColumnCountBinding.bind(itemView)
    fun setCursorData(cursorData: CursorData) {
        itemColumnCountBinding.tvName.text = cursorData.name
    }
}