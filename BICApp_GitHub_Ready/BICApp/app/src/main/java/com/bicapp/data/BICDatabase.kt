package com.bicapp.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.bicapp.data.dao.QuestionnaireDao
import com.bicapp.data.entity.QuestionnaireEntity
import com.bicapp.data.converter.Converters

@Database(
    entities = [QuestionnaireEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BICDatabase : RoomDatabase() {
    
    abstract fun questionnaireDao(): QuestionnaireDao
    
    companion object {
        @Volatile
        private var INSTANCE: BICDatabase? = null
        
        fun getDatabase(context: Context): BICDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BICDatabase::class.java,
                    "bic_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

