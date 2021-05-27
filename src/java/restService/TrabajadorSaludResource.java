/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Alfonso Felix
 */
@Path("trabsalud")
public class TrabajadorSaludResource {

    @Context
    private UriInfo context;
    private final CitasClient citas;
    private final CatalogoExpedientesClient expedientes;
    private final AuthTrabajadorSaludClient auth;

    /**
     * Creates a new instance of TrabajadorSaludResource
     */
    public TrabajadorSaludResource() {
        citas = new CitasClient();
        expedientes = new CatalogoExpedientesClient();
        auth = new AuthTrabajadorSaludClient();
    }

    @POST
    @Path("/solicitarexpediente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postSolicitarExpediente(@HeaderParam("Autorizacion") String token, String json) {
        if (auth.postValidarToken(token).getStatus() == 401) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        return citas.postSolicitudExpediente(json);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postLogin(String json) {
        return auth.postLogin(json);
    }

    @POST
    @Path("/obtenerdatos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postObtenerDatos(@HeaderParam("Autorizacion") String token, String json) {
        return auth.postObtenerDatos(token, json).readEntity(String.class);
    }

    @POST
    @Path("/consultarcitas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postConsultarCitas(@HeaderParam("Autorizacion") String token, String json) {
        return citas.postConsultarCitas(json);
    }

    @POST
    @Path("/consultarexpediente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postConsultarExpediente(@HeaderParam("Autorizacion") String token, String json) {
        auth.postValidarToken(token);
        Gson gson = new GsonBuilder().create();

        String jsonAprobacion = citas.postConsultarAprobacion(json);
        
        int aprobado=gson.fromJson(jsonAprobacion, JsonObject.class).get("estado").getAsInt();
        
        if(aprobado==1){
            return expedientes.postConsultaExpediente(json);
        }
        
        throw new WebApplicationException(Response.Status.UNAUTHORIZED);   
    }
}
