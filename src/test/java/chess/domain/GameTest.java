package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    @DisplayName("Game점수 전체 계산")
    @Test
    void calculateGamePoint_int() {
        Game game = new Game();
        game.init();

        double whitePoint = game.computeWhitePoint();
        double blackPoint = game.computeBlackPoint();

        assertEquals(whitePoint, 38);
        assertEquals(blackPoint, 38);
    }

    @DisplayName("Game Pawn이 여러개 있을 때의 점수 전체 계산")
    @Test
    void calculateGamePawnPoint_int() {
        Game game = new Game();
        game.init();
        Board board = game.getBoard();
        Piece pawnPiece = new Piece(PieceKind.PAWN, PieceColor.WHITE);
        Position firstPawnPosition = Position.of('c', 3);
        Position secondPawnPosition = Position.of('c', 4);
        board.putPieceAtPosition(firstPawnPosition, pawnPiece);
        board.putPieceAtPosition(secondPawnPosition, pawnPiece);

        double whitePoint = game.computeWhitePoint();

        assertEquals(whitePoint, 38.5);
    }

    @DisplayName("게임 차례 위반 테스트")
    @Test
    void judgeTurn_ThrownError() {
        Game game = new Game();
        game.init();

        assertThatThrownBy(() -> game.move("e7", "e6"))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Board.INVALID_TURN_MESSAGE);
    }

    @DisplayName("게임 승패 알아보는 테스트")
    @Test
    void judgeWinner_PieceColor() {
        Game game = new Game();
        game.init();
        Board board = game.getBoard();
        Piece queenPiece = new Piece(PieceKind.QUEEN, PieceColor.WHITE);
        Position checkMatePosition = Position.of('e', 7);
        game.move("a2", "a3");
        game.move("e7", "e6");
        board.putPieceAtPosition(checkMatePosition, queenPiece);
        game.move("e7", "e8");

        PieceColor winnerColor = game.winnerColor();

        assertEquals(winnerColor, PieceColor.WHITE);
    }
}