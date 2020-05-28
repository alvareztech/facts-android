package tech.alvarez.facts.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.alvarez.facts.Category

class InfoViewModelFactory(private val category: Category) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InfoViewModel(category) as T
    }
}