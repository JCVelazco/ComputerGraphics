//Juan Carlos Velazco Rossell A01326707
//Carlos Parrodi Martinez A01421454
//Diamond
import java.lang.Math;
public class Octagon{
    public static void main(String[] args){
        int side = Integer.parseInt(args[0]);
        for(int i = 0; i < (side*2)-1; i++){
            validOctagon(i, side); //0 to the max rows that the diamons will have
            System.out.println();
        }
    }
    
    public static void validOctagon(int row, int side){
        if(row == 0 || row == (2*side)-2){ //if it's at top or bot 
        printOctagon(side-1, side, 0);
        
    }else{
        int sideSpaces = Math.abs(side-row-1); //space at the sides
        int spaceBetween = (2*side + (2*side)) - (sideSpaces*2 + 4);
        //System.out.println(sideSpaces);
        printOctagon(sideSpaces, side, spaceBetween);
    }
}
public static void printOctagon(int sideSpaces, int side, int spaceBetween){
    if(spaceBetween == 0){ //if top or bot, print spaces and print only one sign
        for(int i = 0; i < sideSpaces; i++){//print first spaces
            System.out.printf(" ");
        }
        for(int i = 0; i < side; i++){//print first or last line of signs +
            System.out.printf("+ ");
        }
    }
    else if(sideSpaces == 0){
        for(int i = 0; i < side; i++){//print first spaces
            System.out.printf("+");
            for(int j = 0;  j < spaceBetween ; j++){
                System.out.printf(" ");
            }
            if(i == side-1){
                System.out.printf("+");
            }else{
            System.out.println("+");
        }
        }
    }else{
        for(int i = 0; i < sideSpaces; i++){//print first spaces
            System.out.printf(" ");
        }
        System.out.printf("+");//print sign
        for(int j = 0; j < spaceBetween; j++){//print spaces between
            System.out.printf(" ");
        }
        System.out.printf("+");//print last sign
    }
}

}