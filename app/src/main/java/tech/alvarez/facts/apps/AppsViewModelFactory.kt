package tech.alvarez.facts.apps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.alvarez.facts.Category

class AppsViewModelFactory(private val category: Category) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppsViewModel(category) as T
    }
}