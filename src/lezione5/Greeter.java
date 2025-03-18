package lezione5;

public class Greeter {
    private String name;

    public Greeter(String nameInput) {
        this.name = nameInput;
    }

    public boolean equals(Greeter anotherGreeter){
        if (this.name.equals(anotherGreeter.name)){}
        return false;
    }

    public static void main(String[] args){
        Greeter a = new Greeter("Opeth");
        Greeter b = new Greeter("Sleepytime Gorilla Museum");
        System.out.println(a.equals(b));
    }
}
