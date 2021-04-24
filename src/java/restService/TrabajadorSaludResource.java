/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restService;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Alfonso Felix
 */
@Path("trabsalud")
public class TrabajadorSaludResource {

    @Context
    private UriInfo context;
    private CitasClient citas;
    private ExpedientesClient expedientes;
    private AuthTrabajadorSaludClient auth;

    /**
     * Creates a new instance of TrabajadorSaludResource
     */
    public TrabajadorSaludResource() {
        citas = new CitasClient();
        expedientes = new ExpedientesClient();
        auth = new AuthTrabajadorSaludClient();
    }

    @GET
    @Path("/auth")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonAuth() {

        return auth.getJson();
    }

    @GET
    @Path("/citas")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonCitas() {

        return citas.getJson();
    }

    @GET
    @Path("/expedientes")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonExpedientes() {

        return expedientes.getJson();
    }

    /**
     * PUT method for updating or creating an instance of
     * TrabajadorSaludResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
