package com.bicapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bicapp.databinding.ActivityMainBinding
import com.bicapp.ui.questionnaire.QuestionnaireActivity
import com.bicapp.ui.history.ReportHistoryActivity
import com.bicapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        
        setupUI()
        observeViewModel()
    }
    
    private fun setupUI() {
        binding.btnStartQuestionnaire.setOnClickListener {
            startActivity(Intent(this, QuestionnaireActivity::class.java))
        }
        
        binding.btnReportHistory.setOnClickListener {
            startActivity(Intent(this, ReportHistoryActivity::class.java))
        }
    }
    
    private fun observeViewModel() {
        viewModel.incompleteQuestionnaire.observe(this) { questionnaire ->
            if (questionnaire != null) {
                binding.btnContinueQuestionnaire.visibility = android.view.View.VISIBLE
                binding.btnContinueQuestionnaire.setOnClickListener {
                    val intent = Intent(this, QuestionnaireActivity::class.java)
                    intent.putExtra("questionnaire_id", questionnaire.id)
                    startActivity(intent)
                }
            } else {
                binding.btnContinueQuestionnaire.visibility = android.view.View.GONE
            }
        }
    }
}

