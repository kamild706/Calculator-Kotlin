package parser

import lexer.Lexer
import lexer.TokenType

class Parser(private val lexer: Lexer) {

    private var currentToken = lexer.nextToken()

    private fun consume(type: TokenType) {
        if (currentToken.type == type) {
            currentToken = lexer.nextToken()
        } else {
            throw SyntaxException(currentToken.value!!)
        }
    }

    private fun factor(): Expression {
        if (currentToken.type == TokenType.NUMBER) {
            val token = currentToken
            consume(TokenType.NUMBER)
            return Constant(token)
        } else if (currentToken.type == TokenType.LEFT_PARENTHESIS) {
            consume(TokenType.LEFT_PARENTHESIS)
            val node = expression()
            consume(TokenType.RIGHT_PARENTHESIS)
            return node
        }
        throw SyntaxException(currentToken.value!!)
    }

    private fun power(): Expression {
        if (currentToken.type == TokenType.MINUS) {
            consume(TokenType.MINUS)
            var node = power()
            while (currentToken.type == TokenType.POW) {
                consume(TokenType.POW)
                node = Power(node, power())
            }
            return Negation(node)
        } else {
            var node = factor()
            while (currentToken.type == TokenType.POW) {
                consume(TokenType.POW)
                node = Power(node, power())
            }
            return node
        }
    }

    private fun term(): Expression {
        var node = power()
        while (currentToken.type in arrayOf(TokenType.TIMES, TokenType.DIV)) {
            val token = currentToken
            consume(currentToken.type)
            node = if (token.type == TokenType.TIMES) {
                Product(node, power())
            } else {
                Quotient(node, power())
            }
        }
        return node
    }

    private fun expression(): Expression {
        var node = term()
        while (currentToken.type in arrayOf(TokenType.PLUS, TokenType.MINUS)) {
            val token = currentToken
            consume(currentToken.type)
            node = if (token.type == TokenType.PLUS) {
                Sum(node, term())
            } else {
                Difference(node, term())
            }
        }
        return node
    }

    fun parse(): Expression {
        val node = expression()
        if (currentToken.type != TokenType.EOI) {
            throw SyntaxException(currentToken.value!!)
        }
        return node
    }
}