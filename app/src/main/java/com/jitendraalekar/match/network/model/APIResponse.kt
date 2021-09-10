package com.jitendraalekar.match.network.model

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("results")
    val results: List<NetworkUser>
)
