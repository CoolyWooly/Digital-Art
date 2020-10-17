package kz.digitalart.app.ui.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kz.digitalart.app.R
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.liked.adapter.LikedAdapter
import kz.digitalart.app.utils.EndlessRecyclerViewScrollListener

@AndroidEntryPoint
class LikedFragment : Fragment(), LikedAdapter.OnExhibitClickListener {

    private val TAG: String = this::class.java.simpleName

    private val viewModel: LikedViewModel by viewModels()

    private val adapter: LikedAdapter by lazy { LikedAdapter(arrayListOf(), this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_liked, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(getString(R.string.popular))
        initView()
        with(viewModel) {
            exhibitsData.observe(viewLifecycleOwner, Observer {
                if (it.isNotEmpty()) {
                    adapter.add(it)
                }
            })
            error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, "${it?.error}", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun initView() {
        val mLayoutManager = GridLayoutManager(context, 2)
        rv_main_home.layoutManager = mLayoutManager
        rv_main_home.adapter = adapter
        rv_main_home.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                viewModel.getExhibits(page, 20, null)
            }
        })
    }

    override fun onExhibitClick(exhibitModel: ExhibitModel) {
        val action = LikedFragmentDirections.actionFragmentLikedToFragmentLikedDetails()
        action.exhibit = exhibitModel
        val navController = Navigation.findNavController(view!!)
        navController.navigate(action)
    }
}