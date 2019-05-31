package kz.digitalart.app.ui.qr

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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
    private val title by lazy(LazyThreadSafetyMode.NONE) { arguments?.getInt("title") ?: 0 }
    private var mScannerView: ZBarScannerView? = null

    companion object {
        val FRAGMENT_NAME: String = QrFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: QrViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(QrViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_qr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).tv_toolbar.text = getString(title)

        mScannerView = object : ZBarScannerView(context) {
            override fun createViewFinderView(context: Context): IViewFinder {
                return CustomViewFinderView(context)
            }
        }
        content_frame.addView(mScannerView)
    }

    override fun handleResult(rawResult: Result) {
        Toast.makeText(
            context, "Contents = " +
                    ", Format = " + rawResult.barcodeFormat.toString(), Toast.LENGTH_SHORT
        ).show()

        val handler = Handler()
        handler.postDelayed(
            { mScannerView?.resumeCameraPreview(this) },
            2000
        )
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
            val TRADE_MARK_TEXT = ""
            val TRADE_MARK_TEXT_SIZE_SP = 40
        }
    }
}