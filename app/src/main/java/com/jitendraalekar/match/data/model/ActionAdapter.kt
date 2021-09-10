package com.jitendraalekar.match.data.model

import androidx.room.TypeConverter

class ActionAdapter {
    @TypeConverter
    fun fromActionStatus(actionStatus: ActionStatus?): String? = actionStatus?.name

    @TypeConverter
    fun toActionStatus(statusString: String?): ActionStatus? =
        statusString?.let { ActionStatus.valueOf(statusString) }
}
