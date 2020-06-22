package tech.alvarez.facts.apps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.alvarez.facts.App
import tech.alvarez.facts.Category
import tech.alvarez.facts.databinding.FragmentAppsBinding
import tech.alvarez.facts.util.openApp
import tech.alvarez.facts.util.shareText

class AppsFragment : Fragment() {

    private var _binding: FragmentAppsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AppsViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AppsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppsBinding.inflate(inflater, container, false)
        with(binding) {
            recyclerView = appsRecyclerView
            return root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var category = Category.DEVICE
        arguments?.takeIf {
            it.containsKey("position")
        }?.apply {
            val position = getInt("position")
            category = Category.values()[position]
        }
        viewModel =
            ViewModelProvider(this, AppsViewModelFactory(category)).get(AppsViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        adapter = AppsAdapter(AppListener({
            openApp(it.packageName)
        }, {
            shareApp(it)
        }))
        recyclerView.adapter = adapter
        viewModel.information.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adapter.data = it
        })
        binding.progressBar.visibility = View.VISIBLE
        viewModel.load()
    }

    private fun shareApp(app: App) {
        shareText("${app.name} ${app.version}\n${app.packageName}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AppsFragment()
    }
}