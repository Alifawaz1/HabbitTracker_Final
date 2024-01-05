package com.example.habbit_tracker.models

data class Habit(
    val id: Int,
    var name: String,
    var description: String,
    var progress: Int,
    val goal: Int,

    )