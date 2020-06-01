package tech.alvarez.facts.info

import android.Manifest
import android.content.Context
import android.content.Intent
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
import tech.alvarez.facts.R

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var viewModel: InfoViewModel

    private var recyclerView: RecyclerView? = null
    private var adapter: InfoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.info_fragment, container, false)
        recyclerView = view.findViewById(R.id.infoRecyclerView)
        return view
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
        recyclerView?.layoutManager = linearLayoutManager

        adapter = InfoAdapter(ItemListener({
            handleItemSelection(it)
        }, {
            shareItem(it)
        }))
        recyclerView!!.adapter = adapter
        viewModel.information.observe(viewLifecycleOwner, Observer {
            adapter?.data = it
        })
    }

    private fun handleItemSelection(item: Info) {
        item.permission?.let {
            if (!hasPermissions(context, arrayOf(item.permission))) {
                requestPermissions(arrayOf(item.permission), 888);
            }
            adapter?.notifyDataSetChanged()
        }
    }

    private fun shareItem(item: Info) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${item.label}\n${item.value}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
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
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            // FIXME: crash
        }
    }
}
