package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.File;
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

public final class ChessGame {

    private static final String NO_TURN_MESSAGE = "현재 진영에 속해있지 않는 위치입니다.";

    private final Board board;
    private GameState gameState;
    private Team turn = Team.WHITE;

    public ChessGame() {
        this.board = new Board(createBoard());
        this.gameState = GameState.READY;
    }

    public void play(Position from, Position to) {
        if (!isTurn(from)) {
            throw new IllegalArgumentException(NO_TURN_MESSAGE);
        }

        boolean isKing = isKing(to);
        board.move(from, to);
        if (isKing) {
            gameState = GameState.END;
        }
        turn = turn.oppositeColor();
    }

    private boolean isTurn(Position position) {
        return board.isSameColor(position, turn);
    }

    public Status getStatus() {
        return new Status(board.getTotalStatus());
    }

    public boolean isKing(Position to) {
        return board.isKing(to);
    }

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> squares = new HashMap<>();

        initEmptyPieces(squares);
        initNotPawnSquares(squares, Rank.ONE, Team.WHITE);
        initPawnPieces(squares, Rank.TWO, Team.WHITE);
        initPawnPieces(squares, Rank.SEVEN, Team.BLACK);
        initNotPawnSquares(squares, Rank.EIGHT, Team.BLACK);

        return squares;
    }

    private void initEmptyPieces(Map<Position, Piece> squares) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                squares.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    private void initPawnPieces(Map<Position, Piece> squares, Rank rank, Team team) {
        for (File file : File.values()) {
            squares.replace(new Position(file, rank), new Pawn(team));
        }
    }

    private void initNotPawnSquares(Map<Position, Piece> squares, Rank rank, Team team) {
        squares.replace(new Position(File.A, rank), new Rook(team));
        squares.replace(new Position(File.B, rank), new Knight(team));
        squares.replace(new Position(File.C, rank), new Bishop(team));
        squares.replace(new Position(File.D, rank), new Queen(team));
        squares.replace(new Position(File.E, rank), new King(team));
        squares.replace(new Position(File.F, rank), new Bishop(team));
        squares.replace(new Position(File.G, rank), new Knight(team));
        squares.replace(new Position(File.H, rank), new Rook(team));
    }

    public void start() {
        gameState = GameState.PLAYING;
    }

    public boolean isPlaying() {
        return gameState == GameState.PLAYING;
    }

    public Board getBoard() {
        return board;
    }
}
