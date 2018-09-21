package lexer

class MathLexer(input: String) : Lexer(input) {

    val DECIMAL_SEPARATOR = '.'
    val PLUS = '+'
    val MINUS = '-'
    val TIMES = '*'
    val DIV = '/'
    val POW = '^'
    val LEFT_PARENTHESIS = '('
    val RIGHT_PARENTHESIS = ')'

    private fun skipWhitespace() {
        while (currentChar != null && Character.isSpaceChar(currentChar!!)) {
            consume()
        }
    }

    private fun recognizeRationalNumber(): String {
        val sb = StringBuilder()
        var isDecimalSeparatorFound = false

        while (currentChar != null) {
            if (Character.isDigit(currentChar!!)) {
                sb.append(currentChar!!)
            } else if (currentChar!! == DECIMAL_SEPARATOR) {
                if (isDecimalSeparatorFound) {
                    throw InvalidCharacterException(currentChar, index)
                } else {
                    sb.append(currentChar!!)
                    isDecimalSeparatorFound = true
                }
            } else {
                break
            }
            consume()
        }

        val number = sb.toString()
        if (number.endsWith(DECIMAL_SEPARATOR)) {
            throw InvalidCharacterException(DECIMAL_SEPARATOR, index)
        }

        return number
    }

    override fun nextToken(): Token {
        while (currentChar != null) {
            if (Character.isSpaceChar(currentChar!!)) {
                skipWhitespace()
                continue
            }
            if (Character.isDigit(currentChar!!)) {
                return Token(TokenType.NUMBER, recognizeRationalNumber(), index)
            }

            var token: Token? = null
            if (currentChar == PLUS) {
                token = Token(TokenType.PLUS, PLUS, index)
            }
            if (currentChar == MINUS) {
                token = Token(TokenType.MINUS, MINUS, index)
            }
            if (currentChar == TIMES) {
                token = Token(TokenType.TIMES, TIMES, index)
            }
            if (currentChar == DIV) {
                token = Token(TokenType.DIV, DIV, index)
            }
            if (currentChar == POW) {
                token = Token(TokenType.POW, POW, index)
            }
            if (currentChar == LEFT_PARENTHESIS) {
                token = Token(TokenType.LEFT_PARENTHESIS, LEFT_PARENTHESIS, index)
            }
            if (currentChar == RIGHT_PARENTHESIS) {
                token = Token(TokenType.RIGHT_PARENTHESIS, RIGHT_PARENTHESIS, index)
            }

            if (token != null) {
                consume()
                return token
            }

            throw InvalidCharacterException(currentChar, index)
        }

        return Token(TokenType.END_OF_INPUT, null, index)
    }
}