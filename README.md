# 1단계 - 로또 (자동)

## 기능 목록

### 입력
- [x] 구매금액을 입력받는다.
- [x] 지난 주 당첨 번호를 입력받는다.
- [x] 보너스 볼을 입력받는다.

### 출력
- [x] 발급된 로또 번호를 출력한다.
- [x] 당첨 통계를 출력한다.
- [x] 수익금을 출력한다.

### 도메인
- [x] 중복되지 않는 로또 번호 6개를 랜덤으로 생성한다.
- [x] 입력받은 구매금액 만큼 구매 로또를 생성한다.
  - [x] 로또 번호는 중복이 없다.
  - [x] 로또 번호는 6개여야 한다.
  - [x] 로또 번호의 범위는 1~45 여야 한다.
- [x] 입력받은 당첨 번호를 통해 당첨 로또를 생성한다.
  - [x] 보너스 번호가 포함 되지 않는다. 
  - [x] 로또 번호는 6개여야 한다.
  - [x] 로또 번호의 범위는 1~45 여야 한다.
- [x] 구매한 로또 번호를 오름차순으로 정렬한다.
- [x] 입력받은 보너스 번호를 통해 당첨 보너스 번호 생성한다.
- [x] 주어진 수만큼 로또를 생성을 반복한다.
- [x] 구매한 로또 번호와 당첨 번호를 비교한다.
- [x] 보너스 번호가 포함되어 있는지 확인한다.
- [x] 보너스 번호가 포함되어 있지 않고, 6개의 숫자가 일치하면 1등이다.
- [x] 보너스 번호가 포함되어 있고 5개의 숫자가 일치하면 2등이다.
- [x] 보너스 번호가 포함되어 있지 않고, 5개 숫자가 일치하면 3등이다.
- [x] 보너스 번호가 포함되어 있지 않고, 4개 숫자가 일치하면 4등이다.
- [x] 보너스 번호가 포함되어 있지 않고, 3개 숫자가 일치하면 5등이다.
- [x] 등수에 따라 당첨금을 계산한다.
- [x] 당첨된 로또의 총 수입금을 계산한다.
- [x] 수익률을 계산한다.

### 유틸리티
- [x] 중복되지 않는 숫자 6개를 생성한다.
- [x] 정수를 1000으로 나눈 몫을 반환한다.
  - [x] 0미만일 경우 IllegalArgumentException이 발생한다.

### 리팩터링
- [x] divideByThousand() Exception 메시지 일반화한다.
- [x] Money 클래스로 원시값을 포장한다.
  - [x] 금액을 1000원으로 나눈 몫을 반환하는 기능 구현한다.
- [x] judgeEitherSecondOrThird() 특정 등수에 구애받지 않도록 변경한다.
- [x] enum의 values()를 활용하여, when을 사용하지 않고 코드를 개선한다.
- [x] validateNumeric(), validateWinningNumbersIsNumeric()와 같은 domain 요구사항을 domain 모델에서 처리하도록 수정한다.
- [x] LottoController에서 멤버 변수를 사용하지 않는 구조로 변경한다.
- [x] initWinningLottoNumbers()를 호출되지 않은 상태에서 matchLottos()를 호출할 수 있도록 변경한다.
- [x] RankTest에서 로또 숫자 일치 상수값을 Rank의 enum값으로 변경한다.
