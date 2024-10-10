package ir.younesmohammadi.mvp_learn.mvp.presenter

import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import ir.younesmohammadi.mvp_learn.mvp.ext.BaseLifecycle
import ir.younesmohammadi.mvp_learn.mvp.ext.OnBinData
import ir.younesmohammadi.mvp_learn.mvp.model.ModelTaskFragment
import ir.younesmohammadi.mvp_learn.mvp.view.ViewTaskFragment

class PresenterTaskFragment(
    val view: ViewTaskFragment,
    val model: ModelTaskFragment
) : BaseLifecycle {

    override fun onCreate() {

        initDataRecycler()
        dataHandler()
        editTask()

    }

    private fun initDataRecycler() {
        view.initRecycler(
            arrayListOf(),
            object : OnBinData {
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
            object : OnBinData {
                override fun requestData(state: Boolean) {
                    model.getTasks(
                        state,
                        object : OnBinData {
                            override fun getData(taskEntity: List<TaskEntity>) {
                                view.showTasks(taskEntity)
                            }
                        }
                    )
                }
            }
        )
    }

    private fun editTask() {
        view.editTask(object : OnBinData {
            override fun editData(taskEntity: TaskEntity) {
                model.editData(taskEntity)
            }
        })

    }

}