package ir.younesmohammadi.mvp_learn.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.younesmohammadi.mvp_learn.db.DBHandler

@Entity(tableName = DBHandler.TASK_TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val state: Boolean,
    @ColumnInfo val date: String,
    @ColumnInfo val importance: String
)
