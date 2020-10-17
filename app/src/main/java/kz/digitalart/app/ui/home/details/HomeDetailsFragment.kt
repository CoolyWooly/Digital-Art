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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_details.*
import kz.digitalart.app.R
import kz.digitalart.app.databinding.FragmentHomeDetailsBinding
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.ui.MainActivity

@AndroidEntryPoint
class HomeDetailsFragment : Fragment() {

    private val TAG: String = this::class.java.simpleName
    private var exhibitModel: ExhibitModel? = null

    private val viewModel: HomeDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeDetailsBinding>(
            inflater, R.layout.fragment_home_details, container, false
        )
        exhibitModel = arguments?.getSerializable("exhibit") as ExhibitModel
        binding.item = exhibitModel
        Log.e(TAG, exhibitModel?.id_exhibit.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(getString(R.string.overview))
        audio_player.setURL(exhibitModel?.audio)
        if (exhibitModel?.images.isNullOrEmpty()) {
            carousel_slider.visibility = View.GONE
        } else {
            val strList = exhibitModel!!.images!!.map { it.url!! }
            carousel_slider.setItems(strList)
        }
        tv_rate.setOnClickListener {
//            if (!viewModel.prefsImpl.getRatings().contains(exhibitModel?.id_exhibit.toString())) {
                showRateAlertDialog()
//            }
        }
        with(viewModel) {
            ratingModelData.observe(viewLifecycleOwner, Observer {
                tv_rate.text = it.rating?.toString()
            })
            error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, "${it?.error}", Toast.LENGTH_LONG).show()
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
                viewModel.prefsImpl.setRatings(exhibitModel?.id_exhibit!!)
                viewModel.setExhibitRate(exhibitModel?.id_exhibit, ratingBar.rating.toDouble())
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}