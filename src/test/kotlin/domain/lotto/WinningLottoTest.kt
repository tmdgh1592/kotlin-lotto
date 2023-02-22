package domain.lotto

import domain.lotto.number.LottoNumber
import domain.result.LottoMatchResult
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("provideWinningLottoAndPurchasedLottoAndMatchedResult")
    fun `구매한 로또 한 개가 주어졌을 때, 호출 시, 일치하는 번호 개수와 보너스 번호 일치 여부를 반환한다`(
        winningLotto: WinningLotto,
        bonusNumber: LottoNumber,
        purchasedLotto: PurchasedLotto,
        matchedResult: LottoMatchResult,
    ) {
        val expected = winningLotto.matchLotto(purchasedLotto, bonusNumber)
        Assertions.assertThat(expected).isEqualTo(matchedResult)
    }

    @ParameterizedTest
    @MethodSource("provideWinningLottoAndNotContainedBonusNumber")
    fun `당첨번호와 6개의 당첨번호에 포함되지 않는 보너스 번호가 주어졌을 때, LottoTicket 객체 생성시, 예외가 발생하지 않는다`(
        lotto: Lotto,
        bonusNumber: LottoNumber,
    ) {
        assertDoesNotThrow {
            WinningLotto(lotto, bonusNumber)
        }
    }

    @ParameterizedTest
    @MethodSource("provideWinningLottoAndContainedBonusNumber")
    fun `당첨번호와 6개의 당첨번호에 포함되는 보너스 번호가 주어졌을 때, WinningLotto 객체 생성시, IllegalStateException이 발생한다`(
        lotto: Lotto,
        bonusNumber: LottoNumber,
    ) {
        assertThrows<IllegalStateException> {
            WinningLotto(lotto, bonusNumber)
        }
    }

    companion object {
        @JvmStatic
        fun provideWinningLottoAndPurchasedLottoAndMatchedResult(): List<Arguments> =
            listOf(
                Arguments.of(
                    WinningLotto(Lotto((1..6).map { number -> LottoNumber(number) }.toSet()), LottoNumber(10)),
                    LottoNumber(10),
                    PurchasedLotto((1..6).map { number -> LottoNumber(number) }.toSet()),
                    LottoMatchResult(6, false)
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }.toSet()),
                        LottoNumber(10)
                    ),
                    LottoNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) }.toSet()),
                    LottoMatchResult(5, true)
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }.toSet()),
                        LottoNumber(10)
                    ),
                    LottoNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 8, 12, 45).map { number -> LottoNumber(number) }.toSet()),
                    LottoMatchResult(5, false)
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }.toSet()),
                        LottoNumber(10)
                    ),
                    LottoNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 8, 44, 45).map { number -> LottoNumber(number) }.toSet()),
                    LottoMatchResult(4, false)
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }.toSet()),
                        LottoNumber(10)
                    ),
                    LottoNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 43, 44, 45).map { number -> LottoNumber(number) }.toSet()),
                    LottoMatchResult(3, false)
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }.toSet()),
                        LottoNumber(10)
                    ),
                    LottoNumber(10),
                    PurchasedLotto(listOf(2, 4, 42, 43, 44, 45).map { number -> LottoNumber(number) }.toSet()),
                    LottoMatchResult(2, false)
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }.toSet()),
                        LottoNumber(10)
                    ),
                    LottoNumber(10),
                    PurchasedLotto(listOf(2, 41, 42, 43, 44, 45).map { number -> LottoNumber(number) }.toSet()),
                    LottoMatchResult(1, false)
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }.toSet()),
                        LottoNumber(10)
                    ),
                    LottoNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 10, 44, 45).map { number -> LottoNumber(number) }.toSet()),
                    LottoMatchResult(3, true)
                )
            )

        @JvmStatic
        fun provideWinningLottoAndNotContainedBonusNumber(): List<Arguments> =
            listOf(
                Arguments.of((1..6).map { number -> LottoNumber(number) }, LottoNumber(7)),
                Arguments.of(
                    listOf(1, 3, 5, 7, 8, 9).map { number -> LottoNumber(number) },
                    LottoNumber(45)
                ),
                Arguments.of(
                    listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) },
                    LottoNumber(3)
                )
            )

        @JvmStatic
        fun provideWinningLottoAndContainedBonusNumber(): List<Arguments> =
            listOf(
                Arguments.of((1..6).map { number -> LottoNumber(number) }, LottoNumber(6)),
                Arguments.of(
                    listOf(1, 3, 5, 7, 8, 9).map { number -> LottoNumber(number) },
                    LottoNumber(8)
                ),
                Arguments.of(
                    listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) },
                    LottoNumber(12)
                )
            )
    }
}
