package com.example.relook
import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.altbeacon.beacon.Identifier
import android.R.attr.keySet
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.bootstrap_edittext)
            val editTextStr = editText.text.toString()
            val editText2 = findViewById<EditText>(R.id.bootstrap_edittext2)
            val editText2Str = editText2.text.toString()
            val stations = mutableListOf("西馬込","馬込","中延","戸越","五反田","高輪台","泉岳寺","三田" ,"大門", "新橋","東銀座","宝町","日本橋","人形町","東日本橋","浅草橋","蔵前","浅草","本所吾妻橋","押上(スカイツリー前)")
            val requests = mutableListOf("28534442-4c45-4144-80c0-1800ffffffff","38534442-4c45-4144-80c0-1800ffffffff","48534442-4c45-4144-80c0-1800ffffffff","58534442-4c45-4144-80c0-1800ffffffff","68534442-4c45-4144-80c0-1800ffffffff","78534442-4c45-4144-80c0-1800ffffffff","88534442-4c45-4144-80c0-1800ffffffff","uuid8" ,"deac3f40829011e5b15c0002a5d5c51b0000000","uuid10","uuid11","uuid12","uuid13","uuid14","uuid15","uuid16","uuid17","uuid18")
            val index = stations.indexOf(editTextStr)
            val index2 = stations.indexOf(editText2Str)
            val range = index..index2
            var i = 0
            if (8 !in range) {
                i=8
            }
            val requested_uuid = requests[i]
            check(index, index2,requested_uuid)
        }
    }
    private fun check(index: Int,index2: Int,requested_uuid:String):Boolean {
        if (index != -1 && index2 != -1) {
            val intent = Intent(application, SubActivity::class.java)
            intent.putExtra("undesired_uuid", requested_uuid)
            startActivity(intent)
        }
        else{
            AlertDialog.Builder(this)
                .setTitle("入力が正しくありません")
                .setMessage("正しい駅名を入力しているかどうかご確認ください")
                .show()
        }
        return true
    }
}
