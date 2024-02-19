package chess;

import java.util.ArrayList;

public interface Piece {

    boolean checkCheck();

    boolean validMove();

    ReturnPlay move(String file, int rank);

    ReturnPiece findNewSpot(String file, int rank);

    ReturnPlay eatThePiece(ReturnPiece eatThePiece, int rank);
}