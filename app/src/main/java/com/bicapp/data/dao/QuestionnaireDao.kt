package com.bicapp.data.dao

import androidx.room.*
import androidx.lifecycle.LiveData
import com.bicapp.data.entity.QuestionnaireEntity

@Dao
interface QuestionnaireDao {
    
    @Query("SELECT * FROM questionnaires ORDER BY dataPreenchimento DESC")
    fun getAllQuestionnaires(): LiveData<List<QuestionnaireEntity>>
    
    @Query("SELECT * FROM questionnaires WHERE id = :id")
    suspend fun getQuestionnaireById(id: Long): QuestionnaireEntity?
    
    @Query("SELECT * FROM questionnaires WHERE isCompleted = 0 ORDER BY dataPreenchimento DESC LIMIT 1")
    suspend fun getIncompleteQuestionnaire(): QuestionnaireEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionnaire(questionnaire: QuestionnaireEntity): Long
    
    @Update
    suspend fun updateQuestionnaire(questionnaire: QuestionnaireEntity)
    
    @Delete
    suspend fun deleteQuestionnaire(questionnaire: QuestionnaireEntity)
    
    @Query("DELETE FROM questionnaires WHERE id = :id")
    suspend fun deleteQuestionnaireById(id: Long)
}

