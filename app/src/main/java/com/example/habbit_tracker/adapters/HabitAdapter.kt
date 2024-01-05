package com.example.habbit_tracker.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habbit_tracker.R
import com.example.habbit_tracker.models.Habit

class HabitAdapter(//for displaying name and goal
    private val habits: List<Habit>,
    private val onItemClick: (Habit) -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitName: TextView = itemView.findViewById(R.id.habitName)
        val habitGoal: TextView = itemView.findViewById(R.id.habitGoal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(itemView)
    }
//sets the habit name in the corresponding item
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val currentHabit = habits[position]
        holder.habitName.text = currentHabit.name

        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentHabit)
        }
    }
//detemining the size of the recycler view
    override fun getItemCount() = habits.size
}
