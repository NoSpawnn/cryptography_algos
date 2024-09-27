package ciphers

object Vigenere {
    private const val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun encrypt(text: String, key: String): String =
        text
            .zip(key.repeatAndTruncateToLength(text))
            .map { (t, k) ->
                t.shift(k, ALPHABET)
            }.joinToString("")

    fun decrypt(text: String, key: String): String =
        text
            .zip(key.repeatAndTruncateToLength(text))
            .map { (t, k) ->
                t.shift(k, ALPHABET, invert = true)
            }.joinToString("")

    private fun Char.shift(keyChar: Char, alphabet: String, invert: Boolean = false): Char =
        if (invert) // Decrypt
            alphabet[(alphabet.indexOf(this, ignoreCase = true) - alphabet.indexOf(
                keyChar, ignoreCase = true
            )).mod(alphabet.length)]
        else // Encrypt
            alphabet[(alphabet.indexOf(this, ignoreCase = true) + alphabet.indexOf(
                keyChar, ignoreCase = true
            )).mod(alphabet.length)]

    private fun String.repeatAndTruncateToLength(other: String): String =
        if (this.length > other.length) this.substring(0, other.length)
        else this.repeat((other.length / this.length) + 1).substring(0, other.length)
}