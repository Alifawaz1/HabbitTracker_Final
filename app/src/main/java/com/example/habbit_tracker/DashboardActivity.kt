package com.example.habbit_tracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habbit_tracker.activities.HabitListActivity
import com.example.habbit_tracker.adapters.HabitAdapter
import com.example.habbit_tracker.models.Habit
import com.example.habbit_tracker.models.HabitDatabase

class DashboardActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var btnGoToHabitList: Button
    private lateinit var recyclerViewHabits: RecyclerView
    private val habitList = mutableListOf<Habit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)//UI for the dashboardActivity

        tvWelcome = findViewById(R.id.tvWelcome)
        btnGoToHabitList = findViewById(R.id.btnGoToHabitList)
        recyclerViewHabits = findViewById(R.id.recyclerViewHabits)

        // Set a welcome message
        tvWelcome.text = "Welcome, User!"

        // Adapter for displaying habits in the RecyclerView
        val adapter = HabitAdapter(habitList) { habit ->
            showToast("Clicked on habit: ${habit.name}")
        } // Create your custom adapter
        recyclerViewHabits.adapter = adapter
        recyclerViewHabits.layoutManager = LinearLayoutManager(this)

        //navigation to HabitListActivity
        btnGoToHabitList.setOnClickListener {
            // Handle the click event to navigate to the HabitListActivity
            val intent = Intent(this, HabitListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        //update the habit list to save them in the database
        super.onResume()
        habitList.clear()
        habitList.addAll(HabitDatabase.habits)
        recyclerViewHabits.adapter?.notifyDataSetChanged()
    }

    private fun showToast(message: String) {
    }
}
