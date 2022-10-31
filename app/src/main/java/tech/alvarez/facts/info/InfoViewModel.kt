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
            loadGaidInfo()
        }
        Category.HMS -> {
            information.value = hmsInfo
            loadOaidInfo()
        }
        Category.OS -> information.value = osInfo
        Category.FEATURES -> information.value = featureInfo
        Category.BATTERY -> information.value = batteryInfo
        else -> information.value = emptyList()
    }

    private fun loadGaidInfo() {
        viewModelScope.launch {
            val gaid = withContext(Dispatchers.IO) { GMS.gaid() }
            val list = mutableListOf<Info>()
            list.addAll(gmsInfo)
            // TODO: Avoid non-null assert
            list.add(Info("Google Advertising ID", gaid!!, null, null))
            information.value = list
        }
    }

    private fun loadOaidInfo() {
        viewModelScope.launch {
            val oaid = withContext(Dispatchers.IO) { HMS.oaid() }
            val list = mutableListOf<Info>()
            list.addAll(hmsInfo)
            list.add(Info("Open Advertising Identifier (OAID)", oaid, null, null))
            information.value = list
        }
    }
}
