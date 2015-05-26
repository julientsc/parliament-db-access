package ch.eiafr.tsc.parl.rest;

import ch.eiafr.tsc.parl.StoreRDF;
import ch.eiafr.tsc.parl.sparql.Parl;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Julien on 26.05.15.
 */
@Path("/parl/")
public class WSParl {

    StoreRDF store = null;

    public WSParl() {
        try {
            store = StoreRDF.getInstance("prod-parliament");
        } catch (RepositoryException e) {
            System.err.println("Cannot start WSParl");
        }
    }

    @GET
    @Path("/canton")
    @Produces("application/json")
    public Response getCantonFilter() throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Parl.getCantonFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/canton/{id}")
    @Produces("application/json")
    public Response getCanton(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparqlAllFields(Parl.getCanton(id));
        return Response.status(200).entity(output).build();
    }

    //

    @GET
    @Path("/council")
    @Produces("application/json")
    public Response getCouncilFilter(@PathParam("param") String msg) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Parl.getCouncilFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/council/{id}")
    @Produces("application/json")
    public Response getCouncil(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparqlAllFields(Parl.getCouncil(id));
        return Response.status(200).entity(output).build();
    }

    //

    @GET
    @Path("/faction")
    @Produces("application/json")
    public Response getFactionFilter(@PathParam("param") String msg) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Parl.getFactionFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/faction/{id}")
    @Produces("application/json")
    public Response getFaction(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparqlAllFields(Parl.getFaction(id));
        return Response.status(200).entity(output).build();
    }

    //

    @GET
    @Path("/language")
    @Produces("application/json")
    public Response getLangFilter(@PathParam("param") String msg) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Parl.getLangFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/language/{id}")
    @Produces("application/json")
    public Response getLang(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparqlAllFields(Parl.getLang(id));
        return Response.status(200).entity(output).build();
    }

    //

    @GET
    @Path("/party")
    @Produces("application/json")
    public Response getPartyFilter(@PathParam("param") String msg) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Parl.getPartyFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/party/{id}")
    @Produces("application/json")
    public Response getParty(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparqlAllFields(Parl.getParty(id));
        return Response.status(200).entity(output).build();
    }



}