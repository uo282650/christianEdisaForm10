package com.edisa.formacion.mayo2025;

import java.util.Scanner;

public class Circulo implements Figura{
    private double radious;
    @Override
    public void getData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Radious of the circle: ");
        radious = scanner.nextDouble();
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radious,2);
    }
}
