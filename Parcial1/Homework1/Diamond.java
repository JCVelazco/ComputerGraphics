//Juan Carlos Velazco Rossell A01326707
//Carlos Parrodi Martinez A01421454
//Diamond
import java.lang.Math;
public class Diamond{
    public static void main(String[] args){
        int side = Integer.parseInt(args[0]);
        for(int i = 0; i < (side*2)-1; i++){
            validDiamond(i, side); //0 to the max rows that the diamons will have
            System.out.println();
        }
    }
    
    public static void validDiamond(int row, int side){
        if(row == 0 || row == (2*side)-2){ //if it's at top or bot 
        printDiamond(side-1, side, 0);
        
    }else{
        int sideSpaces = Math.abs(side-row-1); //space at the sides
        int spaceBetween = (2*side-1) - (2 + sideSpaces*2);
        printDiamond(sideSpaces, side, spaceBetween);
    }
}
public static void printDiamond(int sideSpaces, int side, int spaceBetween){
    if(spaceBetween == 0){ //if top or bot, print spaces and print only one sign
        while(sideSpaces!= 0){
            System.out.printf(" ");
            sideSpaces--;
        }
        System.out.printf("+");
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