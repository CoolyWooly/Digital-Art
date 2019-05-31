package kz.digitalart.app.ui.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home_details.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kz.digitalart.app.R
import kz.digitalart.app.databinding.FragmentHomeDetailsBinding
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.ui.MainActivity
import javax.inject.Inject
import android.util.Pair as UtilPair

class HomeDetailsFragment : DaggerFragment() {
    private val TAG: String = HomeDetailsFragment::class.java.simpleName
    private val title by lazy(LazyThreadSafetyMode.NONE) { arguments?.getInt("title") ?: 0 }
    private var exhibit: Exhibit? = null

    companion object {
        val FRAGMENT_NAME: String = HomeDetailsFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeDetailsViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(HomeDetailsViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeDetailsBinding>(
                inflater, R.layout.fragment_home_details, container, false)
        exhibit = arguments?.getSerializable("exhibit") as Exhibit
        binding.item = exhibit
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).tv_toolbar.text = getString(title)
        with(viewModel) {
            action.observe(this@HomeDetailsFragment, Observer {
                actionClicked(it)
            })
        }
        audio_player.setURL(exhibit?.audio)
        exhibit?.photos?.let {
            carousel_slider.setItems(it)
        }
    }

    private fun actionClicked(action: String) {
        when (action) {
            HomeDetailsViewModel.ACTION_LANGUAGE -> {
                Toast.makeText(context, action, Toast.LENGTH_LONG).show()
            }
            HomeDetailsViewModel.ACTION_LIKED -> {
                Toast.makeText(context, action, Toast.LENGTH_LONG).show()
            }
            HomeDetailsViewModel.ACTION_ABOUT -> {
                Toast.makeText(context, action, Toast.LENGTH_LONG).show()
            }
        }
    }
}