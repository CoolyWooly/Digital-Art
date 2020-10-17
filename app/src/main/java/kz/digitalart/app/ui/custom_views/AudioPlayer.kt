package kz.digitalart.app.ui.custom_views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.Animatable
import android.media.MediaPlayer
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import kotlinx.android.synthetic.main.view_audio_player.view.*
import kz.digitalart.app.R
import java.util.concurrent.TimeUnit

class AudioPlayer : LinearLayout, View.OnClickListener, MediaPlayer.OnCompletionListener,
    MediaPlayer.OnBufferingUpdateListener, SeekBar.OnSeekBarChangeListener {

    private var lengthOfAudio: Int = 0
    private var URL: String? = null
    private var mediaPlayer: MediaPlayer

    private val r = Runnable { updateSeekProgress() }

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    )
            : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    )
            : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        inflater?.inflate(R.layout.view_audio_player, this)
        btn_play.setOnClickListener(this)
        seekBar.setOnSeekBarChangeListener(this)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnBufferingUpdateListener(this)
        mediaPlayer.setOnCompletionListener(this)
    }

    var isPrepared = false
    override fun onClick(view: View?) {
        try {
            mediaPlayer.setDataSource(URL)
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            //Log.e("AudioPlayer", e.message)
        }

        mediaPlayer.setOnPreparedListener {
            isPrepared = true
            lengthOfAudio = it.duration
            playPause()
        }

        when (view?.id) {
            R.id.btn_play -> {
                if (isPrepared) playPause()
            }
        }
        updateSeekProgress()
    }

    private fun playPause() {
        if (mediaPlayer.isPlaying) {
            pauseAudio()
            setPlayIcon()
        } else {
            playAudio()
            setPauseIcon()
        }
    }

    override fun onCompletion(mp: MediaPlayer?) {
        btn_play.isEnabled = true
    }

    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
        seekBar.secondaryProgress = percent
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            mediaPlayer.seekTo(lengthOfAudio / 100 * seekBar!!.progress)
            updateSeekProgress()
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    private fun updateSeekProgress() {
        seekBar.progress =
            (mediaPlayer.currentPosition.toFloat() / lengthOfAudio * 100).toInt()
        time.text = convertMillis(lengthOfAudio)
        progress_time.text = convertMillis(mediaPlayer.currentPosition)
        handler?.postDelayed(r, 1000)
    }

    private fun pauseAudio() {
        mediaPlayer.pause()
    }

    private fun playAudio() {
        mediaPlayer.start()
    }

    private fun setPlayIcon() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn_play.background = resources.getDrawable(R.drawable.player_pause_to_play)
            val animation = btn_play.background as Animatable
            animation.start()
        } else {
            btn_play.background = resources.getDrawable(R.drawable.player_play)
        }
    }

    private fun setPauseIcon() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn_play.background = resources.getDrawable(R.drawable.player_play_to_pause)
            val animation = btn_play.background as Animatable
            animation.start()
        } else {
            btn_play.background = resources.getDrawable(R.drawable.player_pause)
        }
    }

    private fun convertMillis(millis: Int): String {
        return String.format(
            "%02d:%02d",
//            TimeUnit.MILLISECONDS.toHours(millis.toLong()),
            TimeUnit.MILLISECONDS.toMinutes(millis.toLong()) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    millis.toLong()
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(millis.toLong()) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    millis.toLong()
                )
            )
        )
    }

    fun setURL(string: String?) {
        URL = "https://www.oum.ru/upload/audio/17a/17adf29b51141486bb5adb503509dca3.mp3"
    }

    override fun onDetachedFromWindow() {
        pauseAudio()
        super.onDetachedFromWindow()
    }
}