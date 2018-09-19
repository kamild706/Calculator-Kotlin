package parser

import java.math.BigDecimal
import java.math.RoundingMode

class Quotient(leftOperand: Expression, rightOperand: Expression) : BinaryOperation(leftOperand, rightOperand) {

    override fun getOperator(): String {
        return "/"
    }

    override fun evaluate(): BigDecimal {
        return leftOperand.evaluate().divide(rightOperand.evaluate(), 3, RoundingMode.HALF_EVEN)
    }
}