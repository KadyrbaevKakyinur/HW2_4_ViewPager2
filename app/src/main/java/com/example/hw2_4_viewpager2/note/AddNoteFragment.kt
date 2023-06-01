package com.example.hw2_4_viewpager2.note

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hw2_4_viewpager2.activiti.App
import com.example.hw2_4_viewpager2.databinding.FragmentAddNoteBinding
import com.example.hw2_4_viewpager2.board.BaseFragment
import com.example.hw2_4_viewpager2.model.NoteModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate)  {

    private val REQUEST_CODE = 200
    //округления картинок
    private val radius = 50
    private val margin = 10

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setupUI() {
        getImg()
    }

    fun setAnim() {
        null
    }


    private fun getImg() {
        binding.imgView.setOnClickListener {
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            if (data?.clipData != null) {
                val count = data.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    loadImageFromUri(imageUri)
                }
            } else if (data?.data != null) {
                val imageUri = data.data!!
                loadImageFromUri(imageUri)
            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadImageFromUri(uri: Uri) {
        Glide.with(requireContext())
            .load(uri)
            .centerCrop()
            .transform(RoundedCorners(radius))
            .transform(RoundedCorners(margin))
            .into(binding.imgView)

        binding.saveBtn.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val des = binding.editDes.text.toString()

            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val currentTime = LocalDateTime.now()
            val date = formatter.format(currentTime)

            val noteModel = NoteModel(
                title = title,
                des = des,
                date = date,
                img = uri.toString()
            )
            App.note.getNoteDao().addNote(noteModel)
            findNavController().navigateUp()
        }

    }


}