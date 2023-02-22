package domain.money

import util.common.constant.ERROR_PREFIX

data class Money(val amount: Int) {
    init {
        require(amount >= MIN_AMOUNT) { ERROR_MESSAGE_NEGATIVE_AMOUNT }
    }

    fun isGreaterThan(other: Money): Boolean = (this.amount >= other.amount)

    operator fun div(divisor: Money) = amount.div(divisor.amount)

    operator fun minus(other: Money): Money = Money(this.amount - other.amount)

    companion object {
        private const val MIN_AMOUNT = 0
        private const val ERROR_MESSAGE_NEGATIVE_AMOUNT = "$ERROR_PREFIX 금액은 0보다 크거나 같은 값이어야 합니다."
    }
}
