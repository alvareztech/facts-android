package tech.alvarez.facts.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.alvarez.facts.*
import tech.alvarez.facts.util.GMS
import tech.alvarez.facts.util.HMS

class InfoViewModel(private val category: Category) : ViewModel() {

    val information = MutableLiveData<List<Info>>()

    fun reloadInformation() = when (category) {
        Category.DEVICE -> information.value = deviceInfo()
        Category.GMS -> {
            information.value = gmsInfo
            loadAdsInformation()
        }
        Category.HMS -> {
            information.value = hmsInfo
            loadAdsInformation()
        }
        Category.OS -> information.value = osInfo
        Category.FEATURES -> information.value = featureInfo
        else -> information.value = emptyList()
    }

    private fun loadAdsInformation() {
        viewModelScope.launch {
            when (category) {
                Category.HMS -> {
                    val oaid = withContext(Dispatchers.IO) { HMS.oaid() }
                    val list = mutableListOf<Info>()
                    list.addAll(hmsInfo)
                    list.add(Info("OAID", oaid, null, null))
                    information.value = list
                }
                Category.GMS -> {
                    val oaid = withContext(Dispatchers.IO) { GMS.gaid() }
                    val list = mutableListOf<Info>()
                    list.addAll(hmsInfo)
                    list.add(Info("Google Advertising ID", oaid, null, null))
                    information.value = list
                }
            }
        }
    }
}
