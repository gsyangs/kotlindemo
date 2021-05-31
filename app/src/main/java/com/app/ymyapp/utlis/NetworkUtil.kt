package com.app.ymyapp.utlis

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import com.app.ymyapp.application.YmyApplication

/**
 * @author:create by ys
 * 时间:2021/5/24 17
 * 邮箱 894417048@qq.com
 *
 * object 修饰符修饰类代表整个类中的方法都是静态方法
 */
object NetworkUtil {

    /**
     * 网络是否连接判断工具
     */
    fun isNetConnected(): Boolean {
        val connectivity = YmyApplication.instance?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.activeNetworkInfo
            if (info != null && info.isConnected) {
                if (info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }


    fun getMachineIpAddress(context: Context): String? {
        val wifiManager =
            context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (wifiManager.isWifiEnabled) {
            val wifiInfo = wifiManager.connectionInfo
            val ipAddress = wifiInfo.ipAddress
            return intToIp(ipAddress)
        }
        return "0.0.0.0"
    }

    private fun intToIp(ipAddress: Int): String? {
        return ((ipAddress and 0xFF).toString() + "." + (ipAddress shr 8 and 0xFF) + "." + (ipAddress shr 16 and 0xFF)
                + "." + (ipAddress shr 24 and 0xFF))
    }
}