package parser

import java.math.BigDecimal

class Difference(leftOperand: Expression, rightOperand: Expression) : BinaryOperation(leftOperand, rightOperand) {

    override fun getOperator(): String {
        return "-"
    }

    override fun evaluate(): BigDecimal {
        return leftOperand.evaluate().subtract(rightOperand.evaluate())
    }
}