package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;

class CarNameParserTest {

    @Test
    @DisplayName("쉼표로 구분된 문자열을 자동차 리스트로 변환한다")
    void shouldParseCars_whenValidInputIsGiven() {
        // given
        String input = "pobi,woni,jun";

        // when
        List<Car> cars = CarNameParser.parse(input);

        // then
        assertThat(cars).hasSize(3);
        assertThat(cars.get(0).getName()).isEqualTo("pobi");
        assertThat(cars.get(1).getName()).isEqualTo("woni");
        assertThat(cars.get(2).getName()).isEqualTo("jun");
    }

    @Test
    @DisplayName("이름의 앞뒤 공백을 제거하여 파싱한다")
    void shouldTrimWhitespace_whenNamesHaveSpaces() {
        // given
        String input = " pobi , woni , jun ";

        // when
        List<Car> cars = CarNameParser.parse(input);

        // then
        assertThat(cars).hasSize(3);
        assertThat(cars.get(0).getName()).isEqualTo("pobi");
        assertThat(cars.get(1).getName()).isEqualTo("woni");
        assertThat(cars.get(2).getName()).isEqualTo("jun");
    }

    @Test
    @DisplayName("5자를 초과하는 이름이 있으면 예외가 발생한다")
    void shouldThrowException_whenAnyNameExceedsFiveCharacters() {
        // given
        String input = "pobi,verylongname,jun";

        // when & then
        assertThatThrownBy(() -> CarNameParser.parse(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5자 이하여야 합니다.");
    }

    @Test
    @DisplayName("빈 이름이 있으면 예외가 발생한다")
    void shouldThrowException_whenEmptyNameExists() {
        // given
        String input = "pobi,,jun";

        // when & then
        assertThatThrownBy(() -> CarNameParser.parse(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("공백만 있는 이름이 있으면 예외가 발생한다")
    void shouldThrowException_whenBlankNameExists() {
        // given
        String input = "pobi,   ,jun";

        // when & then
        assertThatThrownBy(() -> CarNameParser.parse(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 비어있을 수 없습니다.");
    }
}
