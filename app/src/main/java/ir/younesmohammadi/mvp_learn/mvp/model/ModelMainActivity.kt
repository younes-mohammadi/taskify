package ir.younesmohammadi.mvp_learn.mvp.model

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ir.younesmohammadi.mvp_learn.db.DBHandler
import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ModelMainActivity(private val activity: AppCompatActivity) {

    private val db = DBHandler.getDatabase(activity)

    fun saveData(taskEntity: TaskEntity) {
        activity.lifecycleScope.launch(IO) {
            db.taskDao().insertTask(taskEntity)
        }
    }

}