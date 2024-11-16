// Clase Derivada para representar a los empleados del hotel
class PersonalHotel extends GestionHoteleria {
    private int id;
    private String nombre;
    private String rol;
    private double salarioMensual;

    public PersonalHotel(int id, String nombre, String rol, double salarioMensual) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.salarioMensual = salarioMensual;
    }

    public int getId() {
        return id;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double nuevoSalario) {
        this.salarioMensual = nuevoSalario;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Rol: " + rol + ", Salario Mensual: " + salarioMensual;
    }
}

// Subclase para Recepcionista
class Recepcionista extends PersonalHotel {
    public Recepcionista(int id, String nombre, double salarioMensual) {
        super(id, nombre, "Recepcionista", salarioMensual);
    }
}

// Subclase para Chef Ejecutivo
class ChefEjecutivo extends PersonalHotel {
    public ChefEjecutivo(int id, String nombre, double salarioMensual) {
        super(id, nombre, "Chef Ejecutivo", salarioMensual);
    }
}

// Subclase para Ama de Llaves
class AmaDeLlaves extends PersonalHotel {
    public AmaDeLlaves(int id, String nombre, double salarioMensual) {
        super(id, nombre, "Housekepping - Ama de Llaves", salarioMensual);
    }
}

// Subclase para Gerente
class Gerente extends PersonalHotel {
    public Gerente(int id, String nombre, double salarioMensual) {
        super(id, nombre, "Gerente", salarioMensual);
    }
}
