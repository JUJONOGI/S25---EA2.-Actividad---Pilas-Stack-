import java.util.Scanner;

// Pila basica
class MyStack<T> {
    private Node<T> top;

    // Clase interna para manejar los nodos
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    // Insertar elemento (push)
    public void push(T data) {
        Node<T> node = new Node<>(data);
        node.next = top;
        top = node;
    }

    // Eliminar y retornar el elemento superior (pop)
    public T pop() {
        if (isEmpty()) return null;
        T data = top.data;
        top = top.next;
        return data;
    }

    // Ver el elemento superior sin eliminarlo
    public T peek() {
        if (isEmpty()) return null;
        return top.data;
    }

    // Verificar si la pila está vacía
    public boolean isEmpty() {
        return top == null;
    }
}

// Clase principal con la lógica del editor de texto
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyStack<String> undoStack = new MyStack<>();
        MyStack<String> redoStack = new MyStack<>();

        int opcion;
        do {
            System.out.println("\n--- Simulador de Editor de Texto ---");
            System.out.println("1. Escribir texto");
            System.out.println("2. Deshacer (Undo)");
            System.out.println("3. Rehacer (Redo)");
            System.out.println("4. Mostrar texto actual (peek)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Escriba una línea de texto: ");
                    String linea = scanner.nextLine();
                    undoStack.push(linea);
                    redoStack = new MyStack<>(); // se borra la pila de rehacer al escribir
                    System.out.println("Texto agregado.");
                    break;
                case 2:
                    if (!undoStack.isEmpty()) {
                        String textoDeshecho = undoStack.pop();
                        redoStack.push(textoDeshecho);
                        System.out.println("Se deshizo: \"" + textoDeshecho + "\"");
                    } else {
                        System.out.println("No hay texto para deshacer.");
                    }
                    break;
                case 3:
                    if (!redoStack.isEmpty()) {
                        String textoRehecho = redoStack.pop();
                        undoStack.push(textoRehecho);
                        System.out.println("Se rehizo: \"" + textoRehecho + "\"");
                    } else {
                        System.out.println("No hay acciones para rehacer.");
                    }
                    break;
                case 4:
                    String textoActual = undoStack.peek();
                    if (textoActual != null) {
                        System.out.println("Texto actual (última línea): \"" + textoActual + "\"");
                    } else {
                        System.out.println("No hay texto aún.");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del editor...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
