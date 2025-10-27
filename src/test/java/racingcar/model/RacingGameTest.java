package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RacingGameTest {

    @Test
    @DisplayName("Cars와 시도 횟수로 RacingGame을 생성할 수 있다")
    void shouldCreateRacingGame_whenValidInputIsGiven() {
        // given
        Cars cars = new Cars(Arrays.asList(Car.named("pobi"), Car.named("woni")));
        int attempts = 5;

        // when
        RacingGame game = RacingGame.of(cars, attempts);

        // then
        assertThat(game).isNotNull();
        assertThat(game.getAttempts()).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -5, -10, -100})
    @DisplayName("0 혹은 음수 시도 횟수로 생성하면 예외가 발생한다")
    void shouldThrowException_whenAttemptsIsZeroOrNegative(int negativeAttempts) {
        // given
        Cars cars = new Cars(List.of(Car.named("pobi")));

        // when & then
        assertThatThrownBy(() -> RacingGame.of(cars, negativeAttempts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("getCars는 생성 시 전달한 Cars 객체를 반환한다")
    void shouldReturnCars_whenGetCarsIsCalled() {
        // given
        Cars cars = new Cars(List.of(Car.named("pobi")));
        RacingGame game = RacingGame.of(cars, 5);

        // when
        Cars result = game.getCars();

        // then
        assertThat(result).isEqualTo(cars);
    }

    @Test
    @DisplayName("모든 자동차가 같은 위치에 있으면 모두 우승자다")
    void shouldReturnAllWinners_whenAllCarsHaveSamePosition() {
        // given
        Car car1 = Car.named("pobi");
        Car car2 = Car.named("woni");
        Car car3 = Car.named("jun");
        car1.move();
        car2.move();
        car3.move();
        Cars cars = new Cars(Arrays.asList(car1, car2, car3));
        RacingGame game = RacingGame.of(cars, 5);

        // when
        List<String> winners = game.findWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("단독 우승자를 찾을 수 있다")
    void shouldFindSingleWinner_whenOneCarIsAhead() {
        // given
        Car car1 = Car.named("pobi");
        Car car2 = Car.named("woni");
        Car car3 = Car.named("jun");
        car1.move();
        Cars cars = new Cars(Arrays.asList(car1, car2, car3));
        RacingGame game = RacingGame.of(cars, 5);

        // when
        List<String> winners = game.findWinners();

        // then
        assertThat(winners).containsExactly("pobi");
    }

    @Test
    @DisplayName("공동 우승자를 찾을 수 있다")
    void shouldFindMultipleWinners_whenMultipleCarsAreTied() {
        // given
        Car car1 = Car.named("pobi");
        Car car2 = Car.named("woni");
        Car car3 = Car.named("jun");
        car1.move();
        car1.move();
        car2.move();
        car2.move();
        car3.move();
        Cars cars = new Cars(Arrays.asList(car1, car2, car3));
        RacingGame game = RacingGame.of(cars, 5);

        // when
        List<String> winners = game.findWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
    }

    @Test
    @DisplayName("getAttempts는 생성 시 전달한 시도 횟수를 반환한다")
    void shouldReturnAttempts_whenGetAttemptsIsCalled() {
        // given
        Cars cars = new Cars(List.of(Car.named("pobi")));
        int expectedAttempts = 10;
        RacingGame game = RacingGame.of(cars, expectedAttempts);

        // when
        int actualAttempts = game.getAttempts();

        // then
        assertThat(actualAttempts).isEqualTo(expectedAttempts);
    }
}