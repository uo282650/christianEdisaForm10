package com.edisa.formacion.mayo2025.DropWizard;

import com.edisa.formacion.mayo2025.QR.QRGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public class Recursos {

    @GET
    @Path("/codabar/generar")
    public Response generarCodigoBarras(@QueryParam("texto") String texto,
                                        @QueryParam("formato_codigo_barras") String formatoCodigoBarras){
        return QRGenerator.getQRImageResponse(texto,formatoCodigoBarras);
    }

}
