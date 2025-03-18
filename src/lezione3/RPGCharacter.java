package lezione3;

public class RPGCharacter {
    private String name;
    private int level;
    private String role;

    public RPGCharacter(String name, int level, String role){
        this.name = name;
        this.level = level;
        this.role = role;

    }
    public String describe(){
        return "The mighty "+role+" known as "+name+" stands at level "+level+".";
    }
}
