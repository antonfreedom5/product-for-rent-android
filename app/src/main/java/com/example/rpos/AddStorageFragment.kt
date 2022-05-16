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

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddStorageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddStorageFragment : Fragment() {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddStorageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddStorageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}