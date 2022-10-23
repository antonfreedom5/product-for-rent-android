package com.example.rpos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.rpos.model.Employee
import com.example.rpos.model.Position
import com.example.rpos.source.EmployeeSource
import com.example.rpos.source.PositionsSource
import kotlinx.coroutines.runBlocking

class AddEmployeeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = runBlocking {
        val view = inflater.inflate(R.layout.fragment_add_employee, container, false)
        val name = view.findViewById<EditText>(R.id.nameText)
        val phone = view.findViewById<EditText>(R.id.phoneText)
        val address = view.findViewById<EditText>(R.id.addressText)

        val spinner = view.findViewById<Spinner>(R.id.positionSpinner)
        val positions = PositionsSource().getPositions()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, positions)
        spinner.adapter = adapter
        var selectedPosition = Position(id = 0, name = "")
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedPosition = positions[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        val button = view.findViewById<Button>(R.id.addButton)
        button.setOnClickListener { onClick(name.text.toString(), phone.text.toString(), address.text.toString(), selectedPosition) }
        return@runBlocking view
    }

    private fun onClick(name: String, phone: String, address: String, position: Position) = runBlocking {
        EmployeeSource().add(Employee(id = 0, name, phone, address, position))
        val newFragment: Fragment = EmployeeFragment()

        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
    }
}