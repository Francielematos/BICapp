package com.bicapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bicapp.data.entity.QuestionnaireEntity
import com.bicapp.repository.QuestionnaireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.liveData

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuestionnaireRepository
) : ViewModel() {
    
    val incompleteQuestionnaire: LiveData<QuestionnaireEntity?> = liveData {
        emit(repository.getIncompleteQuestionnaire())
    }
    
    fun refreshIncompleteQuestionnaire() {
        viewModelScope.launch {
            // Trigger refresh by calling the repository again
        }
    }
}

