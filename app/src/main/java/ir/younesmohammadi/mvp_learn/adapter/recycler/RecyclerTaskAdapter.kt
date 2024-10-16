package ir.younesmohammadi.mvp_learn.adapter.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.younesmohammadi.mvp_learn.adapter.recycler.utils.RecyclerDiffUtils
import ir.younesmohammadi.mvp_learn.androidWrapper.ColorImportance
import ir.younesmohammadi.mvp_learn.androidWrapper.PersianDate
import ir.younesmohammadi.mvp_learn.androidWrapper.Utils
import ir.younesmohammadi.mvp_learn.databinding.RecyclerItemBinding
import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import ir.younesmohammadi.mvp_learn.mvp.ext.OnBinData

class RecyclerTaskAdapter(
    private val tasks: ArrayList<TaskEntity>,
    private val onBinData: OnBinData,
    private val utils: Utils
) : RecyclerView.Adapter<RecyclerTaskAdapter.TaskViewHolder>() {

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

    inner class TaskViewHolder(
        private val binding: RecyclerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: TaskEntity) {

            binding.txtTitle.text = data.title
            binding.checkbox.isChecked = data.state
            binding.txtDate.text = data.date
            binding.txtImportance.text = data.importance

            when (data.importance) {
                ColorImportance.Low -> binding.cardImportance.setCardBackgroundColor(Color.parseColor("#D3D3D3"))
                ColorImportance.Normal -> binding.cardImportance.setCardBackgroundColor(Color.parseColor("#FF9800"))
                ColorImportance.High -> binding.cardImportance.setCardBackgroundColor(Color.parseColor("#FF5252"))
            }


            binding.checkbox.setOnClickListener {
                if (data.state) {
                    onBinData.editData(
                        TaskEntity(
                            id = data.id,
                            title = data.title,
                            state = false,
                            date = PersianDate.getDate(),
                            importance = data.importance
                        )
                    )
                } else {
                    onBinData.editData(
                        TaskEntity(
                            id = data.id,
                            title = data.title,
                            state = true,
                            date = PersianDate.getDate(),
                            importance = data.importance
                        )
                    )
                }
            }
            binding.imgDelete.setOnClickListener {
                onBinData.deleteData(
                    TaskEntity(
                        id = data.id,
                        title = data.title,
                        state = data.state,
                        date = data.date,
                        importance = data.importance
                    )
                )
            }
            binding.imgEdit.setOnClickListener {
                utils.dialogEditTask(data, onBinData)
            }

        }

    }

    fun updateData(newList: ArrayList<TaskEntity>) {

        val diffCallback = RecyclerDiffUtils(tasks, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        tasks.clear()
        tasks.addAll(newList)

        diffResult.dispatchUpdatesTo(this)

        utils.scrollRecycler()

    }

}