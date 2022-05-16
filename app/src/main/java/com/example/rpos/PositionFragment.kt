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
import com.example.rpos.source.PositionsSource
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PositionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PositionFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_position, container, false)
        val positionTable = view.findViewById<TableLayout>(R.id.positionTable)
        val addButton = view.findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener { addClickEvent() }
        val result = PositionsSource().getPositions()
        val headerRow = TableRow(context)
        val headerColumn1 = TextView(context)
        headerColumn1.text = "Name"
        val headerColumn2 = TextView(context)
        headerColumn2.text = "Delete"
        headerColumn2.background = ColorDrawable(Color.parseColor("#A9A9A9"))
        headerColumn2.setTextColor(Color.WHITE)
        headerRow.addView(headerColumn1, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
        ))
        headerRow.addView(headerColumn2, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
        ))
        positionTable.addView(headerRow)

        for (i in result) {
            val row1 = TableRow(context)
            val text = TextView(context)
            text.text = i.name
            text.textSize = 15F
            val deleteButton = Button(context)
            deleteButton.text = "Delete"
            deleteButton.setOnClickListener { deleteClickEvent(i.id, i.name) }
            row1.addView(text, TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
            ))
            row1.addView(deleteButton, TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f
            ))
            positionTable.addView(row1)
        }
        return@runBlocking view
    }

    private fun deleteClickEvent(id: Long, name: String) = runBlocking {
        PositionsSource().deleteById(id)
        val toast = Toast.makeText(context, "$name deleted", Toast.LENGTH_SHORT)
        val newFragment: Fragment = PositionFragment()
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
        toast.show()
    }

    private fun addClickEvent() {
        val newFragment: Fragment = AddPositionFragment()
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
         * @return A new instance of fragment PositionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PositionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}