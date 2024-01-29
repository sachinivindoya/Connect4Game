package lk.ijse.service;

public class AiPlayer extends Player {
    public AiPlayer(Board board) {
        super(board);
    }

    public void movePiece(int col) {
        col = this.predictColumn();
        this.board.updateMove(col, Piece.GREEN);
        this.board.getBoardUI().update(col, false);
        Winner winner = this.board.findWinner();
        if (winner.getWinningPiece() != Piece.EMPTY) {
            this.board.getBoardUI().notifyWinner(winner);
        } else if (!this.board.existLegalMoves()) {
            this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }

    private int predictColumn() {
        boolean isUserWinning = false;
        int tiedColumn = 0;

        int i;
        for(i = 0; i < 6; ++i) {
            if (this.board.isLegalMoves(i)) {
                int row = this.board.findNextAvailableSpot(i);
                this.board.updateMove(i, Piece.GREEN);
                int heuristicVal = this.minimax(0, false);
                this.board.updateMove(i, row, Piece.EMPTY);
                if (heuristicVal == 1) {
                    return i;
                }

                if (heuristicVal == -1) {
                    isUserWinning = true;
                } else {
                    tiedColumn = i;
                }
            }
        }

        if (isUserWinning && this.board.isLegalMoves(tiedColumn)) {
            return tiedColumn;
        } else {
            boolean var6 = false;

            do {
                i = (int)(Math.random() * 6.0D);
            } while(!this.board.isLegalMoves(i));

            return i;
        }
    }

    private int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = this.board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE) {
            return -1;
        } else if (this.board.existLegalMoves() && depth != 2) {
            int i;
            int row;
            int heuristicVal;
            if (!maximizingPlayer) {
                for(i = 0; i < 6; ++i) {
                    if (this.board.isLegalMoves(i)) {
                        row = this.board.findNextAvailableSpot(i);
                        this.board.updateMove(i, Piece.BLUE);
                        heuristicVal = this.minimax(depth + 1, true);
                        this.board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == -1) {
                            return heuristicVal;
                        }
                    }
                }
            } else {
                for(i = 0; i < 6; ++i) {
                    if (this.board.isLegalMoves(i)) {
                        row = this.board.findNextAvailableSpot(i);
                        this.board.updateMove(i, Piece.GREEN);
                        heuristicVal = this.minimax(depth + 1, false);
                        this.board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == 1) {
                            return heuristicVal;
                        }
                    }
                }
            }

            return 0;
        } else {
            return 0;
        }
    }
}
