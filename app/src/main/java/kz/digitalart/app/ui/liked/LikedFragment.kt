package kz.digitalart.app.ui.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kz.digitalart.app.R
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.liked.adapter.LikedAdapter
import kz.digitalart.app.utils.EndlessRecyclerViewScrollListener
import javax.inject.Inject
import android.util.Pair as UtilPair


class LikedFragment : DaggerFragment(), LikedAdapter.OnExhibitClickListener {

    private val TAG: String = LikedFragment::class.java.simpleName

    companion object {
        val FRAGMENT_NAME: String = LikedFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LikedViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LikedViewModel::class.java)
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
        (activity as MainActivity).tv_toolbar.text = getString(R.string.popular)
        initView()
        with(viewModel) {
            exhibitsData.observe(this@LikedFragment, Observer {
                if (it!!.isNotEmpty()) {
                    adapter.add(it)
                }
            })
            error.observe(this@LikedFragment, Observer {
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

    override fun onExhibitClick(exhibit: Exhibit) {
        val action = LikedFragmentDirections.actionFragmentLikedToFragmentLikedDetails()
        action.exhibit = exhibit
        val navController = Navigation.findNavController(view!!)
        navController.navigate(action)
    }
}