package com.example.rpos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.example.rpos.model.Product
import com.example.rpos.model.Storage
import com.example.rpos.source.ProductSource
import com.example.rpos.source.StorageSource
import kotlinx.coroutines.runBlocking

class AddProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = runBlocking {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)
        val name = view.findViewById<EditText>(R.id.nameText)
        val price = view.findViewById<EditText>(R.id.priceText)
        val count = view.findViewById<EditText>(R.id.countText)

        val spinner = view.findViewById<Spinner>(R.id.storageSpinner)
        val storages = StorageSource().getStorages()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, storages)

        spinner.adapter = adapter
        var selectedStorage = Storage(id = 0, name = "", address = "", phone = "")
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedStorage = storages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        val button = view.findViewById<Button>(R.id.addButton)
        button.setOnClickListener { onClick(name.text.toString(), price.text.toString().toLong(), selectedStorage, count.text.toString().toLong()) }
        return@runBlocking view
    }

    private fun onClick(name: String, price: Long, storage: Storage, count: Long) = runBlocking {
        ProductSource().add(Product(id = 0, name, price, storage, count))
        val newFragment: Fragment = ProductFragment()

        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
    }
}