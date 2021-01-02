package com.example.cursorvisualizermodule

import android.app.Dialog
import android.content.Context
import android.database.Cursor
import android.database.Cursor.*
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.cursorvisualizermodule.databinding.DialogCursorBinding
import java.util.*
import kotlin.collections.ArrayList

class CursorVisualizerImpl : CursorVisualizer {
    val adapter = CursorVisualRvAdt()
    override fun attachRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }

    override fun setCursor(cursor: Cursor) {
        val cursors = ArrayList<CursorData>()
        cursors.add(CursorData("cursor.columnCount = ${cursor.columnCount}"))
        cursors.add(
            CursorData(
                "cursor.columnNames = ${
                    Arrays.toString(cursor.columnNames)
                }"
            )
        )
        cursor.moveToFirst()
        while (cursor.moveToNext()) {
            Log.e("__sarang", "columnCount = ${cursor.columnCount}")
            var text = StringBuffer()
            for (i in 0 until cursor.columnCount) {
                if (cursor.getType(i) != FIELD_TYPE_NULL)
                    text.append(
                        "" +
                                "${cursor.getColumnName(i)} = ${cursor.getData(i)} \n" +
                                ""
                    )
            }
            cursors.add(CursorData(text.toString()))
        }

        adapter.setCursors(cursors)
    }

    override fun showDialog(context: Context, cursor: Cursor) {
        val viewBinding = DialogCursorBinding.inflate(LayoutInflater.from(context))
        attachRecyclerView(viewBinding.rv)
        setCursor(cursor)

        Dialog(context).apply {
            setContentView(viewBinding.root)
        }.show()
    }

    fun Cursor.getData(i: Int): String {
        if (this.getType(i) == FIELD_TYPE_STRING) {
            return getString(i)
        } else if (this.getType(i) == FIELD_TYPE_FLOAT) {
            return getFloat(i).toString()
        } else if (this.getType(i) == FIELD_TYPE_INTEGER) {
            return getInt(i).toString()
        } else if (this.getType(i) == FIELD_TYPE_NULL) {
            return getInt(i).toString()
        }


        return "type is not defined"
    }
}