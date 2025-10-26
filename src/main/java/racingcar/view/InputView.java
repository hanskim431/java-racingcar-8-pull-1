package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputCarNames() {
        final String REQUEST_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
        System.out.println(REQUEST_CAR_NAMES_MESSAGE);
        return Console.readLine();
    }

    public String inputAttemptCount() {
        final String REQUEST_ATTEMPT_COUNT_MESSAGE = "시도할 횟수는 몇 회인가요?";
        System.out.println(REQUEST_ATTEMPT_COUNT_MESSAGE);
        return Console.readLine();
    }
}
