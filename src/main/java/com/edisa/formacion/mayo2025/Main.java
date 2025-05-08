package com.edisa.formacion.mayo2025;

//import java.util.InputMismatchException;
//import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print(calculateArea());
    }

    public static String calculateArea(){
        Figura figura1 = new FiguraFactory().createFigura(FiguraFactory.CIRCLE);
        Figura figura2 = new FiguraFactory().createFigura(FiguraFactory.RECTANGLE);
        double area1;
        double area2;

        figura1.getData();
        area1 = figura1.calculateArea();
        System.out.println("Area is "+area1);

        figura2.getData();
        area2 = figura2.calculateArea();
        System.out.println("Area is "+area2);
        /*System.out.print("Width: ");
        double width = getDoubleFromConsole(scanner);
        System.out.print("Height: ");
        double height = getDoubleFromConsole(scanner);
        scanner.close();*/

        return "";//"The area is "+width*height;
    }

    /*private static double getDoubleFromConsole(Scanner scanner){
        double res = 0;
        boolean valid = false;
        while (!valid) {
            try {
                res = scanner.nextDouble();

                if (res <= 0) {
                    throw new IllegalArgumentException("The number must be strictly greater than 0.");
                }

                valid = true;
            } catch (InputMismatchException e) {
                System.out.print("The input was not formated as a proper decimal number, like for example '1,1234'. Try again");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
        return res;
    }*/
}