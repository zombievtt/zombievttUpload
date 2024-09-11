package com.example.design

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.design.databinding.RowWordBinding

class WordAdapter (var words : List<Word>) : RecyclerView.Adapter<WordAdapter.WordViewHolder>(){

    class WordViewHolder(val view : RowWordBinding): RecyclerView.ViewHolder(view.root) {
        init {
            val nameText = view.name
            val diffText = view.different
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = RowWordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return WordViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words.get(position)
        holder.view.name.text = word.name
        holder.view.different.text = word.difficulty.toString()
    }
}