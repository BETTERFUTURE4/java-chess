package chess.domain.boardstrategy;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Team;
import java.util.HashMap;
import java.util.Map;

public class InjectBoardStrategy implements BoardStrategy {
    private static final Map<String, Piece> stringPieceMap = new HashMap<>();

    static {
        stringPieceMap.put(".", new EmptyPiece());
        stringPieceMap.put("R", new Rook(Team.BLACK));
        stringPieceMap.put("N", new Knight(Team.BLACK));
        stringPieceMap.put("B", new Bishop(Team.BLACK));
        stringPieceMap.put("Q", new Queen(Team.BLACK));
        stringPieceMap.put("K", new King(Team.BLACK));
        stringPieceMap.put("P", new Pawn(Team.BLACK));
        stringPieceMap.put("r", new Rook(Team.WHITE));
        stringPieceMap.put("n", new Knight(Team.WHITE));
        stringPieceMap.put("b", new Bishop(Team.WHITE));
        stringPieceMap.put("q", new Queen(Team.WHITE));
        stringPieceMap.put("k", new King(Team.WHITE));
        stringPieceMap.put("p", new Pawn(Team.WHITE));
    }

    private final Map<Position, Piece> positionPieceMap;

    public InjectBoardStrategy(String text) {
        this.positionPieceMap = stringToBoard(text);
    }

    public static Map<String, Piece> getStringPieceMap() {
        return stringPieceMap;
    }

    private static Map<Position, Piece> stringToBoard(String text) {
        Map<Position, Piece> board = new HashMap<>();
        int rankY = 8;
        for (String rank : text.split(" ")) {
            setBoardRank(board, rankY, rank);
            rankY--;
        }
        return board;
    }

    private static void setBoardRank(Map<Position, Piece> board, int rankY, String rank) {
        int fileX = 1;
        for (String pieceString : rank.split("")) {
            board.put(new Position(Column.numberOf(fileX), Rank.numberOf(rankY)),
                    stringPieceMap.get(pieceString));
            fileX++;
        }
    }

    @Override
    public Map<Position, Piece> create() {
        return positionPieceMap;
    }
}
