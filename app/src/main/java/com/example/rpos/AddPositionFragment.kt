package com.example.rpos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.example.rpos.model.Position
import com.example.rpos.source.PositionsSource
import kotlinx.coroutines.runBlocking

class AddPositionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = runBlocking {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_position, container, false)
        val positionName = view.findViewById<EditText>(R.id.positionText)
        val button = view.findViewById<Button>(R.id.addButton)
        button.setOnClickListener { byClick(positionName.text.toString()) }
        return@runBlocking view
    }

    private fun byClick(name: String) = runBlocking {
        PositionsSource().add(Position(id = 0, name = name))
        val newFragment: Fragment = PositionFragment() //YourFragment заменить на нужный

        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
    }
}