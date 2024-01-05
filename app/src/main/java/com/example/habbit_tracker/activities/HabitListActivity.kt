package com.example.habbit_tracker.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habbit_tracker.R
import com.example.habbit_tracker.adapters.HabitAdapter
import com.example.habbit_tracker.models.HabitDatabase

class HabitListActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private val habitAdapter = HabitAdapter(HabitDatabase.habits) { habit ->
        // Handle item click (open HabitDetailActivity for editing)
        val intent = Intent(this, HabitDetailActivity::class.java)
        intent.putExtra("habitId", habit.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habbit_list)

        // CalendarView setup
        calendarView = findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            showToast("Selected Date: $selectedDate")
        }

        // RecyclerView setup
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = habitAdapter

        // Button setup
        val btnAddNewHabit: Button = findViewById(R.id.btnAddNewHabit)
        btnAddNewHabit.setOnClickListener {
            // Handle adding a new habit (navigate to HabitDetailActivity for adding)
            startActivity(Intent(this, HabitDetailActivity::class.java))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
