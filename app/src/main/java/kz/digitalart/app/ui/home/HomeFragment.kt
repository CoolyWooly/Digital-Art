package kz.digitalart.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kz.digitalart.app.R
import kz.digitalart.app.databinding.FragmentHomeBinding
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.domain.model.response.ErrorStatus
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.home.adapter.HomeAdapter
import kz.digitalart.app.utils.EndlessRecyclerViewScrollListener
import javax.inject.Inject


class HomeFragment : DaggerFragment(), HomeAdapter.OnExhibitClickListener {

    private val TAG: String = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    private val adapter: HomeAdapter by lazy { HomeAdapter(arrayListOf(), this) }
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(getString(R.string.nav_item_main))
        initView()
        with(viewModel) {
            searchString.observe(viewLifecycleOwner, Observer {
                scrollListener.resetValues()
                adapter.clear()
                getExhibits(1, 20, it)
            })
            exhibitsData.observe(viewLifecycleOwner, Observer {
                ll_connection_error.visibility = View.GONE
                if (it.isNotEmpty()) {
                    adapter.add(it)
                }
            })
            error.observe(viewLifecycleOwner, Observer {
                if (listOf(ErrorStatus.NO_CONNECTION, ErrorStatus.TIMEOUT).contains(it.status) &&
                    adapter.itemCount == 0
                ) {
                    ll_connection_error.visibility = View.VISIBLE
                }
                Toast.makeText(context, "${it?.error}", Toast.LENGTH_LONG).show()
            })
        }

        tv_retry.setOnClickListener {
            viewModel.getExhibits(page = 1, limit = 20)
        }
    }


    private fun initView() {
        val mLayoutManager = GridLayoutManager(context, 2)
        rv_main_home.layoutManager = mLayoutManager
        rv_main_home.adapter = adapter
        scrollListener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                viewModel.getExhibits(page, 20, viewModel.searchString.value)
            }
        }
        rv_main_home.addOnScrollListener(scrollListener)
        swipe.setOnRefreshListener {
            scrollListener.resetValues()
            adapter.clear()
            viewModel.getExhibits(1, 20, viewModel.searchString.value)
        }
    }

    override fun onExhibitClick(exhibitModel: ExhibitModel) {
        val action = HomeFragmentDirections.actionNavItemMainToFragmentHomeDetails()
        action.exhibit = exhibitModel
        val navController = Navigation.findNavController(view!!)
        navController.navigate(action)
    }
}