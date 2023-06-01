package com.example.hw2_4_viewpager2.note

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

    @Query("select * from notemodel order by date asc")
    fun getSortedByDate(): List<NoteModel>

    @Query("select * from notemodel order by title asc")
    fun getSortedByTitle(): List<NoteModel>


}