package ir.younesmohammadi.mvp_learn.mvp.model

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import ir.younesmohammadi.mvp_learn.db.DBHandler
import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import ir.younesmohammadi.mvp_learn.mvp.ext.OnBinData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelTaskFragment(private val fragment: Fragment) {

    private var db = DBHandler.getDatabase(fragment.requireContext())

    fun editData(taskEntity: TaskEntity) {
        fragment.lifecycleScope.launch(IO) {
            db.taskDao().updateTask(taskEntity)
        }
    }

    fun getTasks(
        state: Boolean,
        onBinData: OnBinData
    ) {
        fragment.lifecycleScope.launch(IO) {
            val tasks = db.taskDao().getTasksByColumn(state)
            withContext(Main) {
                tasks.collect {
                    onBinData.getData(it)
                }
            }
        }
    }

    fun deleteData(taskEntity: TaskEntity) {
        fragment.lifecycleScope.launch(IO) {
            db.taskDao().deleteTask(taskEntity)
        }
    }

}