package com.jitendraalekar.match.ui.dashboard

import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.data.model.User

sealed class DashboardViewState {
    object Loading : DashboardViewState()
    data class Content(val list: List<DashboardUser>) :
        DashboardViewState()

    data class Error(val throwable: Throwable) : DashboardViewState()
}

data class DashboardUser(
    val uuid: String,
    val name: String,
    val age: String,
    val thumbnail: String,
    val mediumPicture: String,
    val largePicture: String,
    val location: String,
    val actionStatus: ActionStatus
) {
    companion object {
        fun fromUser(user: User): DashboardUser {
            return DashboardUser(
                uuid = user.uuid,
                name = user.firstName + " " + user.lastName,
                age = user.age.toString(),
                thumbnail = user.thumbnailPicture,
                mediumPicture = user.mediumPicture,
                largePicture = user.largePicture,
                location = user.city + " " + user.country,
                actionStatus = user.actionStatus
            )
        }
    }
}

