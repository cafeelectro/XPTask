package com.ilidev.xptask.mvp.ext

import com.ilidev.xptask.db.dao.TaskEntity

interface OnBindData {
    fun saveData(taskEntity: TaskEntity) {}
    fun editData(taskEntity: TaskEntity) {}
    fun deleteData(taskEntity: TaskEntity) {}
    fun getData(taskEntity: List<TaskEntity>) {}
    fun requestData(state: Boolean) {}

}