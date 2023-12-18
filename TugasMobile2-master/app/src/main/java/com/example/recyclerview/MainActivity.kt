package com.example.recyclerview


import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null


    //}
//
//
//class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val list = ArrayList<video>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_video)
        recyclerView.setHasFixedSize(true)
        list.addAll(getList())
        showRecyclerList()
    }

    private fun getList(): ArrayList<video> {
        val gambar = resources.obtainTypedArray(R.array.data_gambar)
        val dataName = resources.getStringArray(R.array.judul_video)
        val dataDesripsi = resources.getStringArray(R.array.data_dekripsi)
        val videoId = resources.obtainTypedArray(R.array.video_id)

        val listvideo = ArrayList<video>()
        for (i in dataName.indices) {
            val video = video(
                gambar.getResourceId(i, -1),
                dataName[i],
                dataDesripsi[i],
                videoId.getResourceId(i, -1)
            )
            listvideo.add(video)
        }

        return listvideo


        setContentView(R.layout.activity_main)

        // Inisialisasi MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.sound)

        // Mulai pemutaran
        mediaPlayer!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Pastikan untuk melepaskan sumber daya MediaPlayer setelah selesai
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }


    private fun showRecyclerList() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listadapter = ListAdapter(list)
        recyclerView.adapter = listadapter
    }
}
