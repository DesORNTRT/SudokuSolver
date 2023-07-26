public class SudokuSolver {

    static int N = 9;

    //überprüft, ob an einer stelle field[x][y] eine nummer eingesetzt werden kann
    public static boolean isValid(int[][] field, int x, int y, int num){

        //horizontal checken
        for(int i =0;i< field.length;i++){
            if(field[x][i]==num){
                //System.out.println("F1");
                return false;
            }
        }

        //vertikal checken
        for(int i =0;i< field.length;i++){
            if(field[i][y]==num){
                //System.out.println("F2");
                return false;
            }
        }
        //block checken
        int startx = x - x % 3;
        int starty = y -y % 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(field[i+startx][j+starty]==num){
                    //System.out.println("F3");
                    return false;
                }
            }
        }
        return true;
    }


    //printet das field in der konsole
    public static void printField(int[][] field){
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field.length; j++){
                if(field[i][j]==0){
                    System.out.print(" - ");
                }
                else{System.out.print(" "+field[i][j]+" ");}
                }
            System.out.println("");
            }
        }


    static boolean solve(int[][] field, int x, int y)
    {
        //max index x
        if (x == N - 1 && y == N)
            return true;
        //max index y
        if (y == N) {
            x++;
            y = 0;
        }

        //zelle überspringen falls bereits belegt
        if (field[x][y] != 0)
            return solve(field, x, y + 1);

        for (int num = 1; num < 10; num++) {
            if (isValid(field, x, y, num)) {
                field[x][y] = num;
                if (solve(field, x, y + 1))
                    return true;
            }
            field[x][y] = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] field = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };


        printField(field);
        System.out.println("");
        if(solve(field,0,0))
            printField(field);
        else
            System.out.println("Es gibt keine Lösung für dieses Sudoku");

    }
}