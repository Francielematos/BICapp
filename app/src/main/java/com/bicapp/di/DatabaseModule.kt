package com.bicapp.di

import android.content.Context
import androidx.room.Room
import com.bicapp.data.BICDatabase
import com.bicapp.data.dao.QuestionnaireDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BICDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BICDatabase::class.java,
            "bic_database"
        ).build()
    }
    
    @Provides
    fun provideQuestionnaireDao(database: BICDatabase): QuestionnaireDao {
        return database.questionnaireDao()
    }
}

