package kz.digitalart.app.ui.qr

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_qr.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kz.digitalart.app.R
import kz.digitalart.app.ui.MainActivity
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.core.ViewFinderView
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import javax.inject.Inject
import android.util.Pair as UtilPair


class QrFragment : DaggerFragment(), ZBarScannerView.ResultHandler {

    private val TAG: String = QrFragment::class.java.simpleName
    private var mScannerView: ZBarScannerView? = null
    private val CAMERA_REQUEST_CODE = 0

    companion object {
        val FRAGMENT_NAME: String = QrFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: QrViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(QrViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(TAG, "onCreateView")
        setupPermissions()

        return inflater.inflate(R.layout.fragment_qr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).tv_toolbar.text = getString(R.string.nav_item_qr)
        Log.e(TAG, "onViewCreated")

        mScannerView = object : ZBarScannerView(context) {
            override fun createViewFinderView(context: Context): IViewFinder {
                return CustomViewFinderView(context)
            }
        }
        content_frame.addView(mScannerView)

        with(viewModel) {
            exhibitsData.observe(this@QrFragment, Observer {
                val action = QrFragmentDirections.actionFragmentQrToFragmentLikedDetails()
                action.exhibit = it
                val navController = Navigation.findNavController(view)
                navController.navigate(action)
                mScannerView?.stopCamera()
            })
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(context!!,
            Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    override fun handleResult(rawResult: Result) {
        viewModel.getExhibit(rawResult.contents.toInt())


        val handler = Handler()
        handler.postDelayed(
            { mScannerView?.resumeCameraPreview(this) },
            2000
        )
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
        mScannerView?.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()           // Stop camera on pause
        Log.e(TAG, "onPause")
    }

    private class CustomViewFinderView : ViewFinderView {
        val PAINT = Paint()

        constructor(context: Context) : super(context) {
            init()
        }

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            init()
        }

        private fun init() {
            PAINT.color = Color.WHITE
            PAINT.isAntiAlias = true
            val textPixelSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                TRADE_MARK_TEXT_SIZE_SP.toFloat(), resources.displayMetrics
            )
            PAINT.textSize = textPixelSize
            setSquareViewFinder(true)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            drawTradeMark(canvas)
        }

        private fun drawTradeMark(canvas: Canvas) {
            val framingRect = framingRect
            val tradeMarkTop: Float
            val tradeMarkLeft: Float
            if (framingRect != null) {
                tradeMarkTop = framingRect.bottom.toFloat() + PAINT.textSize + 10f
                tradeMarkLeft = framingRect.left.toFloat()
            } else {
                tradeMarkTop = 10f
                tradeMarkLeft = canvas.height.toFloat() - PAINT.textSize - 10f
            }
            canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, PAINT)
        }

        companion object {
            const val TRADE_MARK_TEXT = ""
            const val TRADE_MARK_TEXT_SIZE_SP = 40
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    val builder = AlertDialog.Builder(context!!, R.style.MyAlertDialogStyle)
                    builder.setTitle("Камера")
                    builder.setMessage("Вкл разрешение")
                    builder.setPositiveButton("YES"){dialog, which ->
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                } else {

                }
            }
        }
    }
}