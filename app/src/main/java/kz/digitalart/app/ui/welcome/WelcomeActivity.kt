package kz.digitalart.app.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import kz.digitalart.app.R
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.custom_views.languages.OnLangItemClickListener
import kz.digitalart.app.utils.updateResources
import javax.inject.Inject


class WelcomeActivity : DaggerAppCompatActivity(), OnLangItemClickListener {

    private val TAG = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: WelcomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(WelcomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val model = WelcomeViewModel()
//        val binding = DataBindingUtil.setContentView<ViewDataBinding>(
//            this,
//            R.layout.activity_welcome
//        ) as ActivityWelcomeBinding
//        binding.model = model
        setContentView(R.layout.activity_welcome)

        drawer_layout.setOnClickListener {
            sp_lang.slideUp()
        }

        btn_start.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        sp_lang.setLanguage(viewModel.prefsImpl.getLanguage())

        sp_lang.onLangItemClickListener = this
    }

    override fun langItemClick(lang: String) {
        updateResources(this, lang)
        viewModel.prefsImpl.setLanguage(lang)
    }
}