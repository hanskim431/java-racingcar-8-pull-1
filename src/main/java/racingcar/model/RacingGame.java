package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RacingGame {
    private static final int MOVE_THRESHOLD = 4;
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;

    private final Cars cars;
    private final int attempts;

    private RacingGame(Cars cars, int attempts) {
        this.cars = cars;
        this.attempts = attempts;
    }

    public static RacingGame of(Cars cars, int attempts) {
        validateAttempts(attempts);
        return new RacingGame(cars, attempts);
    }

    private static void validateAttempts(int attempts) {
        final String ERROR_MINIMUM_ATTEMPTS = "시도 횟수는 1 이상이어야 합니다.";
        if (attempts <= 0) {
            throw new IllegalArgumentException(ERROR_MINIMUM_ATTEMPTS);
        }
    }

    public void playRound() {
        for (Car car : cars) {
            int randomNumber = Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
            if (randomNumber >= MOVE_THRESHOLD) {
                car.move();
            }
        }
    }

    public Cars getCars() {
        return cars;
    }

    public int getAttempts() {
        return attempts;
    }

    public List<String> findWinners() {
        int maxPosition = findMaxPosition();
        return findCarsWithPosition(maxPosition);
    }

    private int findMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }
        return maxPosition;
    }

    private List<String> findCarsWithPosition(int position) {
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == position) {
                winners.add(car.getName());
            }
        }
        return winners;
    }
}
