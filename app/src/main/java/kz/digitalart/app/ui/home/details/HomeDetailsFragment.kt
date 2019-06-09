package kz.digitalart.app.ui.home.details

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
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
    private var exhibit: Exhibit? = null

    companion object {
        val FRAGMENT_NAME: String = HomeDetailsFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeDetailsViewModel by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(HomeDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeDetailsBinding>(
            inflater, R.layout.fragment_home_details, container, false
        )
        exhibit = arguments?.getSerializable("exhibit") as Exhibit
        binding.item = exhibit
        Log.e(TAG, exhibit?.id.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).tv_toolbar.text = getString(R.string.overview)
        audio_player.setURL(exhibit?.audio)
        if (exhibit?.photos.isNullOrEmpty()) {
            carousel_slider.visibility = View.GONE
        } else {
            carousel_slider.setItems(exhibit?.photos!!)
        }
        tv_rate.setOnClickListener {
            if (!viewModel.prefsImpl.getRatings().contains(exhibit?.id.toString())) {
                showRateAlertDialog()
            }
        }
        with(viewModel) {
            ratingData.observe(this@HomeDetailsFragment, Observer {
                tv_rate.text = it.rating?.toString()
            })
        }
    }

    private fun showRateAlertDialog() {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_rate)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val ratingBar = dialog.findViewById(R.id.rb_rate) as RatingBar
        val btnContinue = dialog.findViewById(R.id.btn_continue) as TextView

        btnContinue.setOnClickListener {
            if (ratingBar.rating == 0f) {
                Toast.makeText(context, R.string.select_rating, Toast.LENGTH_LONG).show()
            } else {
                viewModel.prefsImpl.setRatings(exhibit?.id!!)
                viewModel.setExhibitRate(exhibit?.id, ratingBar.rating.toDouble())
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}