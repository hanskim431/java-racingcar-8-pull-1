package racingcar.util;

import java.util.Arrays;
import java.util.List;
import racingcar.model.Car;

public class CarNameParser {
    private static final String DELIMITER = ",";

    public static List<Car> parse(String input) {
        List<Car> list = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(Car::named)
                .toList();
        return list;
    }
}
