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
        if (args.length != 3) {
            System.out.println("Uso: java -jar qr-generator.jar <texto> <ruta_destino.jpg> <formato>");
            System.out.println("Formatos soportados: QR, EAN-13");
            return;
        }

        String texto = args[0];
        String ruta = args[1];
        String formato = args[2].toUpperCase();  // Convertimos a mayúsculas para evitar problemas con la entrada.

        try {
            generarCodigoDeBarras(texto, ruta, formato);
            System.out.println("Código de barras generado en: " + ruta);
        } catch (Exception e) {
            System.err.println("Error generando el código de barras: " + e.getMessage());
        }
    }

    public static void generarCodigoDeBarras(String texto, String rutaSalida, String formato) throws Exception {
        int tamano = 300;

        // Selección del formato de código de barras
        BarcodeFormat barcodeFormat;
        switch (formato) {
            case "QR":
                barcodeFormat = BarcodeFormat.QR_CODE;
                break;
            case "EAN-13":
                barcodeFormat = BarcodeFormat.EAN_13;
                // Verificar que el texto tenga 13 dígitos para EAN-13
                if (texto.length() != 13) {
                    throw new IllegalArgumentException("El código EAN-13 debe tener 13 dígitos.");
                }
                break;
            default:
                throw new IllegalArgumentException("Formato no soportado: " + formato);
        }

        // Crear un mapa de hints para la codificación
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        // Generar la matriz de bits del código de barras
        BitMatrix matrix = new MultiFormatWriter()
                .encode(texto, barcodeFormat, tamano, tamano, hints);

        // Crear una imagen en blanco
        BufferedImage image = new BufferedImage(tamano, tamano, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // Establecer el color de fondo (blanco)
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, tamano, tamano);

        // Establecer el color para los píxeles del código de barras (negro)
        g.setColor(Color.BLACK);

        // Dibujar los píxeles del código de barras en la imagen
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
