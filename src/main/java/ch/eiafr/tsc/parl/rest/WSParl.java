package ch.eiafr.tsc.parl.rest;

import ch.eiafr.tsc.parl.StoreRDF;
import ch.eiafr.tsc.parl.sparql.Parl;
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
@Path("/parl/")
public class WSParl {

    StoreRDF prodParl = null;

    public WSParl() {
        try {
            prodParl = StoreRDF.getInstance("prod-parliament");
        } catch (RepositoryException e) {
            System.err.println("Cannot start WSParl");
        }
    }

    @GET
    @Path("/canton")
    @Produces("application/json;charset=utf-8")
    public Response getCantonFilter() throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Parl.getCantonFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/canton/{id}")
    @Produces("application/json;charset=utf-8")
    public Response getCanton(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparqlAllFields(Parl.getCanton(id));
        return Response.status(200).entity(output).build();
    }

    //

    @GET
    @Path("/council")
    @Produces("application/json;charset=utf-8")
    public Response getCouncilFilter(@PathParam("param") String msg) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Parl.getCouncilFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/council/{id}")
    @Produces("application/json;charset=utf-8")
    public Response getCouncil(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparqlAllFields(Parl.getCouncil(id));
        return Response.status(200).entity(output).build();
    }

    //

    @GET
    @Path("/faction")
    @Produces("application/json;charset=utf-8")
    public Response getFactionFilter(@PathParam("param") String msg) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Parl.getFactionFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/faction/{id}")
    @Produces("application/json;charset=utf-8")
    public Response getFaction(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparqlAllFields(Parl.getFaction(id));
        return Response.status(200).entity(output).build();
    }

    //

    @GET
    @Path("/language")
    @Produces("application/json;charset=utf-8")
    public Response getLangFilter(@PathParam("param") String msg) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Parl.getLangFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/language/{id}")
    @Produces("application/json;charset=utf-8")
    public Response getLang(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparqlAllFields(Parl.getLang(id));
        return Response.status(200).entity(output).build();
    }

    //

    @GET
    @Path("/party")
    @Produces("application/json;charset=utf-8")
    public Response getPartyFilter(@PathParam("param") String msg) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Parl.getPartyFilter());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/party/{id}")
    @Produces("application/json;charset=utf-8")
    public Response getParty(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparqlAllFields(Parl.getParty(id));
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/councillor")
    @Produces("application/json;charset=utf-8")
    public Response getCouncillors(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparql(Parl.getCouncillors());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/councillor/{id}")
    @Produces("application/json;charset=utf-8")
    public Response getCouncillor(@PathParam("id") int id) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = prodParl.getJsonFromSparqlAllFields(Parl.getCouncillor(id));
        return Response.status(200).entity(output).build();
    }


}