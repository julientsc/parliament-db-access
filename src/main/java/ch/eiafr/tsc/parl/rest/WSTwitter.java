package ch.eiafr.tsc.parl.rest;

import ch.eiafr.tsc.parl.StoreRDF;
import org.openrdf.repository.RepositoryException;

import javax.ws.rs.Path;

/**
 * Created by Julien on 26.05.15.
 */
@Path("/parl/")
public class WSTwitter {

    StoreRDF store = null;

    public WSTwitter() {
        try {
            store = StoreRDF.getInstance("prod-twitter");
        } catch (RepositoryException e) {
            System.err.println("Cannot start Twitter");
        }
    }


}