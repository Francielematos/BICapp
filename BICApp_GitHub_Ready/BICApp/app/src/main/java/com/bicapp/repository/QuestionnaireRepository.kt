package com.bicapp.repository

import androidx.lifecycle.LiveData
import com.bicapp.data.dao.QuestionnaireDao
import com.bicapp.data.entity.QuestionnaireEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionnaireRepository @Inject constructor(
    private val questionnaireDao: QuestionnaireDao
) {
    
    fun getAllQuestionnaires(): LiveData<List<QuestionnaireEntity>> {
        return questionnaireDao.getAllQuestionnaires()
    }
    
    suspend fun getQuestionnaireById(id: Long): QuestionnaireEntity? {
        return questionnaireDao.getQuestionnaireById(id)
    }
    
    suspend fun getIncompleteQuestionnaire(): QuestionnaireEntity? {
        return questionnaireDao.getIncompleteQuestionnaire()
    }
    
    suspend fun insertQuestionnaire(questionnaire: QuestionnaireEntity): Long {
        return questionnaireDao.insertQuestionnaire(questionnaire)
    }
    
    suspend fun updateQuestionnaire(questionnaire: QuestionnaireEntity) {
        questionnaireDao.updateQuestionnaire(questionnaire)
    }
    
    suspend fun deleteQuestionnaire(questionnaire: QuestionnaireEntity) {
        questionnaireDao.deleteQuestionnaire(questionnaire)
    }
    
    suspend fun deleteQuestionnaireById(id: Long) {
        questionnaireDao.deleteQuestionnaireById(id)
    }
}

