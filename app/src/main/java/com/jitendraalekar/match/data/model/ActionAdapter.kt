package com.jitendraalekar.match.data.model

import androidx.room.TypeConverter
import java.time.Instant

class ActionAdapter {
    @TypeConverter
    fun fromActionStatus(actionStatus: ActionStatus): String = actionStatus.name

    @TypeConverter
    fun toActionStatus(statusString: String): ActionStatus =
        ActionStatus.valueOf(statusString)
}
