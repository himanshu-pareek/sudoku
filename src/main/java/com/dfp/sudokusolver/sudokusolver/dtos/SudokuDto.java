package com.dfp.sudokusolver.sudokusolver.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"row", "col", "block"})
public class SudokuDto {

    private static final int NUM_ROWS = 9;
    private static final int NUM_COLS = 9;
    
    private int[][] sudoku;

    private boolean[][] row;
    private boolean[][] col;
    private boolean[][] block;

    private boolean solved;

    public void setSudoku(int[][] sudoku) throws Exception {
        if (sudoku == null) {
            throw new Exception("Sudoku can not be empty.");
        }
        if (sudoku.length != NUM_ROWS) {
            throw new Exception("Sudoku must have " + NUM_ROWS + " rows.");
        }
        if (sudoku[0].length != NUM_COLS) {
            throw new Exception("Sudoku must have " + NUM_COLS + " columns.");
        }
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][j] < 0 || sudoku[i][j] > 9) {
                    throw new Exception("Each number must be between 0 and 9.");
                }
            }
        }
        this.sudoku = sudoku;
    }
    
    public int[][] getSudoku() {
        return this.sudoku;
    }

    public boolean[][] getRow() {
        return row;
    }

    public void setRow(boolean[][] row) {
        this.row = row;
    }

    public boolean[][] getCol() {
        return col;
    }

    public void setCol(boolean[][] col) {
        this.col = col;
    }

    public boolean[][] getBlock() {
        return block;
    }

    public void setBlock(boolean[][] block) {
        this.block = block;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
