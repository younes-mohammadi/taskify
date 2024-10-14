package ir.younesmohammadi.mvp_learn.androidWrapper

import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import ir.younesmohammadi.mvp_learn.mvp.ext.OnBinData

interface Utils {

    fun dialogEditTask(taskEntity: TaskEntity, onBinData: OnBinData) {}

    fun scrollRecycler() {}

}