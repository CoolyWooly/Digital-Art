package kz.digitalart.app.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kz.digitalart.app.R
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.settings.languages.LanguagesBottomSheetCallback
import kz.digitalart.app.ui.settings.languages.LanguagesBottomSheetDialogFragment

@AndroidEntryPoint
class SettingsFragment : Fragment(), View.OnClickListener, LanguagesBottomSheetCallback {

    private val TAG: String = this::class.java.simpleName

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(getString(R.string.nav_item_settings))
        ll_language.setOnClickListener(this)
        ll_liked.setOnClickListener(this)
        ll_about.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_language -> {
                val bottomSheetFragment =
                    LanguagesBottomSheetDialogFragment(viewModel.prefsImpl, activity, this)
                bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
            }
            R.id.ll_liked -> {
                val navController = Navigation.findNavController(view!!)
                val action = SettingsFragmentDirections.actionNavItemSettingsToFragmentLiked()
                navController.navigate(action)
            }
            R.id.ll_about -> {
                val navController = Navigation.findNavController(view!!)
                val action = SettingsFragmentDirections.actionNavItemSettingsToNavItemAbout()
                navController.navigate(action)
            }
        }
    }

    override fun languageClicked() {
        activity?.onConfigurationChanged(activity?.applicationContext!!.resources!!.configuration)
        activity?.recreate()
    }
}