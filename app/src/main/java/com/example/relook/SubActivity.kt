package com.example.relook

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.RemoteException
import androidx.appcompat.app.AlertDialog
import org.altbeacon.beacon.*

class SubActivity: AppCompatActivity(), BeaconConsumer {
    private val beaconManager: BeaconManager by lazy { BeaconManager.getInstanceForApplication(this) }
    private val undesiredUuid: String by lazy { intent.getStringExtra("undesired_uuid") }
    private val mainHandler = Handler()

    companion object{
        const val IBEACON_FORMAT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        // パーミッションダイアログ
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 0)
        }

        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(IBEACON_FORMAT))
    }

    override fun onResume() {
        super.onResume()
        beaconManager.bind(this)
    }

    override fun onPause() {
        super.onPause()
        beaconManager.unbind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        beaconManager.unbind(this)
    }

    override fun onBeaconServiceConnect() {
        val region = Region("", null, null, null)

        beaconManager.addMonitorNotifier(object: MonitorNotifier {
            override fun didEnterRegion(region: Region) {
                beaconManager.startRangingBeaconsInRegion(region)
            }

            override fun didExitRegion(region: Region) {
                beaconManager.stopRangingBeaconsInRegion(region)
            }

            override fun didDetermineStateForRegion(i: Int, region: Region?) {
                //領域の入退場のステータス変化
            }
        })

        beaconManager.addRangeNotifier{ beacons: MutableCollection<Beacon>?, _: Region? ->
            if (beacons?.any{ beacon -> beacon.id1.toString() == undesiredUuid } == true){
                mainHandler.post{ alertDialog() }
            }
        }

        try {
            //Beacon情報の監視を開始
            beaconManager.startMonitoringBeaconsInRegion(region)
        } catch (e: RemoteException){
            e.printStackTrace()
        }
    }

    private fun alertDialog(){
        val alertDialog: AlertDialog = this.let{
            val builder = AlertDialog.Builder(it).apply {
                setTitle("望まないビーコンを検知")
                setMessage("おそらく逆方向の電車に乗っています")
                setPositiveButton("ok", null)
            }

            builder.create()
        }
        alertDialog.show()
    }


}
