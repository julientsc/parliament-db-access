package ch.eiafr.tsc.parl.sparql;

/**
 * Created by Julien on 26.05.15.
 */
public class Facebook {

    public static String getParlFacebookAccountList() {
        String query = "SELECT ?id ?link WHERE {\n" +
                "?item\n" +
                "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n" +
                "<http://eia-fr-ontology.ch/subject/FacebookPage> .\n" +
                "\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasLink> ?link .\n" +
                "}";
        return query;
    }

    public static String getAccount(long id) {
        String query = "";
        return query;
    }

    public static String getStatusListFromAccount(long id, long startDate, long stopDate) {
        String query = "";
        return query;
    }

    public static String getStatus(long id) {
        String query = "";
        return query;
    }
}
