package com.ds.norris.model

import com.google.gson.annotations.SerializedName

data class NorrisList(
    @SerializedName("total") val total: String,
    @SerializedName("result") val result: List<Norris>
)