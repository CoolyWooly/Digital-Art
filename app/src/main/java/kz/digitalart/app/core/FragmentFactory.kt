package kz.digitalart.app.core


import kz.digitalart.app.ui.home.HomeFragment

object FragmentFactory {

    fun getHomeFragment(supportFragmentManager: androidx.fragment.app.FragmentManager): HomeFragment {
        var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.FRAGMENT_NAME)
        if (fragment == null) {
            fragment = HomeFragment()
        }
        return fragment as HomeFragment
    }

}