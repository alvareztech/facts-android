package tech.alvarez.facts.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.alvarez.facts.util.Util


data class Info(val label: String, val value: String)

class InfoViewModel : ViewModel() {

    val information: LiveData<List<Info>> = MutableLiveData<List<Info>>().apply {
        postValue(
            listOf(
                Info("Android Version", Util.androidVersion()),
                Info("Android Release", Util.androidRelease()),
                Info("Security Path", Util.securityPath),
                Info("Device Name", Util.getDeviceName()),
                Info("Manufacturer", Util.manufacturer),
                Info("Product", Util.product),
                Info("Model", Util.model),
                Info("Screen Size", Util.screenSize()),
                Info("Board", Util.board),
                Info("Display", Util.display),
                Info("Hardware", Util.hardware),
                Info("32 bit ABIs supported", Util.supported32bits()),
                Info("64 bit ABIs supported", Util.supported64bits()),
                Info("Total", Util.totalMemory())
            )
        )
    }
}
