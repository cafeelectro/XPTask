package com.ilidev.xptask.mvp.view

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilidev.xptask.adapter.RecyclerTaskAdapter
import com.ilidev.xptask.databinding.ActivityMainBinding
import com.ilidev.xptask.databinding.CustomDialogBinding
import com.ilidev.xptask.db.dao.TaskEntity
import com.ilidev.xptask.mvp.ext.OnBindData

class ViewMainActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {
    val binding =
        ActivityMainBinding.inflate(LayoutInflater.from(context))

    private lateinit var adapter: RecyclerTaskAdapter


    fun initRecycler(tasks: ArrayList<TaskEntity>, onBindData: OnBindData) {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = RecyclerTaskAdapter(tasks, onBindData)
        binding.recyclerView.adapter = adapter
    }

    fun showTask(tasks: ArrayList<TaskEntity>) {
        val data = arrayListOf<TaskEntity>()
        tasks.forEach { data.add(it) }
        adapter.dataUpdate(data)
    }

    fun setData(onBindData: OnBindData) {

        onBindData.requestData(false)
        binding.rdbTrue.setOnClickListener {
            onBindData.requestData(true)
        }

        binding.rdbFalse.setOnClickListener {
            onBindData.requestData(false)
        }

    }

    fun showDialog(onBindData: OnBindData) {
        binding.fab.setOnClickListener {
            val view = CustomDialogBinding.inflate(LayoutInflater.from(context))

            val dialog = Dialog(context)
            dialog.setContentView(view.root)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialog.show()

            view.btnSave.setOnClickListener {
                val text = view.edtTask.text.toString()
                if (text.isNotEmpty()) {
                    onBindData.saveData(TaskEntity(title = text, state = false))
                    Toast.makeText(context, "وظیفه شما ایجاد شد😁", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(context, "لطفا وظیفه را وارد نمایید", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}