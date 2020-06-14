package tech.alvarez.facts.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tech.alvarez.facts.databinding.FragmentAboutBinding
import tech.alvarez.facts.util.Util

const val PROJECT_PAGE = "https://alvarez.tech/projects/facts/"

class AboutFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        with(binding) {
            versionTextView.text = Util.appVersion()
            projectButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(PROJECT_PAGE))
                startActivity(intent)
            }
            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AboutFragment()
    }
}