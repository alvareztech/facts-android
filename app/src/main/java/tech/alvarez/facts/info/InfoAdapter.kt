package tech.alvarez.facts.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.alvarez.facts.Info
import tech.alvarez.facts.databinding.ItemInfoBinding

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

    class InfoViewHolder(val binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info) {
            binding.labelTextView.text = info.label
            binding.valueTextView.text = info.value
        }

        companion object {
            fun from(parent: ViewGroup): InfoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemInfoBinding.inflate(layoutInflater, parent, false)
                return InfoViewHolder(binding)
            }
        }
    }
}

