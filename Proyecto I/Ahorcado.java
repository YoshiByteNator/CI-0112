import java.util.Scanner;

//Clase ahorcado implementa el juego del ahorcado entre dos jugadores
//Jugador 1 ingresa una palabra secreta y el jugador 2 intenta adivinar letra por letra
public class Ahorcado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean jugarOtraVez;
        do {
            System.out.println("=== Bienvenidos al Juego del Ahorcado ===");
            System.out.print("Jugador 1, ingrese su  palabra secreta: ");
            String palabraSecreta = scanner.nextLine().toLowerCase(); // Convertir a minúscula para evitar problemas

            // Limpiar la pantalla 
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }

            // Inicializar  arreglo de progreso con guiones bajos
            char[] progreso = new char[palabraSecreta.length()];
            for (int i = 0; i < progreso.length; i++) {
                progreso[i] = '_';
            }

            // Mostrar el estado inicial de la palabra a adivinar
            System.out.print("Palabra a adivinar: ");
            for (char letra : progreso) {
                System.out.print(letra + " ");
            }
            System.out.println();

            // Adivinar letras, maximo de 6 intentos
            int intentos = 6;
            boolean palabraAdivinada = false;

            // SE repite mientras queden intentos y  letras por adivinar
            while (intentos > 0 && !palabraAdivinada) {
                System.out.println("Intentos restantes: " + intentos);
                System.out.print("Jugador 2, ingresa una letra: ");
                char letraIngresada = scanner.nextLine().toLowerCase().charAt(0); // Convertir a minúscula e ingresar solo el primer carácter

                boolean acierto = false;
                //Verifica cada posición de la palabra secreta
                for (int i = 0; i < palabraSecreta.length(); i++) {
                    if (palabraSecreta.charAt(i) == letraIngresada && progreso[i] == '_') {
                        // Si la letra está en la palabra secreta y no ha sido adivinada, se actualiza el progreso
                        progreso[i] = letraIngresada;
                        acierto = true;
                    } else if (palabraSecreta.charAt(i) == letraIngresada && progreso[i] != '_') {
                        acierto = true;
                    } // Si la letra ya fue adivinada, no se cuenta como intento         
                }

                if (!acierto)  // Si la letra no está en la palabra secreta, se resta un intento
                {
                    intentos--;
                    System.out.println("Incorrecto! Pierdes un intento.");
                } else {
                    System.out.println("Correcto!");
                }

                // Mostrar progreso tras el intento
                System.out.print("Palabra: ");
                for (char l : progreso) {
                    System.out.print(l + " ");
                }
                System.out.println();

                // Verifica si ya no quedan guiones bajos
                palabraAdivinada = true;
                for (char l : progreso) {
                    if (l == '_') {
                        palabraAdivinada = false;
                        break;
                    }
                }
            }

            // Mensaje de victoria o derrota
            if (palabraAdivinada) {
                System.out.println(" (:  Felicidades! Has adivinado la palabra: " + palabraSecreta);
            } else {
                System.out.println(" ):  Te  quedaste sin intentos! La palabra era: " + palabraSecreta);
            }

            // Preguntar si se desea jugar otra vez
            System.out.print("¿Desean jugar otra vez? (s/n): ");
            char resp = scanner.nextLine().toLowerCase().charAt(0);
            jugarOtraVez = (resp == 's');
        } while (jugarOtraVez);

        System.out.println("Gracias por jugar!");
        scanner.close();
    }
}
// implementar espacios entre rounds
// preguntarle profe si quiere implemetnrar pista
