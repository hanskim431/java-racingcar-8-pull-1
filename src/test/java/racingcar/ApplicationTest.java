package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("공동 우승자를 쉼표로 구분하여 출력한다")
    void shouldPrintMultipleWinners_whenMultipleCarsWin() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni,jun", "1");
                assertThat(output()).contains("pobi : -", "woni : -", "jun : ", "최종 우승자 : pobi, woni");
            },
            MOVING_FORWARD, MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("여러 라운드를 진행하고 우승자를 출력한다")
    void shouldPrintWinner_whenMultipleRoundsArePlayed() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "3");
                assertThat(output()).contains("pobi : ---", "woni : -", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP,
            MOVING_FORWARD, STOP,
            MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    @DisplayName("실행 결과 메시지를 출력한다")
    void shouldPrintRaceStartMessage_whenGameStarts() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi", "1");
                assertThat(output()).contains("실행 결과");
            },
            MOVING_FORWARD
        );
    }

    @Test
    @DisplayName("빈 자동차 이름이 있으면 예외가 발생한다")
    void shouldThrowException_whenEmptyCarNameExists() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,,woni", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("시도 횟수가 0이면 예외가 발생한다")
    void shouldThrowException_whenAttemptCountIsZero() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,woni", "0"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("시도 횟수가 음수면 예외가 발생한다")
    void shouldThrowException_whenAttemptCountIsNegative() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,woni", "-1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
