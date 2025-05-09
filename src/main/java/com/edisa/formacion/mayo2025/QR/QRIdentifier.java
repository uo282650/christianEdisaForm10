package com.edisa.formacion.mayo2025.QR;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class QRIdentifier {

    public static Response getQRsJson(File img) {
        try {
            BufferedImage bufferedImage = ImageIO.read(img);
            if (bufferedImage == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("No se pudo leer la imagen")
                        .build();
            }

            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Reader reader = new MultiFormatReader();
            GenericMultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(reader);

            Result[] results = multiReader.decodeMultiple(bitmap);

            JSONArray jsonArray = new JSONArray();
            for (Result result : results) {
                JSONObject json = new JSONObject();
                json.put("texto", result.getText());
                json.put("formato", result.getBarcodeFormat().toString());
                jsonArray.put(json);
            }

            return Response.ok(jsonArray.toString()).build();

        } catch (NotFoundException e) {
            return Response.ok("[]").build(); // No se encontraron códigos, pero no es un error
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar la imagen: " + e.getMessage())
                    .build();
        }
    }
}

