package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MoveFilter {

    public static Move[] getContinousMoves(Direction direction, Move[] possibleMoves) {
        List<Move> foundMoves = Arrays.stream(possibleMoves)
                .filter(m -> m.getMoveType().equals(MoveType.CONTINUOUS))
                .filter(m -> m.getxDir() == direction.getXDir())
                .filter(m -> m.getyDir() == direction.getYDir()).collect(Collectors.toList());

        System.out.println("Found Continuous moves: ");
        foundMoves.forEach(System.out::println);

        return foundMoves.toArray(new Move[0]);
    }

    public static Move[] getContinousCaptures(Direction direction, Move[] possibleMoves) {
        List<Move> foundMoves = Arrays.stream(possibleMoves)
                .filter(m -> m.getMoveType().equals(MoveType.CAPTURE_CONTINUOUS))
                .filter(m -> m.getxDir() == direction.getXDir())
                .filter(m -> m.getyDir() == direction.getYDir()).collect(Collectors.toList());

        System.out.println("Found Continuous captures: ");
        foundMoves.forEach(System.out::println);

        return foundMoves.toArray(new Move[0]);
    }

    public static Move[] getStaticMoves(Location endLocation, Move[] possibleMoves) {
        List<Move> foundMoves = Arrays.stream(possibleMoves)
                .filter(m -> m.getMoveType().equals(MoveType.STATIC))
                .filter(m -> m.getEndLocation().getBoardX() == endLocation.getBoardX())
                .filter(m -> m.getEndLocation().getBoardY() == endLocation.getBoardY()).collect(Collectors.toList());

        System.out.println("Found Static moves: ");
        foundMoves.forEach(System.out::println);

        return foundMoves.toArray(new Move[0]);
    }

    public static Move[] getStaticCaptures(Location endLocation, Move[] possibleMoves) {
        List<Move> foundMoves = Arrays.stream(possibleMoves)
                .filter(m -> m.getMoveType().equals(MoveType.CAPTURE_STATIC))
                .filter(m -> m.getEndLocation().getBoardX() == endLocation.getBoardX())
                .filter(m -> m.getEndLocation().getBoardY() == endLocation.getBoardY()).collect(Collectors.toList());

        System.out.println("Found Static captures: ");
        foundMoves.forEach(System.out::println);

        return foundMoves.toArray(new Move[0]);
    }

    public static Move[] getPawnStarts(Location endLocation, Move[] possibleMoves) {
        List<Move> foundMoves = Arrays.stream(possibleMoves)
                .filter(m -> m.getMoveType().equals(MoveType.PAWN_START))
                .filter(m -> m.getEndLocation().getBoardX() == endLocation.getBoardX())
                .filter(m -> m.getEndLocation().getBoardY() == endLocation.getBoardY()).collect(Collectors.toList());

        System.out.println("Found pawn starts: ");
        foundMoves.forEach(System.out::println);

        return foundMoves.toArray(new Move[0]);
    }
}
