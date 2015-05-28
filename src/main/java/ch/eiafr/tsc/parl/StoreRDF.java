package ch.eiafr.tsc.parl;

import com.google.gson.*;
import org.openrdf.query.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Julien on 26.05.15.
 */
public class StoreRDF {

    private static HashMap<String, StoreRDF> stores = null;
    private static String sesameServer = "http://160.98.60.121:8080/openrdf-sesame/";

    private Repository repo = null;
    private HashMap<String, String> previousQueries = new HashMap<String, String>();
    private HashMap<String, String> previousQueriesComplex = new HashMap<String, String>();
    private HashMap<String, String> previousQueriesMultiple = new HashMap<String, String>();

    private StoreRDF(String repositoryID) throws RepositoryException {
        repo = new HTTPRepository(sesameServer, repositoryID);
        repo.initialize();
    }

    public static StoreRDF getInstance(String repositoryID) throws RepositoryException {
        if (stores == null)
            stores = new HashMap<String, StoreRDF>();

        if (!stores.containsKey(repositoryID))
            stores.put(repositoryID, new StoreRDF(repositoryID));

        return stores.get(repositoryID);
    }

    public String getJsonFromSparql(String sparql) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
        if (!previousQueries.containsKey(sparql)) {
            ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
            TupleQuery nameQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, sparql);
            TupleQueryResult nameResult = nameQuery.evaluate();
            while (nameResult.hasNext()) {
                BindingSet bindingSet = nameResult.next();
                HashMap<String, String> line = new HashMap<String, String>();
                for (String name : bindingSet.getBindingNames()) {
                    String value = bindingSet.getValue(name).stringValue();
                    if (value.startsWith("http://eia-fr-ontology.ch/"))
                        value = value.substring("http://eia-fr-ontology.ch/".length());
                    line.put(name, value);
                }
                results.add(line);
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            previousQueries.put(sparql, gson.toJson(results));
        }
        return previousQueries.get(sparql);
    }

    public String getJsonFromSparqlAllFields(String sparql) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
        if (!previousQueriesComplex.containsKey(sparql)) {
            HashMap<String, ArrayList<String>> results = new HashMap<String, ArrayList<String>>();
            TupleQuery nameQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, sparql);
            TupleQueryResult nameResult = nameQuery.evaluate();
            while (nameResult.hasNext()) {
                BindingSet bindingSet = nameResult.next();
                HashMap<String, String> line = new HashMap<String, String>();

                String p = bindingSet.getValue("p").stringValue();
                String o = bindingSet.getValue("o").stringValue();

                if (p.equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"))
                    continue;

                p = p.substring(p.lastIndexOf("/") + 1);

                if (!results.containsKey(p))
                    results.put(p, new ArrayList<String>());

                if (o.startsWith("http://eia-fr-ontology.ch/"))
                    o = o.substring("http://eia-fr-ontology.ch/".length());

                results.get(p).add(o);

            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            previousQueriesComplex.put(sparql, gson.toJson(results));
        }
        return previousQueriesComplex.get(sparql);
    }


    public String getJsonFromSparqlGroupBy(String sparql, String key, ArrayList<String> multi) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
        String keyA = sparql + "|" + key + "|" + multi.toString();
        if (!previousQueriesMultiple.containsKey(keyA)) {

            HashMap<String, HashMap<String, Object>> results = new HashMap<String, HashMap<String, Object>>();

            TupleQuery nameQuery = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, sparql);
            TupleQueryResult nameResult = nameQuery.evaluate();

            while (nameResult.hasNext()) {
                BindingSet bindingSet = nameResult.next();
                HashMap<String, String> line = new HashMap<String, String>();

                String keyValue = bindingSet.getValue(key).stringValue();

                if (!results.containsKey(keyValue))
                    results.put(keyValue, new HashMap<String, Object>());

                HashMap<String, Object> obj = results.get(keyValue);

                for (String v : bindingSet.getBindingNames()) {

                    if (!obj.containsKey(v)) {
                        if (multi.contains(v))
                            obj.put(v, new ArrayList<String>() /*new ItemMultiple()*/);
                        else
                            obj.put(v, new String() /*new ItemSingle()*/);
                    }

                    if (multi.contains(v)) {
                        ArrayList<String> t = (ArrayList<String>) obj.get(v);
                        String vString = bindingSet.getValue(v).stringValue();
                        if (vString.equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"))
                            continue;

                        if (vString.startsWith("http://eia-fr-ontology.ch/"))
                            vString = vString.substring("http://eia-fr-ontology.ch/".length());

                        if (!t.contains(vString))
                            t.add(vString);
                    } else {
                        String t = (String) obj.get(v);
                        String vString = bindingSet.getValue(v).stringValue();
                        if (vString.equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"))
                            continue;

                        if (vString.startsWith("http://eia-fr-ontology.ch/"))
                            vString = vString.substring("http://eia-fr-ontology.ch/".length());

                        obj.put(v, vString);
                    }

                }

            }

            ArrayList<HashMap<String, Object>> resultsList = new ArrayList<HashMap<String, Object>>();

            for (String key2 : results.keySet()) {
                resultsList.add(results.get(key2));
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String content = gson.toJson(resultsList);
            previousQueriesMultiple.put(keyA, content);
        }

        return previousQueriesMultiple.get(keyA);
    }

    class Item {

    }

    class ItemSingle extends Item {

        String item = "";

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }
    }

    class ItemMultiple extends Item {
        private ArrayList<String> items = new ArrayList<String>();

        public ArrayList<String> getItems() {
            return items;
        }
    }


}
