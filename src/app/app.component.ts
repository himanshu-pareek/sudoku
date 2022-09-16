import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(
    private http: HttpClient
  ) {

  }

  title = 'SudokuSolver';
  numRows = 9;
  numCols = 9;
  rows: number[] = [];
  cols: number[] = [];

  sudoku: number[][] = [];

  error = "";
  solvedSudoku: number[][] | null = null;
  solving = false;

  ngOnInit(): void {
    for (let i = 0; i < this.numRows; i++) {
      this.rows.push(i);
    }
    for (let i = 0; i < this.numCols; i++) {
      this.cols.push(i);
    }
    this.sudoku = this.rows.map(
      row => this.cols.map (col => 0)
    );

    console.log(this.sudoku);

  }

  handleInputChange(event: Event, row: number, col: number) {
    console.log(event, row, col);
    // @ts-ignore
    this.sudoku[row][col] = parseInt(event.target.value);
    console.log(this.sudoku);
  }

  clearSudoku() {
    this.sudoku = this.rows.map(
      row => this.cols.map (col => 0)
    );
  }

  valueToDisplay(value: number) {
    return value === 0 ? "" : value;
  }

  solveSudoku() {
    console.log("Send sudoku to server to solve");
    console.log(this.sudoku);
    this.solving = true;
    this.solvedSudoku = null;
    this.error = "";
    try {
      this.http.post<{sudoku: number[][], solved: boolean}>(
        "/api/v1/sudoku/solve", {
        sudoku: this.sudoku
      }).subscribe(response => {
        this.solving = false;
        if (!response.solved) {
          this.error = "Sudoku can not be solved";
        } else {
          this.solvedSudoku = response.sudoku;
        }
        console.log(response);
      });
    } catch (e) {
      // @ts-ignore
      this.error = e.message;
      this.solving = false;
    }
  }

}
