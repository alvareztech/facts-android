package tech.alvarez.facts.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.alvarez.facts.R

class InfoAdapter() : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    var data = listOf<Info>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder.from(parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val value = data[position]
        holder.bind(value)
    }

    class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labelTextView: TextView = itemView.findViewById(R.id.labelTextView)
        val valueTextView: TextView = itemView.findViewById(R.id.valueTextView)

        fun bind(info: Info) {
            labelTextView.text = info.label
            valueTextView.text = info.value
        }

        companion object {
            fun from(parent: ViewGroup): InfoViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
                return InfoViewHolder(view)
            }
        }
    }
}

