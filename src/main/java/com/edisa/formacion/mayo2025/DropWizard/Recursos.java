package com.edisa.formacion.mayo2025.DropWizard;

import com.edisa.formacion.mayo2025.QR.QRGenerator;
import com.edisa.formacion.mayo2025.QR.QRIdentifier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/api")
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public class Recursos {

    @GET
    @Path("/codabar/generar")
    public Response generarCodigoBarras(@QueryParam("texto") String texto,
                                        @QueryParam("formato_codigo_barras") String formatoCodigoBarras){
        return QRGenerator.getQRImageResponse(texto,formatoCodigoBarras);
    }

    @POST
    @Path("/codabar/identificar")
    @Consumes({"image/jpg","image/png"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response identificarCodigosBarras(InputStream fileInputStream) {
        try {
            // Aquí recibimos el InputStream con la imagen en binario
            File tempFile = File.createTempFile("uploaded", ".jpg"); // Crea un archivo temporal
            OutputStream os = new FileOutputStream(tempFile);

            // Copia el InputStream al archivo temporal
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();

            return QRIdentifier.getQRsJson(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la imagen").build();
        }
    }
}
