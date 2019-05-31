package kz.digitalart.app.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import kz.digitalart.app.R
import kz.digitalart.app.databinding.ActivityWelcomeBinding
import kz.digitalart.app.ui.MainActivity
import javax.inject.Inject


class WelcomeActivity : DaggerAppCompatActivity() {
    private val TAG = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: WelcomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(WelcomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = WelcomeViewModel()
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_welcome
        ) as ActivityWelcomeBinding
        binding.model = model

        drawer_layout.setOnClickListener {
            sp_lang.slideUp()
        }

        btn_start.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}