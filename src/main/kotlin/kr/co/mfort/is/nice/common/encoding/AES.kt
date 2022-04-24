package kr.co.mfort.`is`.nice.common.encoding

import org.apache.commons.codec.binary.Hex
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

fun aesEncode(secretKey: String, plainText: String) = AESEncoder(secretKey).encode(plainText)

class AESEncoder(
    secretKey: String
) {
    /**
     * Cipher는 non-thread-safe 이므로 AESEncoder 인스턴스마다 Cipher 객체를 갖도록한다.
     *
     * references:
     * - https://stackoverflow.com/questions/43989519/cipher-getinstance-and-cipher-getinit-for-each-message-in-case-of-random-iv}
     * - https://stackoverflow.com/questions/6957406/is-cipher-thread-safe
     */
    private val cipher: Cipher

    init {
        val secureKey = SecretKeySpec(secretKey.toByteArray(), "AES");
        this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        this.cipher.init(Cipher.ENCRYPT_MODE, secureKey)
    }

    fun encode(plainText: String): String {
        return Hex.encodeHexString(this.cipher.doFinal(plainText.toByteArray()))
    }
}
