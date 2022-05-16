package com.example.rpos

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.rpos.source.ClientSource
import com.example.rpos.source.EmployeeSource
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClientFragment : Fragment() {
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_client, container, false)
        val addButton = view.findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener { onAddClick() }
        val positionTable = view.findViewById<TableLayout>(R.id.clientTable)
        val result = ClientSource().getClients()
        val headerRow = TableRow(context)

        val headerColumn1 = TextView(context)
        val headerColumn2 = TextView(context)
        val headerColumn3 = TextView(context)
        val headerColumn4 = TextView(context)
        val headerColumn5 = TextView(context)

        headerColumn1.text = "Name"

        headerColumn2.text = "Phone"
        headerColumn2.background = ColorDrawable(Color.parseColor("#A9A9A9"))
        headerColumn2.setTextColor(Color.WHITE)

        headerColumn3.text = "Address"

        headerColumn4.text = "Products"
        headerColumn4.background = ColorDrawable(Color.parseColor("#A9A9A9"))
        headerColumn4.setTextColor(Color.WHITE)

        headerColumn5.text = "Delete"

        headerRow.addView(headerColumn1, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
        ))
        headerRow.addView(headerColumn2, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
        ))
        headerRow.addView(headerColumn3, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
        ))
        headerRow.addView(headerColumn4, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
        ))
        headerRow.addView(headerColumn5, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
        ))
        positionTable.addView(headerRow, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
        ))

        for (i in result) {
            val tableRow = TableRow(context)
            val columnText1 = TextView(context)
            columnText1.text = i.name
            columnText1.textSize = 15F
            val columnText2 = TextView(context)
            columnText2.text = i.phone
            columnText2.textSize = 15F
            val columnText3 = TextView(context)
            columnText3.text = i.address
            columnText3.textSize = 15F
            val columnText4 = TextView(context)
            columnText4.text = i.products.joinToString(",") { it.name }
            columnText4.textSize = 15F

            val deleteButton = Button(context)
            deleteButton.text = "Delete"
            deleteButton.setOnClickListener { onDeleteClick(i.id, i.name) }

            tableRow.addView(columnText1, TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
            ))
            tableRow.addView(columnText2, TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
            ))
            tableRow.addView(columnText3, TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
            ))
            tableRow.addView(columnText4, TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
            ))
            tableRow.addView(deleteButton, TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
            ))
            positionTable.addView(tableRow)
        }
        return@runBlocking view
    }

    private fun onDeleteClick(id: Long, name: String) = runBlocking {
        ClientSource().deleteById(id)
        val toast = Toast.makeText(context, "$name deleted", Toast.LENGTH_SHORT)
        val newFragment: Fragment = ClientFragment()
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
        toast.show()
    }

    private fun onAddClick() {
        val newFragment: Fragment = AddClientFragment()
        val transaction = requireFragmentManager().beginTransaction()
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
         * @return A new instance of fragment ClientFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClientFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}