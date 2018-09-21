package parser

import lexer.MathLexer
import lexer.TokenType

open class Parser(private val lexer: MathLexer) {
    protected var lookahead = lexer.nextToken()

    protected fun match(type: TokenType) {
        if (lookahead.type == type) {
            lookahead = lexer.nextToken()
        } else {
            throw SyntaxException(lookahead, type)
        }
    }
}