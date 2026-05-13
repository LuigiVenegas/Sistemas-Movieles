package com.example.focuswolf.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.focuswolf.data.entity.ProjectEntity

@Dao
interface ProjectDao {

    @Query("SELECT COUNT(*) FROM projects")
    suspend fun countProjects(): Int

    @Query("SELECT * FROM projects")
    suspend fun getAllProjects(): List<ProjectEntity>

    @Insert
    suspend fun insertProject(project: ProjectEntity): Long

    @Delete
    suspend fun deleteProject(project: ProjectEntity)
}