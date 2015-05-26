import ch.eiafr.tsc.parl.StoreRDF;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

public class Main {


    public static void main(String[] args) throws RepositoryException, QueryEvaluationException, MalformedQueryException {

        StoreRDF store = StoreRDF.getInstance("prod-twitter");

        String query = "SELECT * WHERE {\n" +
                "?s\n" +
                "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n" +
                "<http://eia-fr-ontology.ch/subject/Profile>\n" +
                "}";

        System.out.println(query);
        String content = store.getJsonFromSparql(query);

        System.out.println(content);
    }


}
