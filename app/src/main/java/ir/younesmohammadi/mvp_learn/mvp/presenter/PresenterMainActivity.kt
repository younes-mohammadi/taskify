package ir.younesmohammadi.mvp_learn.mvp.presenter

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import ir.younesmohammadi.mvp_learn.mvp.ext.BaseLifecycle
import ir.younesmohammadi.mvp_learn.mvp.ext.OnBinData
import ir.younesmohammadi.mvp_learn.mvp.model.ModelMainActivity
import ir.younesmohammadi.mvp_learn.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : BaseLifecycle {

    override fun onCreate() {
        setNewTask()
        infoDev()
    }

    private fun setNewTask() {
        view.showDialogAddTask(
            object : OnBinData {
                override fun saveData(taskEntity: TaskEntity) {
                    model.saveData(taskEntity)
                }
            }
        )
    }

    fun initTabLayout(fragmentManager: FragmentManager, lifecycle: Lifecycle) {

        view.setTabLayout(fragmentManager, lifecycle)

    }

    private fun infoDev(){
        view.showDialogDev()
    }

}