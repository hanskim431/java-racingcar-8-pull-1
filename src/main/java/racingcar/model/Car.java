package racingcar.model;

public class Car {
    private final static int NAME_LENGTH_LIMITS = 5;

    private final String name;
    private int position;

    private Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public static Car named(String name) {
        validateNameNotNull(name);
        String trimmedName = name.trim();
        validateName(trimmedName);
        return new Car(trimmedName);
    }

    private static void validateNameNotNull(String name) {
        final String ERROR_NULL_NAME = "자동차 이름은 null일 수 없습니다.";
        if (name == null) {
            throw new IllegalArgumentException(ERROR_NULL_NAME);
        }
    }

    private static void validateName(String name) {
        validateNameNotEmpty(name);
        validateNameLength(name);
    }

    private static void validateNameNotEmpty(String name) {
        final String ERROR_EMPTY_NAME = "자동차 이름은 비어있을 수 없습니다.";
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_NAME);
        }
    }

    private static void validateNameLength(String name) {
        final String ERROR_NAME_LENGTH = "자동차 이름은 5자 이하여야 합니다.";
        if (name.length() > NAME_LENGTH_LIMITS) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
    }

    public void move() {
        position++;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
