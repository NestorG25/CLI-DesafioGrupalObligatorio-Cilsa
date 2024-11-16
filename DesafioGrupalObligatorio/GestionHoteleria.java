import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clase Derivada - Subclase para empleados de hotel
abstract class Empleado {
    protected int id;
    protected String nombre;
    protected String apellido;
    protected double salarioBase;

    public Empleado(int id, String nombre, String apellido, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();

    @Override
    public String toString() {
        return "ID: " + id + ", Empleado: " + nombre + " " + apellido + ", Salario Base: " + salarioBase;
    }
}

// Clase Derivada - Subclase para el personal hotelero
class PersonalHotelero extends Empleado {
    private String puesto;

    public PersonalHotelero(int id, String nombre, String apellido, double salarioBase, String puesto) {
        super(id, nombre, apellido, salarioBase);
        this.puesto = puesto;
    }

    @Override
    public double calcularSalario() {
        return salarioBase;
    }

    @Override
    public String toString() {
        return super.toString() + ", Puesto: " + puesto;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }
}

// Clase Derivada - Subclase para gestionar empleados del hotel
class GestorEmpleados {
    private List<Empleado> empleados = new ArrayList<>();

    public boolean agregarEmpleado(Empleado empleado) {
        for (Empleado e : empleados) {
            if (e.id == empleado.id) {
                System.out.println("ID ya existe. Por favor, use un ID diferente.");
                return false;
            }
        }
        empleados.add(empleado);
        return true;
    }

    public void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    public Empleado buscarEmpleadoPorId(int id) {
        for (Empleado e : empleados) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public double calcularTotalSalarios() {
        double total = 0;
        for (Empleado e : empleados) {
            total += e.calcularSalario();
        }
        return total;
    }

    public boolean renunciarEmpleado(int id) {
        Empleado empleado = buscarEmpleadoPorId(id);
        if (empleado != null) {
            empleados.remove(empleado);
            System.out.println("Empleado con ID " + id + " ha renunciado.");
            return true;
        } else {
            System.out.println("Empleado no encontrado.");
            return false;
        }
    }

    public void listarPersonalPorCategoria() {
        System.out.println("\n--- Personal por Categoría de Salario ---");
        int contadorSalariosAltos = 0;
        int contadorSalariosBajos = 0;

        System.out.println("Salarios Altos (>= 1,000,000):");
        for (Empleado e : empleados) {
            if (e.salarioBase >= 1000000) {
                System.out.println(e);
                contadorSalariosAltos++;
            }
        }
        if (contadorSalariosAltos == 0) {
            System.out.println("No hay empleados con salario alto.");
        }

        System.out.println("\nSalarios Bajos (< 1,000,000):");
        for (Empleado e : empleados) {
            if (e.salarioBase < 1000000) {
                System.out.println(e);
                contadorSalariosBajos++;
            }
        }
        if (contadorSalariosBajos == 0) {
            System.out.println("No hay empleados con salario bajo.");
        }

        // Mostrar los contadores de empleados
        System.out.println("\nTotal de empleados con salarios altos: " + contadorSalariosAltos);
        System.out.println("Total de empleados con salarios bajos: " + contadorSalariosBajos);
    }

    public int contarEmpleadosClaseBaja() {
        int contador = 0;
        for (Empleado e : empleados) {
            if (e.salarioBase < 1000000) {
                contador++;
            }
        }
        return contador;
    }

    public int contarEmpleadosClaseAlta() {
        int contador = 0;
        for (Empleado e : empleados) {
            if (e.salarioBase >= 1000000) {
                contador++;
            }
        }
        return contador;
    }
}

// Clase Base - Superclase para ejecutar el sistema de gestión hotelera
public class GestionHoteleria {
    private static final int AGREGAR_EMPLEADO = 1;
    private static final int LISTAR_EMPLEADOS = 2;
    private static final int BUSCAR_EMPLEADO = 3;
    private static final int TOTAL_SALARIOS = 4;
    private static final int RENUNCIAS = 5;
    private static final int PERSONAL_POR_CATEGORIA = 6;
    private static final int SALIR = 7;

    public static void main(String[] args) {
        GestorEmpleados gestor = new GestorEmpleados();
        gestor.agregarEmpleado(new PersonalHotelero(1, "Marta", "Fernandez", 450000, "Recepcionista"));
        gestor.agregarEmpleado(new PersonalHotelero(2, "Luis", "Rodriguez", 1500000, "Chef Ejecutivo"));
        gestor.agregarEmpleado(new PersonalHotelero(3, "Sara", "Miller", 800000, "Housekeeping - Ama De Llaves"));
        gestor.agregarEmpleado(new PersonalHotelero(4, "Carlos", "Suarez", 2000000, "Gerente"));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Sistema de Gestión de Hoteleria ---");
            System.out.println("\n ------Registro------");
            System.out.println("1. Agregar Personal de Hotel");
            System.out.println("2. Listado de Empleados");
            System.out.println("3. Buscar Empleado por ID");
            System.out.println("4. Presupuesto Total Mensual");
            System.out.println("5. Renuncias");
            System.out.println("6. Personal por Categoría");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case AGREGAR_EMPLEADO:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Salario Base: ");
                    double salarioBase = scanner.nextDouble();
                    scanner.nextLine();

                    if (salarioBase < 0) {
                        System.out.println("El salario base debe ser un número positivo.");
                        continue;
                    }

                    System.out.print("Puesto: ");
                    String puesto = scanner.nextLine();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    while (!gestor.agregarEmpleado(new PersonalHotelero(id, nombre, apellido, salarioBase, puesto))) {
                        System.out.print("ID ya existe. Ingrese un nuevo ID: ");
                        id = scanner.nextInt();
                    }
                    break;

                case LISTAR_EMPLEADOS:
                    gestor.listarEmpleados();
                    break;

                case BUSCAR_EMPLEADO:
                    System.out.print("Ingrese el ID del empleado: ");
                    int idBusqueda = scanner.nextInt();
                    Empleado empleadoEncontrado = gestor.buscarEmpleadoPorId(idBusqueda);
                    if (empleadoEncontrado != null) {
                        System.out.println("\n" + empleadoEncontrado);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;

                case TOTAL_SALARIOS:
                    System.out.println("\nTotal de salarios: " + gestor.calcularTotalSalarios());
                    break;

                case RENUNCIAS:
                    System.out.print("Ingrese ID del empleado que desea que renuncie: ");
                    int idRenunciar = scanner.nextInt();
                    gestor.renunciarEmpleado(idRenunciar);
                    break;

                case PERSONAL_POR_CATEGORIA:
                    gestor.listarPersonalPorCategoria(); // Mostrar personal por categoría
                    break;

                case SALIR:
                    System.out.println("Gracias por usar el --- Sistema de Gestión de Hoteleria ---.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
}
