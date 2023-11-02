package com.example.thriftpoint.di

import android.content.Context
import com.example.thriftpoint.data.datastore.DatastoreSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule{
    @Provides
    @Singleton
    fun provideDatastoreSource(
        @ApplicationContext context: Context
    ) = DatastoreSource(context)
}