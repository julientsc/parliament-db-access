package ch.eiafr.tsc.parl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openrdf.query.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Julien on 26.05.15.
 */
public class StoreRDF {

    private static HashMap<String, StoreRDF> stores = null;
    private static String sesameServer = "http://semantic.ilab-research.ch:8080";

    private Repository repo = null;

    private static StoreRDF getInstance(String repositoryID) throws RepositoryException {
        if(stores == null)
            stores = new HashMap<String, StoreRDF>();

        if(!stores.containsKey(repositoryID))
            stores.put(repositoryID, new StoreRDF(repositoryID));

        return stores.get(repositoryID);
    }

    private StoreRDF(String repositoryID) throws RepositoryException {

        repo = new HTTPRepository(sesameServer, repositoryID);
        repo.initialize();
    }

    private String getJsonFromSparql(String sparql) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
        ArrayList<HashMap<String, String>>  results = new ArrayList<HashMap<String, String>>();
        TupleQuery nameQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, sparql);
        TupleQueryResult nameResult = nameQuery.evaluate();

        while (nameResult.hasNext()) {
            BindingSet bindingSet = nameResult.next();
            HashMap<String, String> line = new HashMap<String, String>();
            for(String name : bindingSet.getBindingNames()) {
                String value =bindingSet.getValue(name).stringValue();
                line.put(name, value);
            }
            results.add(line);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(results);
    }
}
