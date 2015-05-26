package ch.eiafr.tsc.parl.rest;

import ch.eiafr.tsc.parl.StoreRDF;
import ch.eiafr.tsc.parl.sparql.Facebook;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by Julien on 26.05.15.
 */
@Path("/facebook/")
public class WSFacebook {

    StoreRDF prodParl = null;

    public WSFacebook() {
        try {
            prodParl = StoreRDF.getInstance("prod-facebook");
        } catch (RepositoryException e) {
            System.err.println("Cannot start WSFacebook");
        }
    }

    @GET
    @Path("/account/{accountId}")
    @Produces("application/json;charset=utf-8")
    public Response getAccount(@PathParam("accountId") long accountId) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Facebook.getAccount(accountId));
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/parl")
    @Produces("application/json;charset=utf-8")
    public Response getParlFacebookAccountList() throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Facebook.getParlFacebookAccountList());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/status/{statusId}")
    @Produces("application/json;charset=utf-8")
    public Response getStatus(@PathParam("statusId") long statusId) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Facebook.getStatus(statusId));
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/status/{accountId}/{startDate}/{stopDate}")
    @Produces("application/json;charset=utf-8")
    public Response getStatusListFromAccount(@PathParam("accountId") long accountId, @PathParam("startDate") long startDate, @PathParam("stopDate") long stopDate) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Facebook.getStatusListFromAccount(accountId, startDate, stopDate));
        return Response.status(200).entity(output).build();
    }

}