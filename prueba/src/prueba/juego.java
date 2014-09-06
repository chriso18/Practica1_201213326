package prueba;

import java.util.Random;
import java.util.Scanner;

public class juego {
	private Usuario usuario;
    private Computadora computadora;
    private int usuarioPuntos;
    private int computadoraPuntos;
    private int cantidadDeJuegos;
    private int turno; 
 
	private enum Move {
    HANDMAID, HANDMAID1, PRIEST, PRIEST1, GUARD, GUARD1, GUARD2, GUARD3, GUARD4, BARON, BARON1, PRINCE, PRINCE1, KING, COUNTLESS, PRINCESS;
	 /**
     * Compara esta jugada con otra jugada para determinar empate, victoria o 
     * derrota.
     * 
     * @param otherMove
     *            jugada a comparar con
     * @return 1 si esta jugada le gana a la otra, -1 si esta jugada pierde frente a 
     *         la otra, 0 si las jugadas empatan
     */
    public int compareMoves(Move otherMove) {
        // Empate
        if (this == otherMove)
            return 0;
 
        switch (this) {
        case HANDMAID:
            return (otherMove == PRIEST ? 1 : -1);
        case PRIEST:
            return (otherMove == GUARD ? 1 : -1);
        case GUARD:
            return (otherMove == BARON ? 1 : -1);
        }
 
        // Nunca debería llegar hasta aquí
        return 0; }}

	private class Usuario {
		 private Scanner inputScanner;
		 public Usuario() {
	            inputScanner = new Scanner(System.in);
	            }
		 public Move getMove() {
	       
	            // Obtener la entrada del usuario
	            String usuarioInput = inputScanner.nextLine();
	            usuarioInput = usuarioInput.toUpperCase();
	            char firstLetter = usuarioInput.charAt(0);
	            if (firstLetter == 'H' || firstLetter == 'P' || firstLetter == 'G'|| firstLetter == 'B'|| firstLetter == 'R'
	            		|| firstLetter == 'K'|| firstLetter == 'C'|| firstLetter == 'A') {
	                // Usuario ha ingresado un dato válido
	                switch (firstLetter) {
	                case 'H':
	                    return Move.HANDMAID;
	                case 'P':
	                    return Move.PRIEST;
	                case 'G':
	                    return Move.GUARD;
	                case 'B':
	                    return Move.BARON;
	                case 'R':
	                    return Move.PRINCE;
	                case 'K':
	                    return Move.KING;
	                case 'C':
	                    return Move.COUNTLESS;
	                case 'A':
	                    return Move.PRINCESS;
	                } }
	            // Usuario no ha ingresado un dato válido. Solicitar nuevamente.
	            return getMove();
	        }	 
         public boolean playAgain() {
	            System.out.print("Quieres jugar de nuevo? ");
	            String usuarioInput = inputScanner.nextLine();
	            usuarioInput = usuarioInput.toUpperCase();
	            return usuarioInput.charAt(0) == 'S';  }}

	private class Computadora {
		   public Move getMove() {
	            Move[] moves = Move.values();
	            Random random = new Random();
	            int index = random.nextInt(moves.length);
	            return moves[index];}}
	       
	public juego() {
	        usuario = new Usuario();
	        computadora = new Computadora();
	        usuarioPuntos = 0;
	        computadoraPuntos = 0;
	        cantidadDeJuegos = 0;
	}
		 
	public void startGame() {
        System.out.println("Love letter!");
        System.out.println("seleccione su carta");
        
     char vector[]=new char [16]; 
   	 int i=0, j;   	 
   	 vector[i]=(char)(Math.random()*16); 
   	 vector[0]='G';
     vector[1]='G';
   	 vector[2]='G';
   	 vector[3]='G';
   	 vector[4]='G';
   	 vector[5]='H';
   	 vector[6]='H';
   	 vector[7]='B';
   	 vector[8]='B';
   	 vector[9]='R';
   	 vector[10]='R';
   	 vector[11]='C';
   	 vector[12]='C';
   	 vector[13]='A';
   	 vector[14]='A';
	 vector[15]='K';
   	 for(i=1;i<4;i++) { 
   	 vector[i]=(char)(Math.random()*16); 
   	 for(j=0;j<i;j++) { 
   	 if(vector[i]==vector[j]) { 
   	 i--; } } } 
   	 for(i=0;i<4;i++){ 
   	 System.out.print(" "+vector[i]);} 
    
        // Obtener jugadas
        Move usuarioMove = usuario.getMove();
        Move computadoraMove = computadora.getMove();
        System.out.println("\nhas jugado " + usuarioMove + ".");
        System.out.println("Computadora jugo " + computadoraMove + ".\n");
 
        // Comparar jugadas y determinar ganador
        int compareMoves = usuarioMove.compareMoves(computadoraMove);
        switch (compareMoves) {
        case 0: // Empate
            System.out.println("Empate!");
            break;
        case 1: // Gana Usuario
            System.out.println(usuarioMove + " le gana a " + computadoraMove + ". Ganaste!");
            usuarioPuntos++;
            break;
        case -1: // Gana Computadora
            System.out.println(computadoraMove + " le gana a " + usuarioMove + ". Perdiste.");
            computadoraPuntos++;
            break; }
        cantidadDeJuegos++;       
        
        // Preguntar al usuario si jugar nuevamente
        if (usuario.playAgain()) {
            System.out.println();
            startGame();
        } else {
            printGameStats();
        }
    }
 
    /**
     * Imprime las estadísticas del juego. Calcula un empate, como ½ victoria en
     * el porcentaje de victorias.
     */
    private void printGameStats() {
        int wins = usuarioPuntos;
        int losses = computadoraPuntos;
        int ties = cantidadDeJuegos - usuarioPuntos - computadoraPuntos;
        double percentageWon = (wins + ((double) ties) / 2) / cantidadDeJuegos;
 
        // Línea
        System.out.print("+");
        printDashes(68);
        System.out.println("+");
 
        // Imprimir títulos
        System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
                           "VICTORIAS", "DERROTAS", "EMPATES", "JUEGOS", "PORCENTAJE DE V.");
 
        // Línea
        System.out.print("|");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(16);
        System.out.print("+");
        printDashes(18);
        System.out.println("|");
 
        // Imprime valores
        System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
                wins, losses, ties, cantidadDeJuegos, percentageWon * 100);
 
        // Línea
        System.out.print("+");
        printDashes(68);
        System.out.println("+");
    }
 
    private void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-"); } }
  
	public static void main(String[] args) {	 
		
