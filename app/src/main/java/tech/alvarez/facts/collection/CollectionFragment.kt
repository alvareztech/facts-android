package tech.alvarez.facts.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import tech.alvarez.facts.Category
import tech.alvarez.facts.about.AboutFragment
import tech.alvarez.facts.databinding.FragmentCollectionBinding

private const val ARG_PARAM1 = "param1"

class CollectionFragment : Fragment() {
    private var param1: String? = null

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        with(binding) {
            viewPagerAdapter = ViewPagerAdapter(this@CollectionFragment)
            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                val category = Category.values()[position]
                tab.text = getString(category.title)
                tab.icon = AppCompatResources.getDrawable(context!!, category.icon)
            }.attach()

            fab.setOnClickListener {
                val aboutFragment = AboutFragment.newInstance()
                aboutFragment.show(activity!!.supportFragmentManager, "tagy")
            }
            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CollectionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
