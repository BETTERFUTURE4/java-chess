package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.attribute.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractPieceTest {

    @Test
    @DisplayName("체스 말을 생성할 수 있다.")
    void createPiece() {
        AbstractPiece blackAbstractPiece = new Pawn(Team.BLACK);
        AbstractPiece whiteAbstractPiece = new Pawn(Team.WHITE);

        assertAll(
                () -> assertThat(blackAbstractPiece.getTeam()).isEqualTo(Team.BLACK),
                () -> assertThat(whiteAbstractPiece.getTeam()).isEqualTo(Team.WHITE)
        );
    }

    @Test
    @DisplayName("체스 말중 폰을 생성할 수 있다.")
    void createPawn() {
        AbstractPiece pawn = new Pawn(Team.BLACK);

        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("체스 말중 룩을 생성할 수 있다.")
    void createRook() {
        AbstractPiece rook = new Rook(Team.BLACK);

        assertThat(rook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("체스 말중 비숍을 생성할 수 있다.")
    void createBishop() {
        AbstractPiece bishop = new Bishop(Team.BLACK);

        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("체스 말중 킹을 생성할 수 있다.")
    void createKing() {
        AbstractPiece king = new King(Team.BLACK);

        assertThat(king).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("체스 말중 퀸을 생성할 수 있다.")
    void createQueen() {
        AbstractPiece queen = new Queen(Team.BLACK);

        assertThat(queen).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("체스 말중 나이트를 생성할 수 있다.")
    void createKnight() {
        AbstractPiece knight = new Knight(Team.BLACK);

        assertThat(knight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("체스 말이 킹인지 확인할 수 있다.")
    void testKingAlive() {
        AbstractPiece abstractPiece = new King(Team.WHITE);
        AbstractPiece rook = new Rook(Team.BLACK);

        assertAll(
                () -> assertThat(abstractPiece.isKing()).isTrue(),
                () -> assertThat(rook.isKing()).isFalse()
        );
    }
}