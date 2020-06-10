package tech.alvarez.facts.info

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import tech.alvarez.facts.Info

@BindingAdapter("icon")
fun ImageView.setIcon(item: Info?) {
    item?.let {
        setImageResource(item.icon)
        visibility = if (item.icon == 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("label")
fun TextView.setLabel(item: Info?) {
    item?.let {
        text = item.label
    }
}

@BindingAdapter("value")
fun TextView.setValue(item: Info?) {
    item?.let {
        text = item.value
    }
}
