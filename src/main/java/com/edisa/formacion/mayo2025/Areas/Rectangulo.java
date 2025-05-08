package com.edisa.formacion.mayo2025.Areas;

import java.util.Scanner;

public class Rectangulo implements Figura{
    private double width;
    private double height;
    @Override
    public void getData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Width of the rectangle: ");
        width = scanner.nextDouble();
        System.out.println("Height of the rectangle: ");
        height = scanner.nextDouble();
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}
