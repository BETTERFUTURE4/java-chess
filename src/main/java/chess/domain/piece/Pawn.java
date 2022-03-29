package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import chess.domain.piece.strategy.PawnMoveStrategy;

public final class Pawn extends AbstractPiece {

    public Pawn(Team team) {
        super(new Name("P"), team, new PawnMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        return moveStrategy.canMove(team, targetPiece, from, to);
    }
}
