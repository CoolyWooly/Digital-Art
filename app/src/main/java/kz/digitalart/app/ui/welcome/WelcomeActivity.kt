package kz.digitalart.app.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_welcome.*
import kz.digitalart.app.R
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.custom_views.languages.OnLangItemClickListener
import kz.digitalart.app.utils.updateResources

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity(), OnLangItemClickListener {

    private val TAG = this::class.java.simpleName

    private val viewModel: WelcomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        langItemClick(viewModel.prefsImpl.getLanguage())
        setContentView(R.layout.activity_welcome)

        drawer_layout.setOnClickListener {
            sp_lang.slideUp()
        }

        btn_start.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setButtonLang(viewModel.prefsImpl.getLanguage())

        sp_lang.setLanguage(viewModel.prefsImpl.getLanguage())

        sp_lang.onLangItemClickListener = this
    }

    override fun langItemClick(lang: String) {
        viewModel.prefsImpl.setLanguage(lang)
        updateResources(this, lang)
//        updateResources(this.applicationContext, lang)
        onConfigurationChanged(resources.configuration)

        if (btn_start != null) {
            setButtonLang(lang)
        }
    }

    private fun setButtonLang(lang: String) {
        when (lang) {
            "ru" -> btn_start.text = getString(R.string.start)
            "kk" -> btn_start.text = getString(R.string.start_kk)
            "en" -> btn_start.text = getString(R.string.start_en)
        }
    }
}