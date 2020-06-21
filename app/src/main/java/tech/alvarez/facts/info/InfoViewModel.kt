package tech.alvarez.facts.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.alvarez.facts.*

class InfoViewModel(private val category: Category) : ViewModel() {

    val information = MutableLiveData<List<Info>>()

    fun reloadInformation() = when (category) {
        Category.DEVICE -> information.value = deviceInfo()
        Category.GMS -> information.value = gmsInfo
        Category.HMS -> information.value = hmsInfo
        Category.OS -> information.value = osInfo
        Category.FEATURES -> information.value = featureInfo
        else -> information.value = emptyList()
    }
}
