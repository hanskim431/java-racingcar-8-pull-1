package racingcar.model;

public class Car {
    private final static int NAME_LENGTH_LIMITS = 5;

    private String name;
    private int position;

    private Car() {}

    private Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public static Car named(String name) {
        validateName(name);
        return new Car(name);
    }

    private static void validateName(String name) {
        validateNameNotEmpty(name);
        validateNameLength(name);
    }

    private static void validateNameNotEmpty(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }
    }

    private static void validateNameLength(String name) {
        if (name.length() > NAME_LENGTH_LIMITS) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }
    }
}
