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
            val stations = mutableListOf("西馬込","馬込","中延","戸越","五反田","高輪台","泉岳寺", "大門", "新橋","東銀座","宝町")
           // val undesired_uuids= mutableListOf("UAYZT122","askd43fi")
            val index = stations.indexOf(editTextStr)
            val index2 = stations.indexOf(editText2Str)
           // val request = undesired_uuids.indexOf(editTextStr)
            //val request2 = undesired_uuids.indexOf(editText2Str)
            check(index, index2)
        }
    }
    private fun check(index: Int,index2: Int):Boolean {
        if (index != -1 && index2 != -1) {
            val intent = Intent(application, SubActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}
