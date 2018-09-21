package parser

import lexer.MathLexer
import lexer.TokenType
import parser.mathexpression.*

class MathParser(lexer: MathLexer) : Parser(lexer) {

    private fun factor(): Expression {
        if (lookahead.type == TokenType.NUMBER) {
            val token = lookahead
            match(TokenType.NUMBER)
            return Constant(token)
        } else if (lookahead.type == TokenType.LEFT_PARENTHESIS) {
            match(TokenType.LEFT_PARENTHESIS)
            val node = expression()
            match(TokenType.RIGHT_PARENTHESIS)
            return node
        }
        throw SyntaxException(lookahead, TokenType.NUMBER, TokenType.LEFT_PARENTHESIS)
    }

    private fun power(): Expression {
        if (lookahead.type == TokenType.MINUS) {
            match(TokenType.MINUS)
            var node = power()
            while (lookahead.type == TokenType.POW) {
                match(TokenType.POW)
                node = Power(node, power())
            }
            return Negation(node)
        } else {
            var node = factor()
            while (lookahead.type == TokenType.POW) {
                match(TokenType.POW)
                node = Power(node, power())
            }
            return node
        }
    }

    private fun term(): Expression {
        var node = power()
        while (lookahead.type in arrayOf(TokenType.TIMES, TokenType.DIV)) {
            val token = lookahead
            match(lookahead.type)
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
        while (lookahead.type in arrayOf(TokenType.PLUS, TokenType.MINUS)) {
            val token = lookahead
            match(lookahead.type)
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
        if (lookahead.type != TokenType.END_OF_INPUT) {
            throw SyntaxException(lookahead, TokenType.END_OF_INPUT)
        }
        return node
    }
}