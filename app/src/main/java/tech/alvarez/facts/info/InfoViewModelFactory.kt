package tech.alvarez.facts.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import tech.alvarez.facts.Category

class InfoViewModelFactory(private val category: Category) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return InfoViewModel(category) as T
    }
}