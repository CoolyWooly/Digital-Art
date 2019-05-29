package kz.digitalart.app.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kz.digitalart.app.R
import kz.digitalart.app.ui.MainActivity
import javax.inject.Inject
import android.util.Pair as UtilPair


class SettingsFragment : DaggerFragment(), View.OnClickListener {

    private val TAG: String = SettingsFragment::class.java.simpleName
    private val title by lazy(LazyThreadSafetyMode.NONE) { arguments?.getInt("title") ?: 0 }

    companion object {
        val FRAGMENT_NAME: String = SettingsFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SettingsViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).tv_toolbar.text = getString(title)
        ll_language.setOnClickListener(this)
        ll_liked.setOnClickListener(this)
        ll_about.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_language -> {
                Toast.makeText(context, "ll_language", Toast.LENGTH_LONG).show()
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
}