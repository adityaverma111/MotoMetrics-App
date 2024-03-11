package com.example.admin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract.Root
import com.example.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
          val intent = Intent(this@MainActivity,UploadActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.updatebtn.setOnClickListener {
            val intent = Intent(this@MainActivity,UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.deletebtn.setOnClickListener {
            val intent = Intent(this@MainActivity,DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}