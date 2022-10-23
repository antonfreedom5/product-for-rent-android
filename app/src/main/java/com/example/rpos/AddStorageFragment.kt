package com.example.rpos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.example.rpos.model.Storage
import com.example.rpos.source.StorageSource
import kotlinx.coroutines.runBlocking

class AddStorageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = runBlocking {
        val view = inflater.inflate(R.layout.fragment_add_storage, container, false)
        val name = view.findViewById<EditText>(R.id.nameText)
        val phone = view.findViewById<EditText>(R.id.phoneText)
        val address = view.findViewById<EditText>(R.id.addressText)

        val button = view.findViewById<Button>(R.id.addButton)
        button.setOnClickListener { onClick(name.text.toString(), phone.text.toString(), address.text.toString()) }
        return@runBlocking view
    }

    private fun onClick(name: String, phone: String, address: String) = runBlocking {
        StorageSource().add(Storage(id = 0, name, phone, address))
        val newFragment: Fragment = StorageFragment()

        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
    }
}