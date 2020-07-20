package tech.alvarez.facts.apps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.alvarez.facts.App
import tech.alvarez.facts.Category
import tech.alvarez.facts.util.Util

class AppsViewModel(private val category: Category) : ViewModel() {

    val information = MutableLiveData<List<App>>()

    fun load() {
        viewModelScope.launch {
            information.value = withContext(Dispatchers.IO) { loadApps() }
        }
    }

    private fun loadApps(): List<App> = when (category) {
        Category.USER_APPS -> Util.userApps().sortedBy { it.name }.filter { !it.isSystemPackage }
        Category.SYSTEM_APPS -> Util.userApps().sortedBy { it.name }.filter { it.isSystemPackage }
        Category.PACKAGES -> Util.systemApps().sortedBy { it.name }
        else -> emptyList()
    }
}