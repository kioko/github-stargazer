package com.thomaskioko.stargazer.repository.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "login")val login: String
)