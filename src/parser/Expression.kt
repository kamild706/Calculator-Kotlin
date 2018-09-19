package parser

import java.math.BigDecimal

interface Expression {

    fun evaluate(): BigDecimal
    fun printStructure(indentation: Int)
}