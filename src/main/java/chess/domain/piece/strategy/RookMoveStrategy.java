package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.List;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        List<Direction> directions = Direction.rookDirection(color);

        if (!isValidDirection(from, to, directions)) {
            throw new IllegalArgumentException("룩이 이동할 수 없는 위치입니다.");
        }

        return true;
    }

    private boolean isValidDirection(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .anyMatch(direction -> direction.isSameDirection(from, to));
    }
}