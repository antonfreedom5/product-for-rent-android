package com.example.rpos

import android.os.Bundle
import android.util.Log
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddProductFragment : Fragment() {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}