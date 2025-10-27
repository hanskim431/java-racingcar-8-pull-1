package racingcar.config;

import racingcar.controller.RacingGameController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {

    private InputView inputView;
    private OutputView outputView;

    public RacingGameController racingGameController() {
        return new RacingGameController(inputView(), outputView());
    }

    private InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    private OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }
}
