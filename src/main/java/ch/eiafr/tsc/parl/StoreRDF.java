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
    private static String sesameServer = "http://160.98.60.121:8080/openrdf-sesame/";

    private Repository repo = null;

    public static StoreRDF getInstance(String repositoryID) throws RepositoryException {
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

    public String getJsonFromSparql(String sparql) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
        TupleQuery nameQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, sparql);
        TupleQueryResult nameResult = nameQuery.evaluate();
        while (nameResult.hasNext()) {
            BindingSet bindingSet = nameResult.next();
            HashMap<String, String> line = new HashMap<String, String>();
            for(String name : bindingSet.getBindingNames()) {
                String value = bindingSet.getValue(name).stringValue();
                line.put(name, value);
            }
            results.add(line);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(results);
    }

    public String getJsonFromSparqlAllFields(String sparql) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
        HashMap<String, ArrayList<String>> results = new HashMap<String, ArrayList<String>>();
        TupleQuery nameQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, sparql);
        TupleQueryResult nameResult = nameQuery.evaluate();
        while (nameResult.hasNext()) {
            BindingSet bindingSet = nameResult.next();
            HashMap<String, String> line = new HashMap<String, String>();

            String p =bindingSet.getValue("p").stringValue();
            String o =bindingSet.getValue("o").stringValue();

            if(p.equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"))
                continue;

            p = p.substring(p.lastIndexOf("/") + 1);

            if(!results.containsKey(p))
                results.put(p, new ArrayList<String>());

            if(o.startsWith("http://eia-fr-ontology.ch/"))
                o = o.substring("http://eia-fr-ontology.ch/".length());

            results.get(p).add(o);

        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(results);
    }


}
