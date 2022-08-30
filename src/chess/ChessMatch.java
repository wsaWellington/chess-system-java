package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;

	/*
	 * public ChessMatch(int turn, Color currentPlayer, boolean check, boolean
	 * checkMate, ChessPiece enPassantVulnerable, ChessPiece promoted) { super();
	 * board = new Board(8, 8); this.turn = turn; this.currentPlayer =
	 * currentPlayer; this.check = check; this.checkMate = checkMate;
	 * this.enPassantVulnerable = enPassantVulnerable; this.promoted = promoted; }
	 */

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Color currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isCheckMate() {
		return checkMate;
	}

	public void setCheckMate(boolean checkMate) {
		this.checkMate = checkMate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public void setEnPassantVulnerable(ChessPiece enPassantVulnerable) {
		this.enPassantVulnerable = enPassantVulnerable;
	}

	public ChessPiece getPromoted() {
		return promoted;
	}

	public void setPromoted(ChessPiece promoted) {
		this.promoted = promoted;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public ChessPiece[][] getPieces() {

		ChessPiece mat[][] = new ChessPiece[board.getRows()][board.getCollumns()];

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCollumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}

		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		
		return (ChessPiece)capturedPiece;
	}

	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.revomePiece(source);
		Piece capturedPiece = board.revomePiece(target);
		board.placePeace(p, target);		
		
		return capturedPiece;
	}

	private void placeNewPiece(char collumn, int row, ChessPiece piece) {
		board.placePeace(piece, new ChessPosition(collumn, row).toPosition());

	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

}
