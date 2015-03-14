package org.tictactoe.game;

import java.util.Arrays;

import org.tictactoe.game.TicTacToe.Status;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
//import java.io.OutputStream;
import java.io.PrintStream;

public class TicTacToeTest extends TestCase {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	public void cleanUpStreams() {
		PrintStream originalOut = System.out;
		PrintStream originalErr = System.err;
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	public void testTicTacToe() {
		TicTacToe testGame = new TicTacToe();
		char[][] emptyBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		assertTrue("board was not empty", Arrays.deepEquals(emptyBoard, testGame.board));
		assertEquals(-1, testGame.numMoves);
		assertEquals(Status.ONGOING, testGame.status);
	}
	
	public void testGetBoard() {
		TicTacToe testGame = new TicTacToe();
		char[][] emptyBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		assertTrue(Arrays.deepEquals(emptyBoard, testGame.getBoard()));
		char[][] second_win = {{'X',' ',' '},{' ','X',' '},{' ',' ','X'}};
		testGame.board = second_win;
		assertTrue(Arrays.deepEquals(second_win, testGame.getBoard()));
	}
	
	public void testSetBoard() {
		TicTacToe testGame = new TicTacToe();
		char[][] notEmptyBoard = {{'X',' ',' '},{' ','O',' '},{' ',' ','X'}};
		testGame.setBoard(notEmptyBoard);
		assertTrue(Arrays.deepEquals(notEmptyBoard, testGame.getBoard()));
		char[][] second_win = {{'X',' ',' '},{' ','X',' '},{' ',' ','X'}};
		testGame.setBoard(second_win);
		assertTrue(Arrays.deepEquals(second_win, testGame.getBoard()));
	}
	
	public void testCheckForWin() {
		TicTacToe testGame = new TicTacToe();
		char[][] first_win = {{'X',' ',' '},{'X',' ',' '},{'X',' ',' '}};
		testGame.setBoard(first_win);
		assertEquals(true, testGame.checkForWin());
		char[][] second_win = {{'X',' ',' '},{' ','X',' '},{' ',' ','X'}};
		testGame.setBoard(second_win);
		assertEquals(true, testGame.checkForWin());
		char[][] first_not_win = {{'X',' ',' '},{' ','X',' '},{'X',' ',' '}};
		testGame.setBoard(first_not_win);
		assertEquals(false, testGame.checkForWin());
	}
	
	public void testGetPlayer1() {
		TicTacToe testGame = new TicTacToe();
		assertEquals('X', testGame.getPlayer1());
	}
	
	public void testGetPlayer2() {
		TicTacToe testGame = new TicTacToe();
		assertEquals('O', testGame.getPlayer2());
	}
	
	public void testCurrentPlayer() {
		TicTacToe testGame = new TicTacToe();
		testGame.numMoves = 0;
		assertEquals('X', testGame.currentPlayer());
		testGame.numMoves = 1;
		assertEquals('O', testGame.currentPlayer());
	}
	
	public void testToString() {
		TicTacToe testGame = new TicTacToe();
		char[][] first_win = {{'X',' ',' '},{'X',' ',' '},{'X',' ',' '}};
		testGame.setBoard(first_win);
		String firstString = " X |   |   \n-----------\n X |   |   \n-----------\n X |   |   ";
		assertEquals(firstString, testGame.toString());
		
		char[][] second_win = {{'X',' ',' '},{' ','X',' '},{' ',' ','X'}};
		testGame.board = second_win;
		assertTrue(Arrays.deepEquals(second_win, testGame.getBoard()));
		String secondString = " X |   |   \n-----------\n   | X |   \n-----------\n   |   | X ";
		assertEquals(secondString, testGame.toString());
		
		char[][] third_win = {{'X',' ',' '},{' ','X',' '},{'X',' ',' '}};
		testGame.setBoard(third_win);
		String thirdString = " X |   |   \n-----------\n   | X |   \n-----------\n X |   |   ";
		assertEquals(thirdString, testGame.toString());
	}
	
	public void testDisplayInstructions() {
		String compareString = "This is the game of Tic Tac Toe.\nX moves first. Each player chooses their\nmove by selecting the cell they want to place\ntheir mark in. The cells are numbered as follows:\n\n  1  2  3\n  4  5  6\n  7  8  9";
		setUpStreams();
		String seporator = System.getProperty("line.separator");
		TicTacToe testGame = new TicTacToe();
		testGame.displayInstructions();
		assertEquals(compareString + seporator, outContent.toString());
		cleanUpStreams();
	}
	
	public void testIsEmpty() {
		TicTacToe testGame = new TicTacToe();
		char[][] first_win = {{'X',' ',' '},{'X',' ',' '},{'X',' ',' '}};
		testGame.setBoard(first_win);
		assertEquals(false, testGame.isEmpty(1));
		assertEquals(true, testGame.isEmpty(2));
		assertEquals(true, testGame.isEmpty(3));
		assertEquals(false, testGame.isEmpty(4));
		assertEquals(true, testGame.isEmpty(5));
		assertEquals(true, testGame.isEmpty(6));
	}
	public void testSetSquare() {
		TicTacToe testGame = new TicTacToe();
		char currentPlayer = 'X';
		int square = 1;
		testGame.setSquare(square, currentPlayer);
		assertEquals('X', testGame.getBoard()[0][0]);
		int square2 = 9;
		testGame.setSquare(square2, currentPlayer);
		assertEquals('X', testGame.getBoard()[2][2]);
	}
}

