package tech.alvarez.facts.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.alvarez.facts.*

class InfoViewModel(category: Category) : ViewModel() {

    val information: LiveData<List<Info>> = MutableLiveData<List<Info>>().apply {
        when (category) {
            Category.DEVICE -> postValue(deviceInfo)
            Category.GMS -> postValue(gmsInfo)
            Category.HMS -> postValue(hmsInfo)
            Category.OS -> postValue(osInfo)
            Category.FEATURES -> postValue(featureInfo)
        }
    }
}
