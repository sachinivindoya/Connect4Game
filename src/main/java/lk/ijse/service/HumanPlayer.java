package lk.ijse.service;

public class HumanPlayer extends Player {
    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        if (this.board.isLegalMoves(col)) {

            this.board.updateMove(col, Piece.BLUE);
            this.board.getBoardUI().update(col, true);
            Winner winner = this.board.findWinner();

            if (winner.getWinningPiece() != Piece.EMPTY) {
                this.board.getBoardUI().notifyWinner(winner);
            } else if (!this.board.existLegalMoves()) {
                this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }

    }
}
