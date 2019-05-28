package kz.digitalart.app.ui.home

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
import kz.digitalart.app.ui.home.adapter.HomeAdapter
import javax.inject.Inject
import android.util.Pair as UtilPair


class HomeFragment : DaggerFragment(), HomeAdapter.OnExhibitClickListener {

    private val TAG: String = HomeFragment::class.java.simpleName
    private val title by lazy(LazyThreadSafetyMode.NONE) { arguments?.getInt("title") ?: 0 }

    companion object {
        val FRAGMENT_NAME: String = HomeFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    val adapter: HomeAdapter by lazy { HomeAdapter(arrayListOf(), this) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).tv_toolbar.text = getString(title)
        with(viewModel) {
            exhibitsData.observe(this@HomeFragment, Observer {
                initView(it)
            })
            error.observe(this@HomeFragment, Observer {
                progressBar_home.visibility = View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun initView(it: List<Exhibit>?) {
        val mLayoutManager = GridLayoutManager(context, 2)
        rv_main_home.layoutManager = mLayoutManager
        rv_main_home.adapter = adapter
        progressBar_home.visibility = View.GONE
        if (it!!.isNotEmpty()) {
            adapter.clear()
            adapter.add(it)

        } else {
            Toast.makeText(context, context?.getString(R.string.empty_list), Toast.LENGTH_LONG).show()
        }
    }

    override fun onExhibitClick(exhibit: Exhibit) {

        val action = HomeFragmentDirections.actionNavItemMainToFragmentHomeDetails()
        action.exhibit = exhibit
        val navController = Navigation.findNavController(view!!)
        navController.navigate(action)
    }
}