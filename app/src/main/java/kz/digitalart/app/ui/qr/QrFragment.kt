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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_qr.*
import kz.digitalart.app.R
import kz.digitalart.app.ui.MainActivity
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.core.ViewFinderView
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import javax.inject.Inject


class QrFragment : DaggerFragment(), ZBarScannerView.ResultHandler {

    private val TAG: String = this::class.java.simpleName
    private var mScannerView: ZBarScannerView? = null
    private val CAMERA_REQUEST_CODE = 0

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: QrViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(QrViewModel::class.java)
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
        (activity as MainActivity).setToolbarTitle(getString(R.string.nav_item_qr))
        Log.e(TAG, "onViewCreated")

        mScannerView = object : ZBarScannerView(context) {
            override fun createViewFinderView(context: Context): IViewFinder {
                return CustomViewFinderView(context)
            }
        }
        content_frame.addView(mScannerView)
        with(viewModel) {
            exhibitsData.observe(viewLifecycleOwner, Observer {
                val action = QrFragmentDirections.actionFragmentQrToFragmentHomeDetails()
                action.exhibit = it
                val navController = Navigation.findNavController(view)
                navController.navigate(action)
                mScannerView?.stopCamera()
            })
            error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, "${it?.error}", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun handleResult(rawResult: Result) {
        if (rawResult.contents.isDigitsOnly() && rawResult.contents.toIntOrNull() != null) {
            viewModel.getExhibit(rawResult.contents.toInt())
        } else {
            Toast.makeText(context, "Invalid code", Toast.LENGTH_SHORT).show()
        }


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
        val paint = Paint()

        constructor(context: Context) : super(context) {
            init()
        }

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            init()
        }

        private fun init() {
            paint.color = Color.WHITE
            paint.isAntiAlias = true
            val textPixelSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                TRADE_MARK_TEXT_SIZE_SP.toFloat(), resources.displayMetrics
            )
            paint.textSize = textPixelSize
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
                tradeMarkTop = framingRect.bottom.toFloat() + paint.textSize + 10f
                tradeMarkLeft = framingRect.left.toFloat()
            } else {
                tradeMarkTop = 10f
                tradeMarkLeft = canvas.height.toFloat() - paint.textSize - 10f
            }
            canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, paint)
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
                    builder.setTitle(getString(R.string.camera))
                    builder.setMessage(getString(R.string.camera_permission))
                    builder.setPositiveButton("OK") { dialog, which ->
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                } else {

                }
            }
        }
    }
}