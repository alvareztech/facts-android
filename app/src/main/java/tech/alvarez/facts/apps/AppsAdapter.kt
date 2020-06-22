package tech.alvarez.facts.apps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.alvarez.facts.App
import tech.alvarez.facts.databinding.ItemAppBinding

class AppsAdapter(private val appListener: AppListener) :
    RecyclerView.Adapter<AppsAdapter.AppViewHolder>() {

    var data = listOf<App>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder.from(parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val value = data[position]
        holder.bind(value, appListener)
    }

    class AppViewHolder(private val binding: ItemAppBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(app: App, appListener: AppListener) = with(binding) {
            iconImageView.setImageDrawable(app.icon)
            nameTextView.text = app.name
            versionTextView.text = app.version
            packageTextView.text = app.packageName
            userAppChip.visibility = if (app.isSystemPackage) View.GONE else View.VISIBLE
            root.setOnClickListener {
                appListener.onClick(app)
            }
            root.setOnLongClickListener {
                appListener.onLongClick(app)
                true
            }
        }

        companion object {
            fun from(parent: ViewGroup): AppViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAppBinding.inflate(layoutInflater, parent, false)
                return AppViewHolder(binding)
            }
        }
    }
}

class AppListener(
    val clickListener: (app: App) -> Unit,
    val longClickListener: (app: App) -> Unit
) {
    fun onClick(app: App) = clickListener(app)
    fun onLongClick(app: App) = longClickListener(app)
}
