package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deletebtn.setOnClickListener {
            val vehicleNumber = binding.deletesearch.text.toString()
            if(vehicleNumber.isNotEmpty()){
                deletedata(vehicleNumber)
            }else{
                Toast.makeText(this,"Please Enter Vehicle Number", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deletedata(vehicleNumber:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("vehicle Information")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.deletesearch.text.clear()
            Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Something Wnt Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}