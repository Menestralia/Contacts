package com.example.contacts

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.fragment.ContactListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        openContactListFragment()
    }

    private fun openContactListFragment() {
        supportFragmentManager.beginTransaction().add(
            R.id.container,
            ContactListFragment(),
            ContactListFragment.TAG
        )
            .addToBackStack(MainActivity::class.java.simpleName)
            .commit()
    }
}