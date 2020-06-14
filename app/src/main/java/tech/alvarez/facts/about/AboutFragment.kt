package tech.alvarez.facts.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tech.alvarez.facts.R
import tech.alvarez.facts.databinding.FragmentAboutBinding
import tech.alvarez.facts.util.Util

class AboutFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        with(binding) {
            titleTextView.text = getString(R.string.app_name)
            versionTextView.text = Util.appVersion()
            descriptionTextView.text = getString(R.string.app_description)
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