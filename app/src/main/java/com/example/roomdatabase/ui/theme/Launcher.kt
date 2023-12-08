package com.example.roomdatabase.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.EmployEntity
import com.example.roomdatabase.EmployeeApp
import com.example.roomdatabase.EmployeeDao
import com.example.roomdatabase.ItemAdapter
import com.example.roomdatabase.databinding.ActivityLauncherBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Launcher : AppCompatActivity() {
    private var binding:ActivityLauncherBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val employeeDao=(application as EmployeeApp).db.employeeDao()
        binding?.btnAdd?.setOnClickListener {
            AddRecord(employeeDao)
        }
        lifecycleScope.launch {
            employeeDao.fetchAllEmployees().collect(){
                val list=ArrayList(it)
                setupListofDataIntoRecyclerView(list,employeeDao)
            }
        }
    }
    fun AddRecord(employeeDao: EmployeeDao){
        val name=binding?.etName?.text.toString()
        val email=binding?.etEmailId?.text.toString()
        if(email.isNotEmpty()&&name.isNotEmpty()){
            lifecycleScope.launch {
                employeeDao.insert(EmployEntity(name=name,email=email))
                Toast.makeText(applicationContext,"Record saved", Toast.LENGTH_LONG).show()
                binding?.etName?.text?.clear()
                binding?.etEmailId?.text?.clear()
            }
        }
        else
        {
            Toast.makeText(applicationContext,"both boxes must be filled", Toast.LENGTH_LONG).show()
        }
    }
    private fun setupListofDataIntoRecyclerView(employeesList:ArrayList<EmployEntity>,employeeDao: EmployeeDao){
        if(employeesList.isNotEmpty()){
            val itemAdapter=ItemAdapter(employeesList)
            binding?.rvItemsList?.layoutManager=LinearLayoutManager(this)
            binding?.rvItemsList?.adapter=itemAdapter
            binding?.rvItemsList?.visibility= View.VISIBLE
            binding?.tvNoRecordsAvailable?.visibility=View.GONE
        }
        else{
            binding?.rvItemsList?.visibility= View.GONE
            binding?.tvNoRecordsAvailable?.visibility=View.VISIBLE
        }

    }
}