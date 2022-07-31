package chess;

import boardGame.Board;
import boardGame.Piece;

public class ChessPiece extends Piece {

	private Color color;
	private int moveCount;

	public ChessPiece(Board board) {
		super(board);
	}

	public Color getColor() {
		return color;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

}
