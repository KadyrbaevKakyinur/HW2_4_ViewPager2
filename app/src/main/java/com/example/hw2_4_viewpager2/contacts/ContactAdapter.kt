package com.example.hw2_4_viewpager2.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_4_viewpager2.databinding.ItemContactsBinding

class ContactAdapter(private val call: (number: String)->Unit,private val chat:(number: String)->Unit ): ListAdapter<ContactModel,ContactAdapter.ContactViewHolder>(ContactDiffUtill()) {


    class ContactDiffUtill : DiffUtil.ItemCallback<ContactModel>() {
        override fun areItemsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
            return oldItem == newItem
        }


    }
   inner class ContactViewHolder(private val binding: ItemContactsBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: ContactModel) {
            binding.itemTvPhoneNumber.text = model.number
            binding.itemTvName.text = model.name

            binding.itemBtnCall.setOnClickListener {
                model?.number?.let { it1 -> call(it1) }
            }
            binding.itemBtnChat.setOnClickListener {
                model.number?.let(chat)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ContactViewHolder (
        ItemContactsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model)
    }
}