package kz.digitalart.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kz.digitalart.app.R
import kz.digitalart.app.databinding.FragmentHomeBinding
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.home.adapter.HomeAdapter
import kz.digitalart.app.utils.EndlessRecyclerViewScrollListener
import javax.inject.Inject
import android.util.Pair as UtilPair


abstract class HomeFragment : DaggerFragment(), HomeAdapter.OnExhibitClickListener {

    private val TAG: String = HomeFragment::class.java.simpleName

    companion object {
        val FRAGMENT_NAME: String = HomeFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    private val adapter: HomeAdapter by lazy { HomeAdapter(arrayListOf(), this) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).tv_toolbar.text = getString(R.string.nav_item_main)
        initView()
        with(viewModel) {
            searchString.observe(this@HomeFragment, Observer {
                if (it.isNullOrEmpty() || it.length > 2) {
                    getExhibits(0, 20, it)
                    scrollListener.resetValues()
                }
            })
            exhibitsData.observe(this@HomeFragment, Observer {
                if (it!!.isNotEmpty()) {
                    adapter.add(it)
                }
            })
            error.observe(this@HomeFragment, Observer {
                progressBar_home.visibility = View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
        }
    }

    abstract var scrollListener : EndlessRecyclerViewScrollListener

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
        progressBar_home.visibility = View.GONE
    }

    override fun onExhibitClick(exhibit: Exhibit) {

        val action = HomeFragmentDirections.actionNavItemMainToFragmentHomeDetails()
        action.exhibit = exhibit
        val navController = Navigation.findNavController(view!!)
        navController.navigate(action)
    }
}