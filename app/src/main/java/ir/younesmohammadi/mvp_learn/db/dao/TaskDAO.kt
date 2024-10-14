package ir.younesmohammadi.mvp_learn.db.dao

import androidx.room.*
import ir.younesmohammadi.mvp_learn.db.DBHandler
import ir.younesmohammadi.mvp_learn.db.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Insert
    fun insertTask(task: TaskEntity)

    @Query("SELECT * FROM ${DBHandler.TASK_TABLE} WHERE state = :type ORDER BY id DESC")
    fun getTasksByColumn(type: Boolean): Flow<List<TaskEntity>>

    @Update
    fun updateTask(task: TaskEntity)

    @Delete
    fun deleteTask(task: TaskEntity)

}