package com.example.notesapp.data

import androidx.room.*

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertCategory(category: Category): Long

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("select * from categories")
    suspend fun getAllCategories(): List<Category>

    @Transaction
    @Query("select * from categories")
    suspend fun getCategoriesWithNotes(): List<CategoryWithNotes>

}