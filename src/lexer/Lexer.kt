package lexer

class Lexer(input: String) {

    val DECIMAL_SEPARATOR = '.'
    val PLUS = '+'
    val MINUS = '-'
    val TIMES = '*'
    val DIV = '/'
    val POW = '^'
    val LEFT_PARENTHESIS = '('
    val RIGHT_PARENTHESIS = ')'

    private val input = input.toCharArray()
    private var index = 0
    private var currentChar : Char? = input[index]

    private fun advance() {
        index++
        currentChar = if (index < input.size) input[index] else null
    }

    private fun skipWhitespace() {
        while (currentChar != null && Character.isSpaceChar(currentChar!!)) {
            advance()
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
                    throw InvalidCharacterException(currentChar)
                } else {
                    sb.append(currentChar!!)
                    isDecimalSeparatorFound = true
                }
            } else {
                break
            }
            advance()
        }

        val number = sb.toString()
        if (number.endsWith(DECIMAL_SEPARATOR)) {
            throw InvalidCharacterException(DECIMAL_SEPARATOR)
        }

        return number
    }

    fun nextToken(): Token {
        while (currentChar != null) {
            if (Character.isSpaceChar(currentChar!!)) {
                skipWhitespace()
                continue
            }
            if (Character.isDigit(currentChar!!)) {
                return Token(TokenType.NUMBER, recognizeRationalNumber())
            }

            var token: Token? = null
            if (currentChar == PLUS) {
                token = Token(TokenType.PLUS, PLUS)
            }
            if (currentChar == MINUS) {
                token = Token(TokenType.MINUS, MINUS)
            }
            if (currentChar == TIMES) {
                token = Token(TokenType.TIMES, TIMES)
            }
            if (currentChar == DIV) {
                token = Token(TokenType.DIV, DIV)
            }
            if (currentChar == POW) {
                token = Token(TokenType.POW, POW)
            }
            if (currentChar == LEFT_PARENTHESIS) {
                token = Token(TokenType.LEFT_PARENTHESIS, LEFT_PARENTHESIS)
            }
            if (currentChar == RIGHT_PARENTHESIS) {
                token = Token(TokenType.RIGHT_PARENTHESIS, RIGHT_PARENTHESIS)
            }

            if (token != null) {
                advance()
                return token
            }

            throw InvalidCharacterException(currentChar)
        }

        return Token(TokenType.EOI, null)
    }
}