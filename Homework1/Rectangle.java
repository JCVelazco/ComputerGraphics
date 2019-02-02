//Juan Carlos Velazco Rossell A01326707
//Carlos Parrodi Martinez A01421454
//row and cols
//rectange
public class Rectangle{
public static void main(String[] args){
    char myCharacter = '+';
    int rows = Integer.parseInt(args[0]);
    int cols = Integer.parseInt(args[1]);

    for (int i = 0 ; i < rows; i++){
        for(int j = 0 ; j < cols ; j++){
            System.out.printf(""+myCharacter);
        }
        System.out.println();
    }

}
}