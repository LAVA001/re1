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
            val stations = mutableListOf("西馬込","馬込","中延","戸越","五反田","高輪台","泉岳寺","三田" ,"大門", "新橋","東銀座","宝町")
            val requests = mutableListOf("uuid1","uuid2","48534442-4C45-4144-80C0-1800FFFFFFFF","uuid4","uuid5","uuid6","uuid7","uuid8" ,"deac3f40829011e5b15c0002a5d5c51b0000000","uuid10","uuid11","uuid12")
            val index = stations.indexOf(editTextStr)
            val index2 = stations.indexOf(editText2Str)
            val i=3
            val requested_uuid = requests[i]
            check(index, index2,requested_uuid)
        }
    }
    private fun check(index: Int,index2: Int,requested_uuid:String):Boolean {
        if (index != -1 && index2 != -1) {
            val intent = Intent(application, SubActivity::class.java)
            intent.putExtra("undesired_uuid", requested_uuid);
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



//val map: LinkedHashMap<String,String> = linkedMapOf("三田" to "uzfisn1" ,"大門" to "uzfisn2")
// val stations = listof(linkedMapOf(keySet))