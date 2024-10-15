package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartowal!");
        run(args);
        System.out.println("System zakonczyl dzialanie!");
    }

    public static void run(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "f" -> System.out.println("Zwierzak idzie do przodu!");
                case "b" -> System.out.println("Zwierzak idzie do tylu!");
                case "l" -> System.out.println("Zwierzak skreca w lewo!");
                case "r" -> System.out.println("Zwierzak skreca w prawo!");
            }
        }
    }
}
