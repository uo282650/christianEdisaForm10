package com.edisa.formacion.mayo2025.DropWizard;

import com.edisa.formacion.mayo2025.QR.QRGenerator;
import com.edisa.formacion.mayo2025.QR.QRIdentifier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

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
    @Path("/codabars/identificar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response identificarCodigosBarras(@FormParam("imagen") InputStream img) {
        return QRIdentifier.getQRsJson(img); //TODO
    }
}
