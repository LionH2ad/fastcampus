package fastcampus.part1.chapter9

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MediaPlayerService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    //private val receiver = LowBatteryReceiver()

    // BindService 를 진행 할 때 사용 사용하지 않을 것이라 null을 리턴
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    // Service 가 OnCreate 되고 나서 불리게 되는 것
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            MEDIA_PLAYER_PLAY -> {
                if(mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(baseContext, R.raw.catch_me)
                }
                mediaPlayer?.start()
            }
            MEDIA_PLAYER_PAUSE -> {
                mediaPlayer?.pause()
            }
            MEDIA_PLAYER_STOP -> {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
                stopSelf() // Service exit
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}