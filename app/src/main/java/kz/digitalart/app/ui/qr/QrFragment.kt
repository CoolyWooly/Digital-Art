package kz.digitalart.app.ui.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kz.digitalart.app.R
import kz.digitalart.app.ui.MainActivity
import me.dm7.barcodescanner.zbar.Result
import javax.inject.Inject
import android.util.Pair as UtilPair
import me.dm7.barcodescanner.zbar.ZBarScannerView


class QrFragment : DaggerFragment(), ZBarScannerView.ResultHandler {

    private val TAG: String = QrFragment::class.java.simpleName
    private val title by lazy(LazyThreadSafetyMode.NONE) { arguments?.getInt("title") ?: 0 }
    private var mScannerView: ZBarScannerView? = null

    companion object {
        val FRAGMENT_NAME: String = QrFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: QrViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(QrViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mScannerView = ZBarScannerView(context)

        return mScannerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).tv_toolbar.text = getString(title)
    }

    override fun handleResult(rawResult: Result?) {
        // If you would like to resume scanning, call this method below:
        mScannerView?.resumeCameraPreview(this)
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()           // Stop camera on pause
    }
}