package com.example.rpos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.example.rpos.model.Client
import com.example.rpos.model.Product
import com.example.rpos.source.ClientSource
import com.example.rpos.source.ProductSource
import kotlinx.coroutines.runBlocking

class AddClientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = runBlocking {
        val view = inflater.inflate(R.layout.fragment_add_client, container, false)
        val container = view.findViewById<TableRow>(R.id.checkboxContainer)
        val name = view.findViewById<EditText>(R.id.nameText)
        val phone = view.findViewById<EditText>(R.id.phoneText)
        val address = view.findViewById<EditText>(R.id.addressText)

        val products = ProductSource().getProducts()
        val checkedList: MutableList<Product> = mutableListOf()
        products.forEach {
            val checkBox = CheckBox(context)
            checkBox.text = it.name
            checkBox.id = it.id.toInt()
            checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    checkedList.add(it)
                } else {
                    checkedList.remove(it)
                }
            })
            container.addView(checkBox)
        }
        val button = view.findViewById<Button>(R.id.addButton)
        button.setOnClickListener { onClick(name.text.toString(), phone.text.toString(), address.text.toString(), checkedList) }
        return@runBlocking view
    }

    private fun onClick(name: String, phone: String, address: String, products: List<Product>) = runBlocking {
        ClientSource().add(Client(id = 0, name, phone, address, products))
        val newFragment: Fragment = ClientFragment()

        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragmentContainerView,
            newFragment
        )
        transaction.commit()
    }
}