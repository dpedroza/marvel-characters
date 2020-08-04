package com.marvel.data.characters.cryptography

import com.marvel.BuildConfig
import java.security.MessageDigest

object Hash {

    fun generateMD5(timestamp: String, publicKey: String, privateKey: String): String {
        val algorithm = "MD5"
        val tobeHash = timestamp + privateKey + publicKey
        val digest = MessageDigest.getInstance(algorithm)
        digest.update(tobeHash.toByteArray())
        val hexString = StringBuilder()
        for (aMessageDigest in digest.digest()) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    }

    fun createHash(timestamp: String): String {
        val publicKey = BuildConfig.MARVEL_PUBLIC_KEY
        val privateKey = BuildConfig.MARVEL_PRIVATE_KEY
        return generateMD5(timestamp, publicKey, privateKey)
    }
}
