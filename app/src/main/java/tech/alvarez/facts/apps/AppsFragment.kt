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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.alvarez.facts.App
import tech.alvarez.facts.Category
import tech.alvarez.facts.databinding.FragmentAppsBinding
import tech.alvarez.facts.util.openApp
import tech.alvarez.facts.util.openInTheMarket
import tech.alvarez.facts.util.openPlayStoreWeb
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
            handleSelectApp(it)
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

    private fun handleSelectApp(app: App) {
        val items = arrayOf(
            "Open App",
            "Open in the Market App",
            "Open in Play Store Web"
        )
        MaterialAlertDialogBuilder(context)
            .setTitle(app.name)
            .setItems(items) { _, which ->
                when (which) {
                    0 -> openApp(app.packageName)
                    1 -> openInTheMarket(app.packageName)
                    2 -> openPlayStoreWeb(app.packageName)
                }
            }
            .show()
    }

    private fun shareApp(app: App) {
        shareText("${app.name}\n${app.version}\n${app.packageName}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AppsFragment()
    }
}