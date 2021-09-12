package com.fgomes.subscribers.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_subscriber")
data class SubscriberEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val email: String
    ) : Parcelable