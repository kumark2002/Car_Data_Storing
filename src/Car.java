public class Car {
    private String VIN;
    private String name;
    private String DOB;

    public Car(String VIN, String name, String DOB) {
        this.VIN = VIN;
        this.name = name;
        this.DOB = DOB;
    }

    public String getVIN() {
        return VIN;
    }

    public String getName() {
        return name;
    }

    public String getDOB() {
        return DOB;
    }

    @Override
    public String toString() {
        return VIN + " " + name + " " + DOB;
    }
}