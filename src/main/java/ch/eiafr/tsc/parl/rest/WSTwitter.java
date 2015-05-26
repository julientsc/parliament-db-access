package ch.eiafr.tsc.parl.rest;

import ch.eiafr.tsc.parl.StoreRDF;
import ch.eiafr.tsc.parl.sparql.Twitter;
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
@Path("/twitter/")
public class WSTwitter {

    StoreRDF store = null;

    public WSTwitter() {
        try {
            store = StoreRDF.getInstance("prod-twitter");
        } catch (RepositoryException e) {
            System.err.println("Cannot start Twitter");
        }
    }

    @GET
    @Path("/account/{accountId}")
    @Produces("application/json;charset=utf-8")
    public Response getAccount(@PathParam("accountId") long accountId) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Twitter.getAccount(accountId));
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/account/{accountId}")
    @Produces("application/json;charset=utf-8")
    public Response getFollower(@PathParam("accountId") long accountId) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Twitter.getFollower(accountId));
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/account/{accountId}")
    @Produces("application/json;charset=utf-8")
    public Response getFriends(@PathParam("accountId") long accountId) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Twitter.getFriends(accountId));
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/parl")
    @Produces("application/json;charset=utf-8")
    public Response getParlTwitterAccountList() throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Twitter.getParlTwitterAccountList());
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/tweet/{tweetId}")
    @Produces("application/json;charset=utf-8")
    public Response getTweet(@PathParam("tweetId") long tweetId) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Twitter.getTweet(tweetId));
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/tweet/{accountId}/{startDate}/{stopDate}")
    @Produces("application/json;charset=utf-8")
    public Response getTweetsListFromAccount(@PathParam("accountId") long accountId, @PathParam("startDate") long startDate, @PathParam("stopDate") long stopDate) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
        String output = store.getJsonFromSparql(Twitter.getTweetsListFromAccount(accountId, startDate, stopDate));
        return Response.status(200).entity(output).build();
    }



}