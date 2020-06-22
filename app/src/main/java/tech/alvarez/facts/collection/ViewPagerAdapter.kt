package tech.alvarez.facts.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import tech.alvarez.facts.Category
import tech.alvarez.facts.apps.AppsFragment
import tech.alvarez.facts.info.InfoFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = Category.values().size

    override fun createFragment(position: Int) = when (Category.values()[position]) {
        Category.APPS, Category.PACKAGES -> {
            val fragment = AppsFragment()
            fragment.arguments = Bundle().apply {
                putInt("position", position)
            }
            fragment
        }
        else -> {
            val fragment = InfoFragment()
            fragment.arguments = Bundle().apply {
                putInt("position", position)
            }
            fragment
        }
    }
}