import java.util.Scanner;

public class VistaTerminal {
    private Scanner scanner;
    private TareaController controlador;

    public VistaTerminal() {
        this.scanner = new Scanner(System.in);
        this.controlador = new TareaController(new AlmacenamientoMemoria());
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Lista de tareas ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Completar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Descripción de la tarea: ");
                    String descripcion = scanner.nextLine();

                    System.out.print("Título de la tarea: ");
                    String titulo = scanner.nextLine();

                    controlador.agregarTarea(titulo, descripcion);
                    System.out.println("Tarea agregada.");
                    break;

                case "2":
                    try {
                        System.out.print("ID de la tarea a completar: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        controlador.completarTarea(id);
                        System.out.println("Tarea completada.");
                    } catch (NumberFormatException e) {
                        System.out.println("Ingresa un ID válido.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    try {
                        System.out.print("ID de la tarea a eliminar: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        controlador.eliminarTarea(id);
                        System.out.println("Tarea eliminada.");
                    } catch (NumberFormatException e) {
                        System.out.println("Ingresa un ID válido.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}

