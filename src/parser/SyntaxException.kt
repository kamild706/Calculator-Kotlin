package parser

import lexer.Token
import lexer.TokenType

class SyntaxException(token: Token, vararg expected: TokenType) : RuntimeException(
        "Found token '${token.value}' at position ${token.position + 1} " +
                "but expected ${expected.joinToString(" OR ")}")