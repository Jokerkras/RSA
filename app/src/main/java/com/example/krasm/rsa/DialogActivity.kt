package com.example.krasm.rsa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_dialog.*
import java.nio.charset.Charset

class DialogActivity : AppCompatActivity() {

    val rsa: RSA = RSA()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_dialog)

        btnOpenDialog.setOnClickListener({openLogActivity()})
    }

    fun openLogActivity() {
        val intent = Intent(this, LogActivity::class.java)
        var testString = "Test string for RSA"
        val encrypted = rsa.encrypt(testString.toByteArray())
        val decrypted = rsa.decrypt(encrypted)
        intent.putExtra("text", "Open key:\ne = ${rsa.e}\nN = ${rsa.N}\nPrivate key:\nd = ${rsa.d}\nN = ${rsa.N}\n" +
                "Massege:\n" + testString + "\nEncrypted:\n${RSA.Companion.bytesToString(encrypted)}\nDecrypted:\n" + String(decrypted))
        startActivity(intent)
    }
}
