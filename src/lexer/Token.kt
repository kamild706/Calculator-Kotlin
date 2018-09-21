package lexer

class Token(val type: TokenType, val value: String?, val position: Int) {

    constructor(type: TokenType, value: Char, position: Int) : this(type, value.toString(), position)

    override fun toString(): String {
        return "Token(type=$type, value='$value', position='$position')"
    }
}