package kz.digitalart.app.ui.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kz.digitalart.app.R
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.liked.adapter.LikedAdapter
import kz.digitalart.app.utils.EndlessRecyclerViewScrollListener
import javax.inject.Inject


class LikedFragment : DaggerFragment(), LikedAdapter.OnExhibitClickListener {

    private val TAG: String = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LikedViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LikedViewModel::class.java)
    }
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
                progressBar_home.visibility = View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
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
        progressBar_home.visibility = View.GONE
    }

    override fun onExhibitClick(exhibitModel: ExhibitModel) {
        val action = LikedFragmentDirections.actionFragmentLikedToFragmentLikedDetails()
        action.exhibit = exhibitModel
        val navController = Navigation.findNavController(view!!)
        navController.navigate(action)
    }
}