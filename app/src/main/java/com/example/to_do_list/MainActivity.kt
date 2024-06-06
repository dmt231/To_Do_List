package com.example.to_do_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceLayout()
    }
    private fun replaceLayout(){
        val mainFragment = MainFragment()
        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.mainActivity, mainFragment)
        fragmentTrans.commit()
    }
}