package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.model.Cars;

class OutputViewTest {

    private OutputView outputView;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputView = new OutputView();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("경주 시작 메시지를 출력한다")
    void shouldPrintRaceStartMessage_whenPrintRaceStartIsCalled() {
        // when
        outputView.printRaceStart();

        // then
        String output = outputStream.toString();
        assertThat(output).contains("실행 결과");
    }

    @Test
    @DisplayName("경주 시작 메시지 전에 빈 줄을 출력한다")
    void shouldPrintEmptyLineBeforeMessage_whenPrintRaceStartIsCalled() {
        // when
        outputView.printRaceStart();

        // then
        String output = outputStream.toString();
        assertThat(output).startsWith(System.lineSeparator());
    }

    @Test
    @DisplayName("라운드 결과를 출력한다")
    void shouldPrintRoundResult_whenCarsAreGiven() {
        // given
        Car car1 = Car.named("pobi");
        car1.move();
        car1.move();
        Car car2 = Car.named("woni");
        car2.move();
        Cars cars = new Cars(Arrays.asList(car1, car2));

        // when
        outputView.printRoundResult(cars);

        // then
        String output = outputStream.toString();
        assertThat(output).contains("pobi : --");
        assertThat(output).contains("woni : -");
    }

    @Test
    @DisplayName("position이 0인 자동차는 이름과 콜론만 출력한다")
    void shouldPrintNameAndColonOnly_whenPositionIsZero() {
        // given
        Car car = Car.named("pobi");
        Cars cars = new Cars(List.of(car));

        // when
        outputView.printRoundResult(cars);

        // then
        String output = outputStream.toString();
        assertThat(output).contains("pobi : ");
        assertThat(output).doesNotContain("-");
    }

    @Test
    @DisplayName("라운드 결과 출력 후 빈 줄을 출력한다")
    void shouldPrintEmptyLineAfterResult_whenPrintRoundResultIsCalled() {
        // given
        Car car = Car.named("pobi");
        Cars cars = new Cars(List.of(car));

        // when
        outputView.printRoundResult(cars);

        // then
        String output = outputStream.toString();
        assertThat(output).endsWith(System.lineSeparator() + System.lineSeparator());
    }

    @Test
    @DisplayName("단독 우승자를 출력한다")
    void shouldPrintSingleWinner_whenOneWinnerExists() {
        // given
        List<String> winners = List.of("pobi");

        // when
        outputView.printWinners(winners);

        // then
        String output = outputStream.toString().trim();
        assertThat(output).isEqualTo("최종 우승자 : pobi");
    }

    @Test
    @DisplayName("공동 우승자를 쉼표로 구분하여 출력한다")
    void shouldPrintMultipleWinners_whenMultipleWinnersExist() {
        // given
        List<String> winners = Arrays.asList("pobi", "woni", "jun");

        // when
        outputView.printWinners(winners);

        // then
        String output = outputStream.toString().trim();
        assertThat(output).isEqualTo("최종 우승자 : pobi, woni, jun");
    }
}
