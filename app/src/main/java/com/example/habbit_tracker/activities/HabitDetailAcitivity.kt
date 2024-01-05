package com.example.habbit_tracker.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.habbit_tracker.DashboardActivity
import com.example.habbit_tracker.R
import com.example.habbit_tracker.models.Habit
import com.example.habbit_tracker.models.HabitDatabase

class HabitDetailActivity : AppCompatActivity() {

    private lateinit var etHabitName: EditText
    private lateinit var etHabitDescription: EditText
    private lateinit var etHabitProgress: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_detail)

        etHabitName = findViewById(R.id.etHabitName)
        etHabitDescription = findViewById(R.id.etHabitDescription)
        etHabitProgress = findViewById(R.id.etHabitProgress)

        val habitId = intent.getIntExtra("habitId", -1)
        val currentHabit = HabitDatabase.habits.find { it.id == habitId }

        currentHabit?.let {
            etHabitName.setText(it.name)
            etHabitDescription.setText(it.description)
            etHabitProgress.setText(it.progress.toString())
        }

        val btnSave: Button = findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            // Save or update habit details
            saveOrUpdateHabit(currentHabit)
            finish() // Finish the activity
        }

        val btnDelete: Button = findViewById(R.id.btnDelete)
        btnDelete.setOnClickListener {
            deleteHabit(currentHabit)
            finish()
        }
    }

    private fun saveOrUpdateHabit(habit: Habit?) {
        val habitName = etHabitName.text.toString()
        val habitDescription = etHabitDescription.text.toString()
        val habitProgress = etHabitProgress.text.toString().toIntOrNull() ?: 0

        habit?.apply {
            name = habitName
            description = habitDescription
            progress = habitProgress
        } ?: run {
            // Create a new habit
            val newHabit = Habit(
                id = HabitDatabase.habits.size + 1,
                name = habitName,
                description = habitDescription,
                progress = habitProgress,
                goal=3
            )
            HabitDatabase.habits.add(newHabit)

            // Navigate to DashboardActivity after saving the habit
            val intent = Intent(this@HabitDetailActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity
        }

    }

    private fun deleteHabit(habit: Habit?) {
        habit?.let {
            HabitDatabase.habits.remove(it)
        }
    }
}
