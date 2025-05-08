package com.edisa.formacion.mayo2025.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QRGenerator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java -jar qr-generator.jar <texto> <ruta_destino.jpg>");
            return;
        }

        String texto = args[0];
        String ruta = args[1];

        try {
            generarQR(texto, ruta);
            System.out.println("Código QR generado en: " + ruta);
        } catch (Exception e) {
            System.err.println("Error generando el QR: " + e.getMessage());
        }
    }

    public static void generarQR(String texto, String rutaSalida) throws Exception {
        int tamano = 300;

        // Crear un mapa de hints para la codificación
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        // Generar el código QR en una matriz de bits
        BitMatrix matrix = new MultiFormatWriter()
                .encode(texto, BarcodeFormat.QR_CODE, tamano, tamano, hints);

        // Crear una imagen en blanco
        BufferedImage image = new BufferedImage(tamano, tamano, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // Establecer el color de fondo (blanco)
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, tamano, tamano);

        // Establecer el color para los píxeles del código QR (negro)
        g.setColor(Color.BLACK);

        // Dibujar los píxeles del código QR en la imagen
        for (int x = 0; x < tamano; x++) {
            for (int y = 0; y < tamano; y++) {
                if (matrix.get(x, y)) {
                    // Si el valor de la matriz es verdadero (negro), dibujar un rectángulo
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

        g.dispose();

        // Guardar la imagen en la ruta especificada
        File archivoSalida = Paths.get(rutaSalida).toFile();
        ImageIO.write(image, "jpg", archivoSalida);
    }
}
