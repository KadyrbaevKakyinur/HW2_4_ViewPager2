package com.example.hw2_4_viewpager2.fragment.note

import androidx.room.*
import com.example.hw2_4_viewpager2.model.NoteModel

@Dao
interface NoteDao {
    @Query("select * from notemodel")
    fun getAllNote(): List<NoteModel>

    @Insert
    fun addNote(model:NoteModel)

    @Update
    fun updateNote(model: NoteModel)

    @Delete
    fun deleteNote(model:NoteModel)
}