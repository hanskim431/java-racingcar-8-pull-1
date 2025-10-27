package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RacingGameTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10, 100, 10000})
    @DisplayName("1 이상의 시도 횟수로 RacingGame을 생성할 수 있다")
    void shouldCreateRacingGame_whenAttemptsIsPositive(int attempts) {
        // when
        RacingGame racingGame = RacingGame.fromAttempts(attempts);

        // then
        assertThat(racingGame).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -5, -10, -100})
    @DisplayName("0 혹은 음수 시도 횟수로 생성하면 예외가 발생한다")
    void shouldThrowException_whenAttemptsIsZeroOrNegative(int negativeAttempts) {
        // when & then
        assertThatThrownBy(() -> RacingGame.fromAttempts(negativeAttempts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수는 1 이상이어야 합니다.");
    }
}