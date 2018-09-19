package lexer

class InvalidCharacterException(message: Char?) : RuntimeException("\"$message\"")