package com.example.recyclerview

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter (private val videolist:List<video>):RecyclerView.Adapter<ListAdapter.ListViewHolder>(){
    private var mediaPlayer: MediaPlayer? = null
    class ListViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val gambar:ImageView=itemview.findViewById(R.id.img_view)
        val judulvideo:TextView=itemview.findViewById(R.id.tv_judul_video)
        val deskripsi:TextView=itemview.findViewById(R.id.tv_description)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (gambar, judul, deskripsi) = videolist[position]
        holder.gambar.setImageResource(gambar)
        holder.judulvideo.text = judul
        holder.deskripsi.text = deskripsi
        holder.itemView.setOnClickListener {
            // Panggil metode untuk memainkan audio
            playAudio(holder.itemView.context)

            // Tambahkan kode untuk navigasi ke DetailActivity jika diperlukan
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("shadow", videolist[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    private fun playAudio(context: Context) {
        // Cek apakah media sedang diputar, jika iya hentikan
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }

        // Inisialisasi MediaPlayer dengan audio dari res/raw
        mediaPlayer = MediaPlayer.create(context, R.raw.sound)

        // Mulai pemutaran
        mediaPlayer?.start()

    }
    fun onDestroy() {

        // Pastikan untuk melepaskan sumber daya MediaPlayer setelah selesai
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }



    override fun getItemCount(): Int = videolist.size
}