package com.example.week6exercise.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.week6exercise.R
import com.example.week6exercise.databinding.DogListItemBinding
import com.example.week6exercise.model.Dogs

class DogListAdapter(val dogList:ArrayList<Dogs>): RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {
    class DogViewHolder(var view: DogListItemBinding)
        : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<DogListItemBinding>(inflater,
            R.layout.dog_list_item, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dogs = dogList[position]
    }
    fun updateStudentList(newStudentList: ArrayList<Dogs>) {
        dogList.clear()
        dogList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}