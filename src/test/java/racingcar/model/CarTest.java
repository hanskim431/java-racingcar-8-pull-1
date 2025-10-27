package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @Test
    @DisplayName("유효한 이름으로 자동차를 생성할 수 있다")
    void shouldCreateCar_whenValidNameIsGiven() {
        // given
        String validName = "pobi";

        // when
        Car car = Car.named(validName);

        // then
        assertThat(car).isNotNull();
        assertThat(car.getName()).isEqualTo(validName);
    }

    @Test
    @DisplayName("5자 이하의 이름으로 자동차를 생성할 수 있다")
    void shouldCreateCar_whenNameLengthIsFiveOrLess() {
        // given
        String fiveCharName = "12345";

        // when
        Car car = Car.named(fiveCharName);

        // then
        assertThat(car).isNotNull();
        assertThat(car.getName()).isEqualTo(fiveCharName);
    }

    @Test
    @DisplayName("null로 자동차를 생성하면 예외가 발생한다")
    void shouldThrowException_whenNameIsNull() {
        // when & then
        assertThatThrownBy(() -> Car.named(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 null일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "              "})
    @DisplayName("공백 문자열로 자동차를 생성하면 예외가 발생한다")
    void shouldThrowException_whenNameHasOnlySpace(String invalidName) {
        // when & then
        assertThatThrownBy(() -> Car.named(invalidName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 비어있을 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "verylongname", "123456"})
    @DisplayName("5자를 초과하는 다양한 이름으로 생성 시 예외가 발생한다")
    void shouldThrowException_whenNameIsTooLong(String longName) {
        // when & then
        assertThatThrownBy(() -> Car.named(longName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5자 이하여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcd", "abcde"})
    @DisplayName("1자부터 5자까지의 이름은 모두 유효하다")
    void shouldCreateCar_whenNameLengthIsOneToFive(String validName) {
        // when
        Car car = Car.named(validName);

        // then
        assertThat(car).isNotNull();
        assertThat(car.getName()).isEqualTo(validName);
    }

    @Test
    @DisplayName("공백을 포함하여 5자를 초과하면 예외가 발생한다")
    void shouldThrowException_whenNameWithWhitespaceExceedsFive() {
        // given
        String nameWithSpaces = " 123456 ";

        // when & then
        assertThatThrownBy(() -> Car.named(nameWithSpaces))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5자 이하여야 합니다.");
    }

    @Test
    @DisplayName("한글 이름으로 자동차를 생성할 수 있다")
    void shouldCreateCar_whenNameIsKorean() {
        // given
        String koreanName = "포비";

        // when
        Car car = Car.named(koreanName);

        // then
        assertThat(car).isNotNull();
        assertThat(car.getName()).isEqualTo(koreanName);
    }

    @Test
    @DisplayName("숫자로만 이루어진 이름으로 자동차를 생성할 수 있다")
    void shouldCreateCar_whenNameIsNumeric() {
        // given
        String numericName = "12345";

        // when
        Car car = Car.named(numericName);

        // then
        assertThat(car).isNotNull();
        assertThat(car.getName()).isEqualTo(numericName);
    }

    @Test
    @DisplayName("getName은 생성 시 입력한 이름을 반환한다")
    void shouldReturnGivenName_whenGetNameIsCalled() {
        // given
        String expectedName = "pobi";
        Car car = Car.named(expectedName);

        // when
        String actualName = car.getName();

        // then
        assertThat(actualName).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("앞뒤 공백이 제거된 이름을 반환한다")
    void shouldReturnTrimmedName_whenNameHasWhitespace() {
        // given
        Car car = Car.named(" pobi ");

        // when
        String name = car.getName();

        // then
        assertThat(name).isEqualTo("pobi");
    }

    @Test
    @DisplayName("초기 position은 0이다")
    void shouldHaveInitialPositionZero_whenCarIsCreated() {
        // given & when
        Car car = Car.named("pobi");

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("move 메서드 호출 시 position이 1 증가한다")
    void shouldIncreasePositionByOne_whenMoveIsCalled() {
        // given
        Car car = Car.named("pobi");
        int initialPosition = car.getPosition();

        // when
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(initialPosition + 1);
    }

    @Test
    @DisplayName("move 메서드를 여러 번 호출하면 position이 누적된다")
    void shouldAccumulatePosition_whenMoveIsCalledMultipleTimes() {
        // given
        Car car = Car.named("pobi");

        // when
        car.move();
        car.move();
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(3);
    }
}