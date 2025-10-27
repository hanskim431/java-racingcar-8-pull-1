package racingcar.model;

public class RacingGame {

    private final int attempts;

    private RacingGame() {
        attempts = 0;
    }

    private RacingGame(int attempts) {
        this.attempts = attempts;
    }

    public static RacingGame fromAttempts(int attempts) {
        validateAttempts(attempts);
        return new RacingGame(attempts);
    }

    private static void validateAttempts(int attempts) {
        if (attempts <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }
}
