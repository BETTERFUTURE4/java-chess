package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Team;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.KingMoveStrategy;

public final class King extends Piece {
    public King(Team team) {
        super(new Name("K"), team, new KingMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        return moveStrategy.isValidateCanMove(team, from, to);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
