package com.example.focuswolf.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.focuswolf.R
import com.example.focuswolf.data.FakeData

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_stats, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val totalTasks = FakeData.tasks.size

        val completedTasks = FakeData.tasks.count {
            it.state == "Hecha"
        }

        val pendingTasks = FakeData.tasks.count {
            it.state != "Hecha"
        }

        val progressPercent = if (totalTasks == 0) {
            0
        } else {
            (completedTasks * 100) / totalTasks
        }

        val tvCompletedStats = view.findViewById<TextView>(R.id.tvCompletedStats)
        val tvPendingStats = view.findViewById<TextView>(R.id.tvPendingStats)
        val progressStats = view.findViewById<ProgressBar>(R.id.progressStats)

        tvCompletedStats.text = "$completedTasks de $totalTasks tareas completadas"
        tvPendingStats.text = "$pendingTasks tareas pendientes"
        progressStats.progress = progressPercent
    }
}