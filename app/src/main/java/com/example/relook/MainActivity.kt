package com.example.relook
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.bootstrap_edittext)
            val editTextStr = editText.text.toString()
            val editText2 = findViewById<EditText>(R.id.bootstrap_edittext2)
            val editText2Str = editText2.text.toString()
            val stations = mutableListOf("三田", "大門", "新橋")
            val index = stations.indexOf(editTextStr)
            val index2 = stations.indexOf(editText2Str)
            check(index, index2)
        }
    }
    private fun check(index: Int,index2: Int):String {
        if (index != -1 && index2 != -1) {
            val intent = Intent(application, SubActivity::class.java)
            startActivity(intent)
        }
        return "対応していない駅名です"
    }

}
