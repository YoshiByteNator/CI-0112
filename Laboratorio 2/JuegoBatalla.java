import java.util.Scanner;


class Robot {
    private String nombre;
    private int puntosVida; 
    private int ataque;     
    private int defensa;    

    
    // Constructor
    public Robot(String nombre, int puntosVida, int ataque, int defensa) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntosVida() {
        return puntosVida;
    }
    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    // Método para atacar a otro robot
    public void atacar(Robot otroRobot) {
        int dano = this.ataque - otroRobot.defensa;
        if (dano > 0) {
            otroRobot.puntosVida -= dano;
            System.out.println(this.nombre + " ataca a " + otroRobot.nombre + " y le hace " + dano + " puntos de daño.");
        } else {
            System.out.println(this.nombre + " ataca a " + otroRobot.nombre + " pero no le hace daño.");
        }
    }
    // Robot sigue vivo?
    public boolean estaVivo() {
        return this.puntosVida > 0;
    }
}

    public class JuegoBatalla {
        public static final int MAX_ROBOTS = 10;
        
        // Array
        private Robot[] robots;
        private int cantidadRobots;
    
        public JuegoBatalla(int cantidad) {
            // Se asegura que la cantidad no sea mayor al máximo permitido
            if (cantidad > MAX_ROBOTS) {
                System.out.println("La cantidad de robots no puede ser mayor a " + MAX_ROBOTS + ".");
                cantidad = MAX_ROBOTS;
            }
            this.robots = new Robot[cantidad];
            this.cantidadRobots = cantidad;
        }
        public void agregarRobot(Robot robot, int indice) {
            if (indice >= 0 && indice < robots.length) {
                robots[indice] = robot;
            }
        }
        public void iniciarBatalla() {
            try (Scanner scanner = new Scanner(System.in)) {
                int ronda = 1;
                while (cantidadDeRobotsVivos() > 1) {
                System.out.println("\n--- Ronda " + ronda + " ---");
                // Cada robot  ataca a un robot aleatorio
                for (int i = 0; i < cantidadRobots; i++) {
                    // continue if bot  deleted/dead
                    if (robots[i] == null || !robots[i].estaVivo()) {
                        continue;
                    }
                    // Se selecciona un objetivo diferente
                    int objetivo = i;
                    while (objetivo == i || robots[objetivo] == null || !robots[objetivo].estaVivo()) {
                        objetivo = (int)(Math.random() * cantidadRobots);
                    }
                    robots[i].atacar(robots[objetivo]);
                    
                    // Por sirobot atacado ya no está vivo
                    if (!robots[objetivo].estaVivo()) {
                        System.out.println(robots[objetivo].getNombre() + " ha sido destruido.");
                    }
                }
                // actualizacion de arreglo
                eliminarRobotsMuertos();
                ronda++;
                
                // Pausa para ver la ronda ( puntos extras)
                System.out.println("¿Desea continuar la batalla o ver el estado actual de los robots?");
                System.out.println("1. Continuar");
                System.out.println("2. Ver estado actual de los robots");
                System.out.println("3. Detener la simulación");
                System.out.print("Ingrese su opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada
    
                if (opcion == 2) {
                    mostrarEstadoActual();
                } else if (opcion == 3) {
                    System.out.println("Simulación detenida.");
                    return;
                }
                }
            }
        }
        
            public void mostrarGanador() {
            for (int i = 0; i < cantidadRobots; i++) {
                if (robots[i] != null && robots[i].estaVivo()) {
                    System.out.println("\nEl ganador es: " + robots[i].getNombre());
                    return;
                }
            }
            System.out.println("\nNo hay ningún ganador.");
        }
    
        
        private int cantidadDeRobotsVivos() {
            int contador = 0;
            for (int i = 0; i < cantidadRobots; i++) {
                if (robots[i] != null && robots[i].estaVivo()) {
                    contador++;
                }
            }
            return contador;
        }
    
        //eliminar bots muertos y ajustar array
        private void eliminarRobotsMuertos() {
            Robot[] nuevosRobots = new Robot[robots.length];
            int indiceNuevo = 0;
            for (int i = 0; i < cantidadRobots; i++) {
                if (robots[i] != null && robots[i].estaVivo()) {
                    nuevosRobots[indiceNuevo] = robots[i];
                    indiceNuevo++;
                }
            }
            robots = nuevosRobots;
            cantidadRobots = indiceNuevo;
        }
        // Mostrar el estado actual de los robots
private void mostrarEstadoActual() {
    System.out.println("\nEstado actual de los robots:");
    for (int i = 0; i < cantidadRobots; i++) {
        if (robots[i] != null) {
            System.out.println("Robot " + (i + 1) + ": " + robots[i].getNombre() +
                " | Vida: " + robots[i].getPuntosVida() +
                " | Estado: " + (robots[i].estaVivo() ? "Vivo" : "Muerto"));
        }
    }
}
        
        public static void main(String[] args) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Ingrese la cantidad de robots (máximo " + MAX_ROBOTS + "): ");
                int numRobots = scanner.nextInt();
                scanner.nextLine(); // limpiar  buffer de entrada
        
                if (numRobots < 2) {
                    System.out.println("Se necesitan al menos 2 robots para iniciar la batalla.");
                    return;
                }
            // Crear el juego con la cantidad de robots ingresados
        JuegoBatalla juego = new JuegoBatalla(numRobots);

        // Atributos de los robots
        for (int i = 0; i < numRobots; i++) {
            System.out.println("\nRobot " + (i + 1));
            System.out.print("Ingrese el nombre del robot: ");
            String nombre = scanner.nextLine();

            int puntosVida;
            do {
                System.out.print("Ingrese los puntos de vida (entre 50 y 100): ");
                puntosVida = scanner.nextInt();
            } while (puntosVida < 50 || puntosVida > 100);

            int ataque;
            do {
                System.out.print("Ingrese la fuerza de ataque (entre 10 y 20): ");
                ataque = scanner.nextInt();
            } while (ataque < 10 || ataque > 20);
            int defensa;
            do {
                System.out.print("Ingrese el valor de defensa (entre 0 y 10): ");
                defensa = scanner.nextInt();
            } while(defensa < 0 || defensa > 10);
            scanner.nextLine(); // // limpiar el buffer de entrada
            // Crear y agregar robot
            Robot robot = new Robot(nombre, puntosVida, ataque, defensa);
            juego.agregarRobot(robot, i);
        }

        // Iniciar la batalla y mosrtrar el ganador
        juego.iniciarBatalla();
        juego.mostrarGanador();
    }
    }
}
