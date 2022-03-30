package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.dto.CommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExitFinishedTest {
    private final ChessGame chessGame = new ChessGame(new InitBoardStrategy());
    private AbstractState abstractState;

    @BeforeEach
    void setup() {
        chessGame.start();
        abstractState = new ExitFinished(chessGame);
    }


    @Test
    @DisplayName("execute()를 호출 시 예외처리가 되어있다.")
    void executeError() {
        assertThatThrownBy(() -> abstractState.execute(new CommandDto("status")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("isRun()이 false 를 리턴한다.")
    void isRun() {
        assertThat(abstractState.isRun())
                .isFalse();
    }

    @Test
    @DisplayName("해당 상태가 Status 가 아님을 확인할 수 있다.")
    void isStatus() {
        assertThat(abstractState.isStatusFinished())
                .isFalse();
    }

    @Test
    @DisplayName("isPlay() 실행 시 false 를 리턴한다")
    void isPlay() {
        assertThat(abstractState.isPlay())
                .isFalse();
    }
}
