package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {
    private final Board board;

    public BoardDto(Board board) {
        this.board = board;
    }

    public String getBoardString() {
        StringBuilder result = new StringBuilder();
        Map<Position, Piece> squares = board.getSquares();
        List<Rank> ranks = Rank.reverseRanks();
        for (Rank rank : ranks) {
            result.append(ChessBoardOfRankToString(squares, rank));
        }
        return result.toString();
    }

    private String ChessBoardOfRankToString(Map<Position, Piece> squares, Rank rank) {
        StringBuilder result = new StringBuilder();
        for (Column column : Column.values()) {
            Piece piece = squares.get(new Position(column, rank));
            result.append(piece.getName().getValue(piece.getTeam()));
        }
        return result + System.lineSeparator();
    }

    public static List<String> getAblePositions() {
        List<String> positions = new ArrayList<>();
        for (Column column : Column.values()) {
            addPositionOfColumn(positions, column);
        }
        return positions;
    }

    private static void addPositionOfColumn(List<String> positions, Column column) {
        for (Rank rank : Rank.values()) {
            positions.add(column.name() + rank.getNumber());
        }
    }
}
