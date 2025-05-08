package com.edisa.formacion.mayo2025.API;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class CurrencyConverter {

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Uso: java CurrencyConverter <divisa_origen> <divisa_destino> [importe]");
            return;
        }

        String divisaOrigen = args[0].toUpperCase();
        String divisaDestino = args[1].toUpperCase();
        double importeOrigen = 1.0;

        if (args.length == 3 && !args[2].isEmpty()) {
            try {
                importeOrigen = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Error: El importe debe ser un número válido.");
                return;
            }
        }

        try {
            double resultado = convertirDivisa(divisaOrigen, divisaDestino, importeOrigen);
            BigDecimal importeOriginalFmt = BigDecimal.valueOf(importeOrigen).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal resultadoFmt = BigDecimal.valueOf(resultado).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros();

            System.out.printf("Resultado: %s %s = %s %s%n",
                    importeOriginalFmt.toPlainString(), divisaOrigen,
                    resultadoFmt.toPlainString(), divisaDestino);
        } catch (Exception e) {
            System.err.println("Error al convertir divisa: " + e.getMessage());
        }
    }

    public static double convertirDivisa(String divisaOrigen, String divisaDestino, double importeOrigen) throws Exception {
        String urlStr = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
        String currencyToFind = divisaOrigen.equals("EUR") ? divisaDestino : divisaOrigen;

        // Hacer petición HTTP
        URL url = URI.create(urlStr).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream responseStream = conn.getInputStream();

        // Procesar XML
        Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(responseStream);
        xmlDoc.getDocumentElement().normalize();

        NodeList cubeList = xmlDoc.getElementsByTagName("Cube");
        double conversionRate = 0.0;

        for (int i = 0; i < cubeList.getLength(); i++) {
            Node cube = cubeList.item(i);
            if (cube instanceof Element) {
                Element element = (Element) cube;
                if (element.hasAttribute("currency") && element.getAttribute("currency").equals(currencyToFind)) {
                    conversionRate = Double.parseDouble(element.getAttribute("rate"));
                    break;
                }
            }
        }

        if (conversionRate == 0.0) {
            throw new RuntimeException("No se encontró la tasa de conversión para la divisa: " + currencyToFind);
        }

        // Calcular importe convertido
        double resultado = divisaOrigen.equals("EUR")
                ? importeOrigen * conversionRate
                : importeOrigen / conversionRate;

        return BigDecimal.valueOf(resultado).setScale(6, RoundingMode.HALF_UP).doubleValue();
    }
}
