package lk.ijse.service;

public class BoardImpl implements Board{
    private Piece pieces[][] = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
        this.boardUI = boardUI;
    }


    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < NUM_OF_ROWS; ++i) {
            if (this.pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean isLegalMoves(int col) {
        if (findNextAvailableSpot(col) == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean existLegalMoves() {
        for (int col = 0; col < NUM_OF_COLS; col++) {
            if (isLegalMoves(col)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        this.pieces[col][this.findNextAvailableSpot(col)] = move;

    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        this.pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        int rowCount;

        for (int col = 0; col < NUM_OF_COLS; col++) {
            int rowForLoop = findNextAvailableSpot(col) == -1 ? NUM_OF_ROWS : findNextAvailableSpot(col);

            rowCount = 0;


            for (int row = 1; row < rowForLoop; row++) {


                if (pieces[col][row] .equals(pieces[col][row-1])) {
                    rowCount++;

                    if (rowCount == 3) {

                        return new Winner(pieces[col][row], col, row-3, col, row );
                    }
                } else {
                    rowCount = 0;
                }
            }

        }
//        int colCount;

        for(int row = 0; row <NUM_OF_ROWS; ++row) {
            int count = 1;

            for(int col = 1; col <NUM_OF_COLS; ++col) {
                if (pieces[col][row] == pieces[col-1][row] ) {
                    count++;
                    if (count == 4) {
                        return new Winner(pieces[col][row], col - 3, row, col, row);
                    }
                } else {
                    count = 1;

                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}
