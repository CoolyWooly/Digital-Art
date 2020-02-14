package kz.digitalart.app.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.toolbar_main.*
import kz.digitalart.app.R
import kz.digitalart.app.databinding.FragmentAboutBinding
import kz.digitalart.app.ui.MainActivity
import javax.inject.Inject

class AboutFragment : DaggerFragment() {
    private val TAG: String = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AboutViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(AboutViewModel::class.java)
    }
    var binding: FragmentAboutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(
            inflater, R.layout.fragment_about, container, false
        )
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).tv_toolbar.text = getString(R.string.nav_item_about)
        with(viewModel) {
            data.observe(viewLifecycleOwner, Observer {
                binding?.about = it
            })
        }
    }
}