package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarsTest {

    @Test
    @DisplayName("한 대의 자동차로 Cars 객체를 생성할 수 있다")
    void shouldCreateCars_whenSingleCarIsGiven() {
        // given
        List<Car> singleCar = List.of(Car.named("pobi"));

        // when
        Cars cars = new Cars(singleCar);

        // then
        assertThat(cars).isNotNull();
    }

    @Test
    @DisplayName("자동차 리스트로 Cars 객체를 생성할 수 있다")
    void shouldCreateCars_whenValidCarListIsGiven() {
        // given
        List<Car> carList = Arrays.asList(
                Car.named("pobi"),
                Car.named("woni"),
                Car.named("jun")
        );

        // when
        Cars cars = new Cars(carList);

        // then
        assertThat(cars).isNotNull();
    }

    @Test
    @DisplayName("Cars는 입력된 순서대로 자동차를 반복한다")
    void shouldMaintainInsertionOrder_whenIterating() {
        // given
        List<Car> carList = Arrays.asList(
                Car.named("pobi"),
                Car.named("woni"),
                Car.named("jun")
        );
        Cars cars = new Cars(carList);

        // when
        List<String> names = new ArrayList<>();
        for (Car car : cars) {
            names.add(car.getName());
        }

        // then
        assertThat(names).containsExactly("pobi", "woni", "jun");
    }
}