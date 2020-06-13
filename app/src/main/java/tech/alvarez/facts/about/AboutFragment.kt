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

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)

        binding.titleTextView.text = getString(R.string.app_name)
        binding.versionTextView.text = Util.appVersion()
        binding.descriptionTextView.text = getString(R.string.app_description)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    companion object {
        fun newInstance() = AboutFragment()
    }
}