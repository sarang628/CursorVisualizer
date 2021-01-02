package com.example.cursorvisualizermodule

import android.content.Context
import android.database.Cursor
import androidx.recyclerview.widget.RecyclerView

interface CursorVisualizer {
    fun attachRecyclerView(recyclerView: RecyclerView)
    fun setCursor(cursor: Cursor)
    fun showDialog(context: Context, cursor: Cursor)

    companion object{
        fun newInstance() : CursorVisualizer {
            return CursorVisualizerImpl()
        }
    }
}