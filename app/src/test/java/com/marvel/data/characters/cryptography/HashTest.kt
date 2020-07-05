package com.marvel.data.characters.cryptography

import org.junit.Assert.assertEquals
import org.junit.Test

class HashTest {

    @Test
    fun assertMD5GenerationBehavior() {
        val timestamp = "TIMESTAMP"
        val publicKey = "API_KEY"
        val privateKey = "PRIVATE_KEY"
        val expected = "1f965bd78d49b510ff6342e9f41e49bc"
        val answer = Hash.generateMD5(timestamp, publicKey, privateKey)

        assertEquals(expected, answer)
    }
}
