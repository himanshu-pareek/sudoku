package com.dfp.sudokusolver.sudokusolver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dfp.sudokusolver.sudokusolver.dtos.SudokuDto;
import com.dfp.sudokusolver.sudokusolver.services.SudokuService;

@RestController
@RequestMapping("/api/v1/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    @PostMapping("/solve")
    public SudokuDto solve(
        @RequestBody SudokuDto sudokuDto
    ) {
        return sudokuService.solveSudoku(sudokuDto);
    }

}
