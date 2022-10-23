package com.example.rpos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rpos.R
import com.example.rpos.enums.Type
import com.example.rpos.source.*
import kotlinx.coroutines.runBlocking

class PositionAdapter(
    private val type: Type,
    private val ids: List<Long>,
    private val names: List<String>,
    private val callback: () -> Unit
) : RecyclerView.Adapter<PositionAdapter.MyViewHolder>(), View.OnClickListener {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textItem)
        val deleteButton: TextView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recycle_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = if (names[position].length > 50) "${names[position].substring(0, 50)}..." else names[position]
        holder.deleteButton.tag = ids[position]
        holder.deleteButton.setOnClickListener(this)
    }

    override fun getItemCount() = names.size

    override fun onClick(v: View) {
        if (v.id == R.id.deleteButton) {
            deleteClickEvent(v.tag as Long)
            callback.invoke()
        }
    }

    private fun deleteClickEvent(id: Long) = runBlocking {
        when (type) {
           Type.Position -> PositionsSource().deleteById(id)
           Type.Client -> ClientSource().deleteById(id)
           Type.Employee -> EmployeeSource().deleteById(id)
           Type.Product -> ProductSource().deleteById(id)
           Type.Storage -> StorageSource().deleteById(id)
        }
    }
}