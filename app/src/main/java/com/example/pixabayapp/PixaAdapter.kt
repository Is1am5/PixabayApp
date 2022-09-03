package com.example.pixabayapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pixabayapp.databinding.ItemImageBinding
import okhttp3.internal.immutableListOf

class PixaAdapter() : RecyclerView.Adapter<PixaAdapter.PixaViewHolder>() {

    private val list= mutableListOf()<ImageModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PixaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PixaViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel) {
            binding.imageView.load(imageModel.largeImageURL)
        }
    }

    fun setItems(newList: List<ImageModel>){
        list.addAll(newList)
        notifyDataSetChanged()
    }
}