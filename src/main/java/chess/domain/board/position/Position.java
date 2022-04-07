package chess.domain.board.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Position {
    private static final String NO_POSITION_FORMAT_ERROR_MESSAGE = "위치 값의 길이는 2 여야 합니다";
    private static final int ATTRIBUTE_SIZE = 2;

    private final Column column;
    private final Rank rank;

    public Position(Column column, Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    public static Position from(String position) {
        String[] attribute = position.split("");
        validateSize(attribute);
        return new Position(Column.valueOf(attribute[0].toUpperCase(Locale.ROOT)),
                Rank.numberOf(Integer.parseInt(attribute[1])));
    }

    private static void validateSize(String[] attribute) {
        if (attribute.length != ATTRIBUTE_SIZE) {
            throw new IllegalArgumentException(NO_POSITION_FORMAT_ERROR_MESSAGE);
        }
    }

    public static List<Position> calculateRoute(Position from, Position to) {
        Direction direction = Direction.of(from, to);
        List<Position> positions = new ArrayList<>();
        positions.add(from.advancePosition(direction));

        while (!positions.get(positions.size() - 1).equals(to)) {
            positions.add(positions.get(positions.size() - 1).advancePosition(direction));
        }
        return positions.subList(0, positions.size() - 1);
    }

    private Position advancePosition(Direction direction) {
        return new Position(
                Column.numberOf(column.getNumber() + direction.getX()),
                Rank.numberOf(rank.getNumber() + direction.getY())
        );
    }

    public int getXDistance(Position to) {
        return to.column.getNumber() - this.column.getNumber();
    }

    public int getYDistance(Position to) {
        return to.rank.getNumber() - this.rank.getNumber();
    }

    public boolean isEqualRank(Rank rank) {
        return this.rank == rank;
    }

    public boolean isEqualColumn(Column column) {
        return this.column == column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(column, position.column) && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, rank);
    }

    public Column getColumn() {
        return column;
    }

    public Rank getRank() {
        return rank;
    }
}
