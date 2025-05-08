package com.edisa.formacion.mayo2025;

public class FiguraFactory {
    public static final String CIRCLE = "Circle";
    public static final String RECTANGLE = "Rectangle";
    public Figura createFigura(String figuraName){
        if(figuraName.equals(CIRCLE)){
            return new Circulo();
        } else if (figuraName.equals(RECTANGLE)) {
            return new Rectangulo();
        }
        return null;
    }
}
