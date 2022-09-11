package com.dfp.sudokusolver.sudokusolver.services;

import org.springframework.stereotype.Service;

import com.dfp.sudokusolver.sudokusolver.dtos.SudokuDto;

@Service
public class SudokuService {

    private int getBlockIndex (int r, int c) {
        return 3 * (r / 3) + (c / 3);
    }

    private boolean solveSudoku(
        SudokuDto sudokuDto,
        int r,
        int c
    ) {
        if (r == 9) {
            return true;
        }
        int nextCol = c + 1;
        int nextRow = r;
        if (nextCol == 9) {
            nextCol = 0;
            nextRow = nextRow + 1;
        }

        int[][] sudoku = sudokuDto.getSudoku();
        boolean[][] row = sudokuDto.getRow();
        boolean[][] col = sudokuDto.getCol();
        boolean[][] block = sudokuDto.getBlock();

        if (sudoku[r][c] == 0) {
            for (int digit = 1; digit <= 9; digit++) {
                if (!(row[r][digit] || col[c][digit] || block[getBlockIndex(r, c)][digit])) {
                    row[r][digit] = true;
                    col[c][digit] = true;
                    block[getBlockIndex(r, c)][digit] = true;
                    sudoku[r][c] = digit;
                    if (solveSudoku(sudokuDto, nextRow, nextCol)) {
                        return true;
                    }
                    sudoku[r][c] = 0;
                    row[r][digit] = false;
                    col[c][digit] = false;
                    block[getBlockIndex(r, c)][digit] = false;
                }
            }
        } else {
            return solveSudoku(sudokuDto, nextRow, nextCol);
        }
        return false;
    }

    public SudokuDto solveSudoku(SudokuDto sudokuDto) {

        sudokuDto.setRow(new boolean[9][10]);
        sudokuDto.setCol(new boolean[9][10]);
        sudokuDto.setBlock(new boolean[9][10]);

        boolean isNotValid = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int digit = sudokuDto.getSudoku()[i][j];
                if(digit != 0) {
                    isNotValid |= sudokuDto.getRow()[i][digit];
                    isNotValid |= sudokuDto.getCol()[j][digit];
                    isNotValid |= sudokuDto.getBlock()[getBlockIndex(i, j)][digit];
                    sudokuDto.getRow()[i][digit] = true;
                    sudokuDto.getCol()[j][digit] = true;
                    sudokuDto.getBlock()[getBlockIndex(i, j)][digit] = true;
                }
            }
        }

        if (!isNotValid) {
            boolean solved = solveSudoku(sudokuDto, 0, 0);
            sudokuDto.setSolved(solved);
        }

        return sudokuDto;
    }
    
}
