package parser

import java.math.BigDecimal

class Power(leftOperand: Expression, rightOperand: Expression) : BinaryOperation(leftOperand, rightOperand) {

    override fun evaluate(): BigDecimal {
        val base = leftOperand.evaluate().toDouble()
        val exp = rightOperand.evaluate().toDouble()
        return BigDecimal.valueOf(Math.pow(base, exp))
    }

    override fun getOperator(): String {
        return "^"
    }
}