package com.example.admin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.savebtn.setOnClickListener {
          val ownerName = binding.uploadvehicalownername.text.toString()
          val vehicleNumber = binding.uploadvehiclenumber.text.toString()
          val semester = binding.uploadsemester.text.toString()
          val phoneNumber = binding.uploadPhonenumber.text.toString()

          databaseReference = FirebaseDatabase.getInstance().getReference("vehicle Information")
          val vehicleData = VehicleData(ownerName ,vehicleNumber, semester, phoneNumber)
            databaseReference.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                binding.uploadvehicalownername.text.clear()
                binding.uploadvehiclenumber.text.clear()
                binding.uploadsemester.text.clear()
                binding.uploadPhonenumber.text.clear()


                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}