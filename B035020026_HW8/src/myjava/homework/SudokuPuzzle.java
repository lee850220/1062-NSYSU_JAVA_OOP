package myjava.homework;

public class SudokuPuzzle {

    private int [][] board = new int[9][9]; // current state (0 = blank)
    private boolean [][] start = new boolean[9][9]; // which square cannot be change
    private final int [][] p1 = {{1,2,3,4,9,7,8,6,5},
                               {4,5,9,0,0,0,0,0,0},
                               {6,7,8,0,0,0,0,0,0},
                               {3,0,0,0,1,0,0,0,0},
                               {2,0,0,0,0,0,0,0,0},
                               {9,0,0,0,0,5,0,0,0},
                               {8,0,0,0,0,0,0,0,0},
                               {7,0,0,0,0,0,0,0,0},
                               {5,0,0,9,0,0,0,0,0}};
    private final int [][] p2 = {{4,2,8,7,1,3,6,9,5},
                               {3,6,9,8,4,5,7,1,2},
                               {5,7,1,2,9,6,4,8,3},
                               {6,8,7,5,2,9,3,4,1},
                               {1,3,2,6,8,4,5,7,9},
                               {9,5,4,3,7,1,2,6,8},
                               {8,4,3,9,6,2,1,5,7},
                               {7,1,5,4,3,8,9,2,6},
                               {2,9,6,1,5,7,0,0,0}};

    SudokuPuzzle() { // Constructor
        reset();
        init();
    }

    public String toString() { // representation of the puzzle that can be printed
        String s = "\n＋===＋===＋===＋===＋===＋===＋===＋===＋===＋\n";
        String num = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (getValueIn(i,j) == 0) num = " ";
                else num = String.valueOf(getValueIn(i,j));

                if (j == 0) s += "∥ " + num + " ｜ ";
                else if (j % 3 == 2) s += num + " ∥ ";
                else s += num + " ｜ ";
            }
            if (i % 3 == 2)
                s += "\n＋===＋===＋===＋===＋===＋===＋===＋===＋===＋\n";
            else
                s += "\n＋— ＋－ ＋－ ＋－ ＋－ ＋－ ＋－ ＋－ ＋－ ＋\n";
        }
        return s;
    }

    public void addInitial(int row, int col, int value) { // sets initial value that cannot be changed
        board[row][col] = value;
        start[row][col] = true;
    }

    public void addGuess(int row, int col, int value) { // sets value which can be changed
        int ori = board[row][col];
        board[row][col] = value;
        if (!checkPuzzle()) { // if is invalid
            System.out.println("You cannot fill this number.");
            board[row][col] = ori;
        }
    }

    public boolean checkPuzzle() { // check the value in the puzzle do not violate the restrictions
        boolean isValid = true;
        for (int i = 0; i < 9; i++) { // lines
            for (int j = 0; j < 9; j++) {
                int chk = board[i][j]; // rows
                if (chk != 0)
                    for (int k = j + 1; k < 9; k++) {
                        if (board[i][k] == chk) {
                            isValid = false;
                            return isValid;
                        }
                    }

                chk = board[j][i]; // cols
                if (chk != 0)
                    for (int k = j + 1; k < 9; k++) {
                        if (board[k][i] == chk) {
                            isValid = false;
                            return isValid;
                        }
                    }

                chk = board[j/3 + i/3*3][j%3 + i%3*3]; // square
                if(chk != 0)
                    for (int k = j + 1; k < 9; k++) {
                        if (board[k/3 + i/3*3][k%3 + i%3*3] == chk) {
                            isValid = false;
                            return isValid;
                        }
                    }
            }
        }
        return isValid;
    }

    public int getValueIn(int row, int col) { // returns the value in the given square
        return board[row][col];
    }

    public boolean[] getAllowedValue(int row, int col) { // returns an 9-bit boolean array of valid value
        boolean [] allowed = {false,false,false,false,false,false,false,false,false};

        if (start[row][col] == true) { // if it cannot be changed
            allowed[board[row][col] - 1] = true;
        } else { // if it can be changed
            int ori = board[row][col];
            for (int i = 0; i < 9; i++) {
                board[row][col] = i + 1;
                if (checkPuzzle()) {
                    allowed[i] = true;
                }
            }
            board[row][col] = ori;
        }
        return allowed;
    }

    public boolean isFull() { // check board is finished
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    public void reset() { // reset the board to 0
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void init() { // create new game
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(p2[i][j] != 0) addInitial(i, j, p2[i][j]);
            }
        }
    }
}
