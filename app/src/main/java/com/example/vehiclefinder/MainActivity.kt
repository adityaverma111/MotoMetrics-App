package com.example.vehiclefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vehiclefinder.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val SearchVehicleNumber: String = binding.searchPhone.text.toString()
            if(SearchVehicleNumber.isNotEmpty()){
                redData(SearchVehicleNumber)
            }else{
                Toast.makeText(this,"Enter Valid Number ",Toast.LENGTH_LONG).show()
            }
        }

    }
    private  fun redData(vehicleNumber : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("vehicle Information")
       databaseReference.child(vehicleNumber).get().addOnSuccessListener {
           if(it.exists()){
               val ownerName = it.child("ownerName").value
               val vehicleNumber = it.child("vehicleNumber").value
               val semester = it.child("semester").value
               val phoneNumber = it.child("phoneNumber").value
               Toast.makeText(this,"Result Found",Toast.LENGTH_LONG).show()
               binding.searchPhone.text.clear()
               binding.name.text = ownerName.toString()
               binding.readBineNumber.text = vehicleNumber.toString()
               binding.readSemester.text = semester.toString()
               binding.readNumber.text = phoneNumber.toString()
           }
           else{
               Toast.makeText(this,"Vehicle Number Does Not Exists",Toast.LENGTH_LONG).show()
           }
       }.addOnFailureListener{
           Toast.makeText(this,"Something Wrong",Toast.LENGTH_LONG).show()
       }
    }
}