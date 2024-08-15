package com.ilidev.xptask.db.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow
import androidx.room.Query
import androidx.room.Update
import com.ilidev.xptask.db.DBHandler
import com.ilidev.xptask.db.dao.TaskEntity

@Dao
interface TaskDAO {

    @Insert
    fun insertTask(vararg task: TaskEntity)

    @get:Query("SELECT * FROM ${DBHandler.TASK_TABLE}")
    val getTasks: Flow<List<TaskEntity>>

    @Query("SELECT * FROM ${DBHandler.TASK_TABLE} WHERE state = :type")
    fun getTasksByColumn(type: Boolean): Flow<List<TaskEntity>>

    @Update
    fun updateTasks(vararg tasks: TaskEntity): Int

    @Delete
    fun deleteTasks(vararg tasks: TaskEntity): Int

    @Query("DELETE FROM ${DBHandler.TASK_TABLE}")
    fun deleteAllTasks()

}