package com.ilidev.xptask.mvp.model

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ilidev.xptask.db.DBHandler
import com.ilidev.xptask.db.dao.TaskEntity
import com.ilidev.xptask.mvp.ext.OnBindData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelMainActivity(private val activity: AppCompatActivity) {
    val db = DBHandler.getDatabase(activity)
    fun setData(taskEntity: TaskEntity) {
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.taskDao().insertTask(taskEntity)
            }
        }
    }

    fun getTask(state:Boolean,onBindData: OnBindData){
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val tasks = db.taskDao().getTasksByColumn(state)
                withContext(Dispatchers.Main){
                    tasks.collect{
                        onBindData.getData(it)
                    }
                }
            }
        }
    }

    fun editData(taskEntity: TaskEntity) {
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.taskDao().updateTasks(taskEntity)
            }
        }
    }

    fun deleteData(taskEntity: TaskEntity) {
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.taskDao().deleteTasks(taskEntity)
            }
        }
    }

    fun getData(state:Boolean,onBindData: OnBindData){
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val tasks = db.taskDao().getTasksByColumn(state)
                withContext(Dispatchers.Main){
                    tasks.collect{
                        onBindData.getData(it)
                    }
                }
            }
        }
    }

    fun closeDatabase(){
        db.close()
    }
}