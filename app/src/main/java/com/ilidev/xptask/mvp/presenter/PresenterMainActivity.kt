package com.ilidev.xptask.mvp.presenter

import com.ilidev.xptask.db.dao.TaskEntity
import com.ilidev.xptask.mvp.ext.BaseLifeCycle
import com.ilidev.xptask.mvp.ext.OnBindData
import com.ilidev.xptask.mvp.model.ModelMainActivity
import com.ilidev.xptask.mvp.view.ViewMainActivity


class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity,
) : BaseLifeCycle {
    override fun onCreate() {
        setNewTask()
        setDataInitTasks()
        dataHandler()
    }

    private fun setNewTask() {
        view.showDialog(
            object : OnBindData {
                override fun saveData(taskEntity: TaskEntity) {
                    model.setData(taskEntity)
                }
            }
        )
    }

    private fun setDataInitTasks() {
        view.initRecycler(
            arrayListOf(),
            object : OnBindData {
                override fun editData(taskEntity: TaskEntity) {
                    model.editData(taskEntity)
                }

                override fun deleteData(taskEntity: TaskEntity) {
                    model.deleteData(taskEntity)
                }
            }
        )
    }

    private fun dataHandler() {
        view.setData(
            object : OnBindData {
                override fun requestData(state: Boolean) {
                    model.getData(
                        state,
                        object : OnBindData {
                            override fun getData(taskEntity: List<TaskEntity>) {
                                view.showTask(taskEntity as ArrayList<TaskEntity>)
                            }
                        }
                    )
                }
            }
        )
    }

    override fun onDestroy() {
        model.closeDatabase()
    }

}