package ch.eiafr.tsc.parl.rest;

import ch.eiafr.tsc.parl.StoreRDF;
import org.openrdf.repository.RepositoryException;

import javax.ws.rs.Path;

/**
 * Created by Julien on 26.05.15.
 */
@Path("/parl/")
public class WSFacebook {

    StoreRDF store = null;

    public WSFacebook() {
        try {
            store = StoreRDF.getInstance("prod-facebook");
        } catch (RepositoryException e) {
            System.err.println("Cannot start WSFacebook");
        }
    }


}