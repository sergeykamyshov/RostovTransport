package ru.sergeykamyshov.rostovtransport.base.utils

import android.content.Context
import java.io.InputStream
import java.math.BigInteger
import java.security.MessageDigest

class FileUtils {

    companion object {

        fun getJson(context: Context, file: String): String {
            return context.assets
                    .open(file)
                    .bufferedReader()
                    .readText()
        }

        fun getMd5Hash(context: Context, file: String): String {
            var inputStream: InputStream? = null
            try {
                inputStream = context.assets.open(file)
                val digest = MessageDigest
                        .getInstance("MD5")
                        .digest(inputStream.readBytes())
                val hash = BigInteger(1, digest).toString(16)
                return hash
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
            }
            return ""
        }

    }

}