package lk.ijse.service;

public interface Board {
    int NUM_OF_ROWS = 5;
    int NUM_OF_COLS = 6;

    BoardUI getBoardUI();
    int findNextAvailableSpot (int col);
    boolean isLegalMoves (int col);
    boolean existLegalMoves ();
    void updateMove (int col,Piece move);
    Winner findWinner();

    void updateMove(int i, int row, Piece empty);
}
