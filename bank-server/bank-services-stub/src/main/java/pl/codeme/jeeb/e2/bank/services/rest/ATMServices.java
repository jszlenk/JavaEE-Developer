package pl.codeme.jeeb.e2.bank.services.rest;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/atm")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ValidateOnExecution
public interface ATMServices {

    @GET
    @Path("/authCN/{cn}")
    public Response authCardNumber(
        @PathParam("cn") 
        @Pattern(regexp="[0-9]+", message="Numer karty powinien składać się z cyfr")
        String cn
    );

    @GET
    @Path("/authPin/{pin}")
    public Response authPin(@PathParam("pin") String pin);

    @GET
    @Path("/cardinfo")
    @Produces(MediaType.APPLICATION_XML)
    public Response getCardInfo();

    @POST
    @Path("/withdraw")
    public Response withdraw(@Valid @FormParam("amount") Integer amount);

}
