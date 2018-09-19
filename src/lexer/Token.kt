package lexer

class Token(val type: TokenType, val value: String?) {

    constructor(type: TokenType, value: Char) : this(type, value.toString())

    override fun toString(): String {
        return "Token(type=$type, value='$value')"
    }
}