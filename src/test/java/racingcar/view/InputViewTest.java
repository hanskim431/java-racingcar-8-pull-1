package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

    private InputView inputView;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        Console.close();
    }

    @Test
    @DisplayName("자동차 이름 입력 메시지를 출력하고 입력값을 반환한다")
    void shouldReturnInputCarNames_whenInputCarNamesIsCalled() {
        // given
        String input = "pobi,woni,jun";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // when
        String result = inputView.inputCarNames();

        // then
        String output = outputStream.toString();
        assertThat(output).contains("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        assertThat(result).isEqualTo(input);
    }

    @Test
    @DisplayName("시도 횟수 입력 메시지를 출력하고 입력값을 반환한다")
    void shouldReturnAttemptCount_whenInputAttemptCountIsCalled() {
        // given
        String input = "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // when
        String result = inputView.inputAttemptCount();

        // then
        String output = outputStream.toString();
        assertThat(output).contains("시도할 횟수는 몇 회인가요?");
        assertThat(result).isEqualTo(input);
    }
}