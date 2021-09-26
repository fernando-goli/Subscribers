package com.fgomes.subscribers.di

import androidx.room.Room
import com.fgomes.subscribers.data.db.AppDatabase
import com.fgomes.subscribers.repository.DatabaseDataSource
import com.fgomes.subscribers.repository.SubscriberRepository
import com.fgomes.subscribers.ui.subscriber.SubscriberViewModel
import com.fgomes.subscribers.ui.subscriberlist.SubscriberListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SubscriberViewModel(repository = get() ) }
    viewModel { SubscriberListViewModel( repository = get()) }
}

val repositoryModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single  {
        get<AppDatabase>().subscriberDao()
    }

    single <SubscriberRepository>{ DatabaseDataSource(get() )  }


}



