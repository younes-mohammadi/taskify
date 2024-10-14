package ir.younesmohammadi.mvp_learn.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.younesmohammadi.mvp_learn.adapter.recycler.RecyclerTaskAdapter
import ir.younesmohammadi.mvp_learn.androidWrapper.ColorImportance
import ir.younesmohammadi.mvp_learn.androidWrapper.DialogUtils
import ir.younesmohammadi.mvp_learn.androidWrapper.PersianDate
import ir.younesmohammadi.mvp_learn.androidWrapper.SnackBarUtils
import ir.younesmohammadi.mvp_learn.androidWrapper.Utils
import ir.younesmohammadi.mvp_learn.databinding.CustomDialogBinding
import ir.younesmohammadi.mvp_learn.databinding.FragmentTaskBinding
import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import ir.younesmohammadi.mvp_learn.mvp.ext.OnBinData

@SuppressLint("ViewConstructor")
class ViewTaskFragment(
    contextInstance: Context,
    val state: Boolean
) : FrameLayout(contextInstance), Utils {

    val binding = FragmentTaskBinding.inflate(LayoutInflater.from(contextInstance))

    private lateinit var adapter: RecyclerTaskAdapter

    fun initRecycler(taskEntity: ArrayList<TaskEntity>, onBinData: OnBinData) {

        adapter = RecyclerTaskAdapter(taskEntity, onBinData, this)
        binding.recyclerTasks.layoutManager =
            LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        binding.recyclerTasks.adapter = adapter

    }

    fun setData(onBinData: OnBinData) {

        onBinData.requestData(state)

    }

    fun showTasks(tasks: List<TaskEntity>) {

        val data = arrayListOf<TaskEntity>()
        tasks.forEach { data.add(it) }

        if (data.size < 1) {
            binding.groupEmpty.visibility = View.VISIBLE
        } else {
            binding.groupEmpty.visibility = View.GONE
        }

        adapter.updateData(data)

    }

    override fun dialogEditTask(taskEntity: TaskEntity, onBinData: OnBinData) {

        val view = CustomDialogBinding.inflate(LayoutInflater.from(context))

        DialogUtils.show(context, view.root, false)

        view.txtTitle.text = "ویرایش وظیفه"
        view.btnSave.text = "ویرایش"
        view.layoutTask.hint = "وظیفه:"

        when (taskEntity.importance) {
            ColorImportance.Low -> view.radioLow.isChecked = true
            ColorImportance.Normal -> view.radioMedium.isChecked = true
            ColorImportance.High -> view.radioHigh.isChecked = true
        }

        val edit = Editable.Factory()
        view.edtTask.text = edit.newEditable(taskEntity.title)

        view.btnCancel.setOnClickListener { DialogUtils.dismiss() }

        view.btnSave.setOnClickListener {
            val task = view.edtTask.text.toString().trim()
            val viewError = view.layoutTask

            // Prioritization Task
            val selectedPriority = when (view.radioGroup.checkedRadioButtonId) {
                view.radioLow.id -> ColorImportance.Low
                view.radioMedium.id -> ColorImportance.Normal
                view.radioHigh.id -> ColorImportance.High
                else -> ColorImportance.Low
            }

            // save data
            if (task.isNotBlank() && selectedPriority.isNotEmpty()) {

                onBinData.editData(
                    TaskEntity(
                        id = taskEntity.id,
                        title = task,
                        state = taskEntity.state,
                        date = PersianDate.getDate(),
                        importance = selectedPriority
                    )
                )

                editTask(onBinData)

                SnackBarUtils.show(binding.root, "وظیفه با موفقیت ویرایش شد.")
                DialogUtils.dismiss()
            } else {
                viewError.error = "لطفا وظیفه را وارد کنید."
            }

            view.edtTask.addTextChangedListener {
                viewError.error = null
            }

        }

    }

    fun editTask(onBinData: OnBinData): OnBinData {
        return onBinData
    }

    override fun scrollRecycler() {
        binding.recyclerTasks.scrollToPosition(0)
    }

}