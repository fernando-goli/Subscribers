package com.fgomes.subscribers.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fgomes.subscribers.data.db.dao.SubscriberDao
import com.fgomes.subscribers.data.db.entity.SubscriberEntity

@Database(entities = [SubscriberEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun subscriberDao(): SubscriberDao

}