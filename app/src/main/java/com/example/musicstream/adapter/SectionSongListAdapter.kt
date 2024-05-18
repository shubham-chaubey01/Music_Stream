package com.example.musicstream.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicstream.MyExoplayer
import com.example.musicstream.PlayerActivity
import com.example.musicstream.databinding.SectionSongListRecyclerRowBinding
import com.example.musicstream.models.Songmodel
import com.google.firebase.firestore.FirebaseFirestore

class SectionSongListAdapter (private val songIDList :List<String>) :RecyclerView.Adapter<SectionSongListAdapter.MyViewHolder>(){

    class MyViewHolder(private val binding: SectionSongListRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
        //bind data with view
        fun bindData(songID : String){

            FirebaseFirestore.getInstance().collection("Songs")
                .document(songID).get()
                .addOnSuccessListener {
                    val song = it.toObject(Songmodel::class.java)
                    song?.apply {
                        binding.songTitleTextView.text = this.title
                        binding.songSubtitleTextView.text = subtitle
                        Glide.with(binding.songCaveImageView).load(coverUrl)
                            .apply(
                                RequestOptions().transform(RoundedCorners(32))
                            )
                            .into(binding.songCaveImageView)
                        binding.root.setOnClickListener {
                            MyExoplayer.startPlaying(binding.root.context,song)
                            it.context.startActivity(Intent(it.context,PlayerActivity::class.java))
                        }


                    }


                }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SectionSongListRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)    }

    override fun getItemCount(): Int {
        return songIDList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(songIDList[position])
    }
}

private fun Any.startActivity(context: Context?, java: Class<PlayerActivity>) {
    TODO("Not yet implemented")
}




