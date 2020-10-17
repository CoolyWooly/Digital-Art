package kz.digitalart.app.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kz.digitalart.app.R
import kz.digitalart.app.databinding.FragmentAboutBinding
import kz.digitalart.app.ui.MainActivity

@AndroidEntryPoint
class AboutFragment : Fragment() {
    private val TAG: String = this::class.java.simpleName

    private val viewModel: AboutViewModel by viewModels()

    var binding: FragmentAboutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_about, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setToolbarTitle(getString(R.string.nav_item_about))
        with(viewModel) {
            data.observe(viewLifecycleOwner, Observer {
                binding?.about = it
            })
            error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, "${it.error}", Toast.LENGTH_LONG).show()
            })
        }
    }
}