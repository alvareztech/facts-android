package tech.alvarez.facts.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.alvarez.facts.Info
import tech.alvarez.facts.databinding.ItemInfoBinding

class InfoAdapter(private val itemListener: ItemListener) :
    RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

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
        holder.bind(value, itemListener)
    }

    class InfoViewHolder(private val binding: ItemInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info, itemListener: ItemListener) = with(binding) {
            iconImageView.setImageResource(info.icon)
            iconImageView.visibility = if (info.icon == 0) {
                View.GONE
            } else {
                View.VISIBLE
            }
            labelTextView.text = info.label
            valueTextView.text = info.value
            root.setOnClickListener {
                itemListener.onClick(info)
            }
            root.setOnLongClickListener {
                itemListener.onLongClick(info)
                true
            }
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

class ItemListener(
    val clickListener: (info: Info) -> Unit,
    val longClickListener: (info: Info) -> Unit
) {
    fun onClick(item: Info) = clickListener(item)
    fun onLongClick(item: Info) = longClickListener(item)
}
