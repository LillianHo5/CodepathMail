package com.example.codpathmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codpathmail.EmailFetcher.Companion.getEmails
import com.example.codpathmail.EmailFetcher.Companion.getNext5Emails

class MainActivity : AppCompatActivity() {
    lateinit var emails: List<Email>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in the activity layout
        var emailsRv = findViewById<RecyclerView>(R.id.emailsRv)
        // Fetch list of emails
        emails = getEmails()
        // Create adapter by passing in the list of emails
        val emailAdapter = EmailAdapter(emails)
        // Attach the adapter to the RecyclerView to populate items
        emailsRv.adapter = emailAdapter
        // Set layout manager to position the items
        emailsRv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.load_button).setOnClickListener {
            // Fetch next 5 emails
            var newEmails = getNext5Emails()
            // Add new emails to existing list of emails
            (emails as MutableList<Email>).addAll(newEmails)
            // Notify the adapter there's new emails so the RecyclerView layout is updated
            emailAdapter.notifyDataSetChanged()
        }
    }
}

class Email(
    val sender: String,
    val title: String,
    val summary: String) {
}