package tech.alvarez.facts.info

import android.widget.TextView
import androidx.databinding.BindingAdapter
import tech.alvarez.facts.Info

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
