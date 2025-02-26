package com.goryajnoff.cast

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManager
import com.google.android.gms.cast.framework.media.RemoteMediaClient
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private var castSession: CastSession? = null
    private lateinit var sessionManager: SessionManager
    private lateinit var button:Button
    private val videoUrl = "https://videolink-test.mycdn.me/?pct=1&sig=6QNOVp0y3BE&ct=0&clientType=45&mid=193241622673&type=5"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.castButton)

        val castExecutor = Executors.newSingleThreadExecutor()
        val castContext = CastContext.getSharedInstance(this, castExecutor).result
        sessionManager = castContext.sessionManager
        button.setOnClickListener {
            castSession = sessionManager.currentCastSession
            castSession?.let { session ->
                val mediaInfo = MediaInfo.Builder(videoUrl)
                    .setContentType("video/mp4")
                    .build()

                val mediaLoadRequestData = MediaLoadRequestData.Builder()
                    .setMediaInfo(mediaInfo)
                    .build()

                session.remoteMediaClient?.load(mediaLoadRequestData)
            }
        }






    }

}