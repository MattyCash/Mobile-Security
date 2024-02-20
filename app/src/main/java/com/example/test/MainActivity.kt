package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R
import java.io.File
import java.io.FileWriter

class MainActivity : AppCompatActivity() {

    private lateinit var accountNumberEditText: EditText
    private lateinit var accountNameEditText: EditText
    private lateinit var balanceEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        accountNumberEditText = findViewById(R.id.account_number_edit_text)
        accountNameEditText = findViewById(R.id.account_name_edit_text)
        balanceEditText = findViewById(R.id.balance_edit_text)
        val saveButton = findViewById<Button>(R.id.save_button)

        saveButton.setOnClickListener {
            val accountNumber = accountNumberEditText.text.toString()
            val accountName = accountNameEditText.text.toString()
            val balance = balanceEditText.text.toString()

            val data = "Account Number: $accountNumber, Account Name: $accountName, Balance: $balance\n"

            showConfirmationDialog(data)
        }
    }

    private fun showConfirmationDialog(data: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirm Data")
        alertDialogBuilder.setMessage("Is the entered data correct?\n$data")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            saveDataToFile(data)
            clearFields()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun saveDataToFile(data: String) {
        val file = File(filesDir, "bank_accounts.txt")
        try {
            val fileWriter = FileWriter(file, true)
            fileWriter.append(data)
            fileWriter.flush()
            fileWriter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun clearFields() {
        accountNumberEditText.text.clear()
        accountNameEditText.text.clear()
        balanceEditText.text.clear()
    }
}
//test