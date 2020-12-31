package model.boardgame;

import model.exceptions.BoardException;

public class Board {
  private int rows;
	private int columns;
	private Piece[][] pieces; // matriz de peças declarada no tabuleiro 
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {//protegendo o código. Se a condição for atendida, retorna a msg abaixo
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // tabuleiro instaciado no construtor
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {//se passado uma posição inexistente, retorna a msg abaixo
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) { //se já existe uma peça nessa posição, retornar a msg abaixo
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; // atribuindo a posição dada a peça informada "piece"
		piece.position = position; //falando que essa essa peça informada não está mais na posição nula
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {
    return row >= 0 && row < rows && column >= 0 && column < columns;
    /*é mais fácil testar por aqui a linha precisa ser mair ou igual a 0; não pode ser maior que o 
    tabuleiro; mesma coisa a coluba*/
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn()); //reaproveitando o método de cima
	}
	
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) { //
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;//se for diferente de nulo, retorna
	}
}
