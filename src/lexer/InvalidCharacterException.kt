package lexer

class InvalidCharacterException(character: Char?, position: Int) :
        RuntimeException("Unrecognized character '$character' at position ${position + 1}")