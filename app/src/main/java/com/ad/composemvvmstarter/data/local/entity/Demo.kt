package com.ad.composemvvmstarter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ad.composemvvmstarter.utility.AppConstants

@Entity(tableName = AppConstants.TABLE_DEMO)
data class Demo(
    @PrimaryKey
    val id: Int?,
    val name: String?
)