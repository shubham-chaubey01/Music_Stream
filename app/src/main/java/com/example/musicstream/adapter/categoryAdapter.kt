package com.example.musicstream.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicstream.SongsListActivity
import com.example.musicstream.databinding.CategoryItemRecyclerRowBinding
import com.example.musicstream.models.categorymodel

class CategoryAdapter (private val catgoryList: List<categorymodel>):
    RecyclerView.Adapter<CategoryAdapter.MyviewHolder>() {

    class MyviewHolder(private val binding : CategoryItemRecyclerRowBinding ) :RecyclerView.ViewHolder( binding.root) {
        // binds the data with views
        fun bindData(category: categorymodel) {
            binding.nameTextView.text=category.name
            Glide.with(binding.coverImageView).load(category.coverUrl)
                .apply(
                    RequestOptions().transform(RoundedCorners(32))
                )
                .into(binding.coverImageView)

            //start SongsList Activity
            val context =binding.root.context
            binding.root.setOnClickListener{
                SongsListActivity.category = category
                context.startActivities(arrayOf(Intent(context,SongsListActivity::class.java)))
            }


        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
       val binding =CategoryItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyviewHolder(binding)
    }

    override fun getItemCount(): Int {
       return catgoryList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
       holder.bindData(catgoryList[position])
    }
}
