package com.example.rpos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rpos.adapter.PositionAdapter
import com.example.rpos.enums.Type
import com.example.rpos.source.EmployeeSource
import kotlinx.coroutines.runBlocking

class EmployeeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = runBlocking {
        val view = inflater.inflate(R.layout.fragment_position, container, false)
        val result = EmployeeSource().getEmployees()
        val names = result.map { it.toString() }
        val ids = result.map { it.id }

        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PositionAdapter(Type.Employee, ids, names) { updateFragment() }

        val addButton = view.findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener { onAddClick() }
        return@runBlocking view
    }

    private fun updateFragment() = runBlocking {
        val newFragment: Fragment = EmployeeFragment()
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
    }

    private fun onAddClick() {
        val newFragment: Fragment = AddEmployeeFragment()
        val transaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
    }
}