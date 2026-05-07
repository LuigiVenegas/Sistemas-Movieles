package com.example.focuswolf.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.focuswolf.data.entity.ProjectEntity

@Dao
interface ProjectDao {

    @Query("SELECT * FROM projects")
    suspend fun getAllProjects(): List<ProjectEntity>

    @Insert
    suspend fun insertProject(project: ProjectEntity)

    @Update
    suspend fun updateProject(project: ProjectEntity)

    @Delete
    suspend fun deleteProject(project: ProjectEntity)
}