package com.example.rpos

import android.os.Bundle
import android.util.Log
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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddEmployeeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddEmployeeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddEmployeeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddEmployeeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}