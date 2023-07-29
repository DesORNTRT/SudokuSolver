import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {

    static int N = 9;

    //überprüft, ob an einer stelle field[x][y] eine nummer eingesetzt werden kann
    public static boolean isValid(int[][] field, int x, int y, int num){

        //horizontal checken
        for(int i =0;i< field.length;i++){
            if(field[x][i]==num){
                return false;
            }
        }

        //vertikal checken
        for(int i =0;i< field.length;i++){
            if(field[i][y]==num){
                return false;
            }
        }
        //block checken
        int startx = x - x % 3;
        int starty = y -y % 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(field[i+startx][j+starty]==num){
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

    public static boolean inputIsValid(int[][] field){
        List<Integer> list=new ArrayList<>();
        //horizontal
        for(int i=0;i<field.length;i++){
            for(int j =0;j< field.length;j++){
                if(field[i][j]!=0) {
                    if (list.contains(field[i][j])) {
                        return false;
                    }
                }
                list.add(field[i][j]);
            }
            list.clear();
        }
        //vertikal
        for(int i=0;i< field.length;i++){
            for(int j =0;j<field.length;j++){
                if(field[j][i]!=0) {
                    if (list.contains(field[j][i])) {
                        return false;
                    }
                    list.add(field[j][i]);
                }
            }
            list.clear();
        }

        //block
        for(int i=0;i< field.length-3;i+=3){
            for(int j=0;j< field.length-3;j+=3){
                for(int k=0;k<3;k++){
                    for(int l=0;l<3;l++){
                        if(field[i+k][j+l]!=0){
                            if(list.contains(field[i+k][j+l])){
                                return false;
                            }
                            list.add(field[i][j]);
                        }
                    }
                }
                list.clear();
            }
        }

        return true;
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
        if(inputIsValid(field)) {
            if (solve(field, 0, 0))
                printField(field);
        }
        else System.out.println("Es gibt keine Lösung für dieses Sudoku");


        }
}
