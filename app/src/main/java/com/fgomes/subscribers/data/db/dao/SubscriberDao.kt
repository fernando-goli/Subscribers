package com.fgomes.subscribers.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fgomes.subscribers.data.db.entity.SubscriberEntity

@Dao
interface SubscriberDao {

    @Insert
    suspend fun insert(subscriber: SubscriberEntity): Long

    @Update
    suspend fun update(subscriber: SubscriberEntity)

    @Query("DELETE FROM table_subscriber WHERE id =:id")
    suspend fun delete (id: Long)

    @Query("DELETE FROM table_subscriber")
    suspend fun deleteAll()

    @Query("SELECT * FROM  table_subscriber")
    fun getAll(): LiveData<List<SubscriberEntity>>
}
