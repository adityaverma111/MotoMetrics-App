package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivityUpdateBinding
import com.example.admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.updatebtn.setOnClickListener {
            val ownerName = binding.updatevehicalownername.text.toString()
            val vehicleNumber = binding.updatevehiclenumber.text.toString()
            val semester = binding.uploadsemester.text.toString()
            val phoneNumber = binding.updatePhonenumber.text.toString()
            updatedata( ownerName, vehicleNumber , semester ,phoneNumber )
        }

    }

    private fun updatedata(
        ownerName: String,
        vehicleNumber: String,
        semester: String,
        phoneNumber: String
    ) {
        databaseReference = FirebaseDatabase.getInstance().getReference("vehicle Information")
        val vehicleData = mapOf<String, String>(
            "ownerName" to ownerName, "vehicleNumber" to vehicleNumber,
            "semester" to semester, "phoneNumber" to phoneNumber)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            binding.updatevehicalownername.text.clear()
            binding.updatevehiclenumber.text.clear()
            binding.uploadsemester.text.clear()

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UpdateActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()

        }
    }
}