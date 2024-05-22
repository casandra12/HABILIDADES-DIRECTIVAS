package fes.aragon.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import fes.aragon.proyectofinal.databinding.ActivityContactsBinding

class ContactsActivity : AppCompatActivity() {
    private val  db = Firebase.firestore
    private lateinit var contactsRef: CollectionReference
    private lateinit var binding: ActivityContactsBinding
    private val userId = Firebase.auth.currentUser?.uid
    private val contacts = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        adapter = ContactAdapter(contacts, this)
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.contactsRecyclerView.adapter = adapter
        setContentView(binding.root)
        binding.createContactButton.setOnClickListener {
            val intent = Intent(this, CreateContactActivity::class.java)
            startActivity(intent)
        }
        //getContacts()
    }

    override fun onResume() {
        super.onResume()
        getContacts()
    }

    fun getContacts() {
        contacts.clear()
        contactsRef = db.collection("appContactos").document(userId.toString()).collection("contactos")
        contactsRef.get().addOnSuccessListener { documents ->
            for (document in documents) {
                val id = document.id
                val name = document.data["name"].toString()
                val lastName = document.data["lastName"].toString()
                val phone = document.data["phone"].toString()
                val email = document.data["email"].toString()
                val avatar = document.data["avatar"].toString()
                val contact = Contact(id, name, lastName, phone, email, avatar)
                contacts.add(contact)
                Log.d("document", "Contact: $document")
            }
            Log.d("ContactsActivity", "Contacts: $contacts")
            adapter.notifyDataSetChanged()
        }
    }

}