package ciphers

object Vigenere {
    private const val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun encrypt(text: String, key: String): String =
        text
            .zip(key.repeatAndTruncateToLength(text.length))
            .map { (t, k) ->
                t.shift(k, ALPHABET)
            }.joinToString("")

    fun decrypt(text: String, key: String): String =
        text
            .zip(key.repeatAndTruncateToLength(text.length))
            .map { (t, k) ->
                t.shift(k, ALPHABET, invert = true)
            }.joinToString("")

    private fun Char.shift(keyChar: Char, alphabet: String, invert: Boolean = false): Char =
        if (invert) // Decrypt
            alphabet[
                (alphabet.indexOf(this, ignoreCase = true) - alphabet.indexOf(keyChar, ignoreCase = true))
                    .mod(alphabet.length)
            ]
        else // Encrypt
            alphabet[
                (alphabet.indexOf(this, ignoreCase = true) + alphabet.indexOf(keyChar, ignoreCase = true))
                    .mod(alphabet.length)
            ]

    private fun String.repeatAndTruncateToLength(otherLen: Int): String =
        if (this.length > otherLen) this.substring(0, otherLen)
        else this.repeat((otherLen / this.length) + 1).substring(0, otherLen)
}