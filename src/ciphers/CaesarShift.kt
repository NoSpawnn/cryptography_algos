package ciphers

object CaesarShift {
    private const val DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun encrypt(text: String, offset: Int, alphabet: String = DEFAULT_ALPHABET, preserveCase: Boolean = true): String =
        text.map { c ->
            if (preserveCase)
                if (c.isUpperCase()) c.shiftBy(offset, alphabet)
                else c.shiftBy(offset, alphabet).lowercaseChar()
            else
                c.shiftBy(offset, alphabet)
        }.joinToString("")

    fun decrypt(text: String, offset: Int, alphabet: String = DEFAULT_ALPHABET): String =
        encrypt(text, -offset, alphabet)

    private fun Char.shiftBy(n: Int, alphabet: String): Char =
        alphabet[(alphabet.indexOf(this, ignoreCase = true) + n) % alphabet.length]
}