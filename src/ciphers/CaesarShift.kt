package ciphers

object CaesarShift {
    private const val DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun encrypt(text: String, offset: Int, alphabet: String = DEFAULT_ALPHABET): String = text.map { c ->
        if (c.isUpperCase()) c.shiftBy(offset, alphabet)
        else c.shiftBy(offset, alphabet).lowercaseChar()
    }.joinToString("")

    fun decrypt(text: String, offset: Int, alphabet: String = DEFAULT_ALPHABET): String =
        encrypt(text, -offset, alphabet)

    private fun Char.shiftBy(n: Int, alphabet: String): Char =
        alphabet[(alphabet.indexOf(this, ignoreCase = true) + n) % alphabet.length]
}