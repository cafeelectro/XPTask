package com.ilidev.xptask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ilidev.xptask.databinding.RecyclerItemBinding
import com.ilidev.xptask.db.dao.TaskEntity
import com.ilidev.xptask.mvp.ext.OnBindData

class RecyclerTaskAdapter(
    private val tasks: ArrayList<TaskEntity>,
    private val onBindData: OnBindData
) : RecyclerView.Adapter<RecyclerTaskAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(
        private val binding: RecyclerItemBinding
    ) : ViewHolder(binding.root) {


        fun setData(data: TaskEntity) {
            binding.txtTitle.text = data.title
            binding.checkBox.isChecked = data.state
            binding.checkBox.setOnClickListener {
                if (binding.checkBox.isChecked) {
                    onBindData.editData(TaskEntity(data.id, data.title, true))
                } else {
                    onBindData.editData(TaskEntity(data.id, data.title, false))
                }
                binding.imgDelete.setOnClickListener {
                    onBindData.deleteData(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TaskViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.setData(tasks[position])
    }

    fun dataUpdate(newList: ArrayList<TaskEntity>) {

        val diffCallBack = RecyclerDiffUtils(tasks, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        tasks.clear()
        tasks.addAll(newList)
        diffResult.dispatchUpdatesTo(this)


    }
}