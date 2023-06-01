package com.example.hw2_4_viewpager2.note.NoteAdapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_4_viewpager2.UI.loadImageWithGlide
import com.example.hw2_4_viewpager2.activiti.App
import com.example.hw2_4_viewpager2.databinding.ItemRcViewBinding
import com.example.hw2_4_viewpager2.model.NoteModel

class NoteAdapter(private val context: Context) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var list = ArrayList<NoteModel>()
    private val PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<NoteModel>) {

        this.list = list
        notifyDataSetChanged()
    }

    fun deleteNote(position: Int) {
        App.note.getNoteDao().deleteNote(list.removeAt(position))
        notifyItemRemoved(position)
    }

    inner class NoteViewHolder(private val binding: ItemRcViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: NoteModel) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val uri = model.img.toUri()
                binding.itemImg.loadImageWithGlide(uri)
            } else {
                ActivityCompat.requestPermissions(
                    (context as Activity),
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_READ_EXTERNAL_STORAGE
                )
            }
            binding.itemTvNote.text = model.title
            binding.itemTvDate.text = model.date
            binding.itemTvDes.text = model.des
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemRcViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}
