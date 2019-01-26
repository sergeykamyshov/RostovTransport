package ru.sergeykamyshov.rostovtransport.base.utils

import android.content.Context
import timber.log.Timber
import java.io.InputStream
import java.math.BigInteger
import java.security.MessageDigest

class FileUtils {

    companion object {

        fun getMd5Hash(context: Context, file: String): String {
            Timber.d("File $file")
            var inputStream: InputStream? = null
            try {
                inputStream = context.assets.open(file)
                val digest = MessageDigest
                        .getInstance("MD5")
                        .digest(inputStream.readBytes())
                val hash = BigInteger(1, digest).toString(16)
                Timber.d("File MD5 = $hash")
                return hash
            } catch (e: Exception) {
                Timber.e(e)
                e.printStackTrace()
            } finally {
                Timber.d("Finally close")
                inputStream?.close()
            }
            Timber.d("Return empty")
            return ""
        }

    }

}