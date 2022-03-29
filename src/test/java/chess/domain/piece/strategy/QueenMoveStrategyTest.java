package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.attribute.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenMoveStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "D,ONE,D,FIVE",
            "D,ONE,B,THREE",
            "D,ONE,F,THREE",
            "D,THREE,A,THREE",
            "D,THREE,B,ONE",
            "D,FIVE,D,ONE",
            "D,THREE,F,ONE",
            "D,THREE,F,THREE"
    })
    @DisplayName("퀸이 갈 수 있는 위치 중 하나여야 한다.")
    void canValidMove(Column columnA, Rank rankA, Column columnB, Rank rankB) {
        assertDoesNotThrow(() -> new QueenMoveStrategy()
                .isValidateCanMove(Team.WHITE,
                        new Position(columnA, rankA),
                        new Position(columnB, rankB)
                )
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A,ONE,C,TWO",
            "C,TWO,E,ONE"
    })
    @DisplayName("퀸이 갈 수 있는 위치가 아니면 에러가 발생한다.")
    void canInvalidMove(Column columnA, Rank rankA, Column columnB, Rank rankB) {
        assertThatThrownBy(() -> new QueenMoveStrategy()
                .isValidateCanMove(Team.WHITE,
                        new Position(columnA, rankA),
                        new Position(columnB, rankB)
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸이 이동할 수 없는 위치입니다.");
    }
}
