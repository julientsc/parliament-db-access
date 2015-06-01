import ch.eiafr.tsc.parl.StoreRDF;
import ch.eiafr.tsc.parl.sparql.Twitter;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import sun.util.resources.CalendarData_en_IE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {


    public static void main(String[] args) throws RepositoryException, QueryEvaluationException, MalformedQueryException {

        /*
        String query = Twitter.getTweetsListFromAccount(311941092, "1401189890334", "1432725890309");
        System.out.println(query);
*/

        ArrayList<Integer> lst = new ArrayList<Integer>();
        lst.add(4091);
        String query = Twitter.getTweetsCountYear(lst, "1401189890334", "1432725890309");
        System.out.println(query);

        /*
        StoreRDF store = StoreRDF.getInstance("prod-twitter");

        String query = "SELECT * WHERE {\n" +
                "?s\n" +
                "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n" +
                "<http://eia-fr-ontology.ch/subject/Profile>\n" +
                "}";

        System.out.println(query);
        String content = store.getJsonFromSparql(query);

        System.out.println(content);*/
    }


}
