package ir.younesmohammadi.mvp_learn.mvp.ext

import ir.younesmohammadi.mvp_learn.db.model.TaskEntity

interface OnBinData {

    fun saveData(taskEntity: TaskEntity) {}

    fun editData(taskEntity: TaskEntity) {}

    fun getData(taskEntity: List<TaskEntity>) {}

    fun requestData(state: Boolean) {}

    fun deleteData(taskEntity: TaskEntity) {}

}