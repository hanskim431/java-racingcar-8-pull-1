package racingcar.controller;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.RacingGame;
import racingcar.util.CarNameParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String carNamesInput = inputView.inputCarNames();
        List<Car> carList = CarNameParser.parse(carNamesInput);
        Cars cars = new Cars(carList);

        String attemptCountInput = inputView.inputAttemptCount();
        int attemptCount = Integer.parseInt(attemptCountInput);

        RacingGame game = RacingGame.of(cars, attemptCount);

        outputView.printRaceStart();

        for (int i = 0; i < game.getAttempts(); i++) {
            game.playRound();
            outputView.printRoundResult(game.getCars());
        }

        List<String> winners = game.findWinners();
        outputView.printWinners(winners);
    }
}
