package com.br.mltest.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultBodyResponse (
    @Json(name = "results")
    val itemResults: List<ItemResultsResponse>
)