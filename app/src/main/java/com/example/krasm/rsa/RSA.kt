package com.example.krasm.rsa

import java.math.BigInteger
import java.util.Random

class RSA {
    /*private*/ var r: Random = Random()
    /*private*/ var p: BigInteger = BigInteger.ZERO
    /*private*/ var q: BigInteger = BigInteger.ZERO
    /*private*/ var N: BigInteger = BigInteger.ZERO
    /*private*/ var phi: BigInteger = BigInteger.ZERO
    /*private*/ var e: BigInteger = BigInteger.ZERO
    /*private*/ var d: BigInteger = BigInteger.ZERO
    /*private*/ val bitLength = 64

    constructor() {
        r = Random()
        p = BigInteger.probablePrime(bitLength, r)
        q = BigInteger.probablePrime(bitLength, r)
        N = p.multiply(q)
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))
        e = BigInteger.probablePrime(bitLength / 2, r)
        while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE)
        }
        d = e.modInverse(phi)
    }

    constructor(e: BigInteger, d: BigInteger, N: BigInteger) {
        this.e = e
        this.d = d
        this.N = N
    }

    // Encrypt message
    fun encrypt(message: ByteArray): ByteArray {
        return BigInteger(message).modPow(e, N).toByteArray()
    }

    // Decrypt message
    fun decrypt(message: ByteArray): ByteArray {
        return BigInteger(message).modPow(d, N).toByteArray()
    }

    companion object {
        fun bytesToString(encrypted: ByteArray): String {
            var test = ""
            for (b in encrypted)
            {
                test += b.toString()
            }
            return test;
        }
    }
}