package tech.alvarez.facts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsInstance
import com.huawei.hms.analytics.HiAnalyticsTools
import tech.alvarez.facts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var instance: HiAnalyticsInstance? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        HiAnalyticsTools.enableLog();
        instance = HiAnalytics.getInstance(this);
        instance?.setAnalyticsEnabled(true)
    }
}
