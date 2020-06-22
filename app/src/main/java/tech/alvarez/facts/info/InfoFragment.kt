package tech.alvarez.facts.info

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.alvarez.facts.Category
import tech.alvarez.facts.Info
import tech.alvarez.facts.databinding.FragmentInfoBinding
import tech.alvarez.facts.util.shareText

private const val REQUEST_PERMISSIONS = 777

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: InfoViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        with(binding) {
            recyclerView = infoRecyclerView
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
            ViewModelProvider(this, InfoViewModelFactory(category)).get(InfoViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        adapter = InfoAdapter(ItemListener({
            handleItemSelection(it)
        }, {
            shareItem(it)
        }))
        recyclerView.adapter = adapter
        viewModel.information.observe(viewLifecycleOwner, Observer {
            adapter.data = it
        })
        viewModel.reloadInformation()
    }

    private fun handleItemSelection(item: Info) {
        item.permission?.let {
            if (!hasPermissions(context, arrayOf(item.permission))) {
                requestPermissions(arrayOf(item.permission), REQUEST_PERMISSIONS);
            }
        }
    }

    private fun shareItem(item: Info) {
        shareText("${item.label}\n${item.value}")
    }

    private fun hasPermissions(context: Context?, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context!!,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PERMISSIONS ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewModel.reloadInformation()
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = InfoFragment()
    }
}
