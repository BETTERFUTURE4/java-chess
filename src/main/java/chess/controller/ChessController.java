package chess.controller;

import chess.controller.commendexcuter.Start;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        new Start();
    }
}
