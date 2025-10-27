package racingcar.view;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.Cars;

public class OutputView {
    private static final String RACE_START_MESSAGE = "실행 결과";
    private static final String WINNER_MESSAGE_PREFIX = "최종 우승자 : ";
    private static final String POSITION_MARKER = "-";
    private static final String NAME_POSITION_SEPARATOR = " : ";
    private static final String WINNER_SEPARATOR = ", ";

    public void printRaceStart() {
        System.out.println();
        System.out.println(RACE_START_MESSAGE);
    }

    public void printRoundResult(Cars cars) {
        for (Car car : cars) {
            System.out.println(car.getName() + NAME_POSITION_SEPARATOR + POSITION_MARKER.repeat(car.getPosition()));
        }
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println(WINNER_MESSAGE_PREFIX + String.join(WINNER_SEPARATOR, winners));
    }
}
