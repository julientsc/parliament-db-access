package ch.eiafr.tsc.parl.sparql;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Julien on 26.05.15.
 */
public class Twitter {

    public static String getParlTwitterAccountList() {
        String query = "";
        return query;
    }

    public static String getFriends(long id) {
        String query = "SELECT ?friend WHERE {\n" +
                "?user <http://eia-fr-ontology.ch/predicate/hasId> ?id .\n" +
                "?user <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/TwitterProfile>.\n" +
                "?user <http://eia-fr-ontology.ch/predicate/hasFriend> ?friend .\n" +
                "FILTER (str(?id) = \"" + id + "\") .\n" +
                "} ";
        return query;
    }

    public static String getFollower(long id) {
        String query = "SELECT ?follower WHERE {\n" +
                "?user <http://eia-fr-ontology.ch/predicate/hasId> ?id .\n" +
                "?user <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/TwitterProfile>.\n" +
                "?user <http://eia-fr-ontology.ch/predicate/hasFollower> ?follower .\n" +
                "FILTER (str(?id) = \"" + id + "\") .\n" +
                "}  ";
        return query;
    }

    public static String getAccount(long id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String query = "SELECT * WHERE {\n" +
                "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n" +
                "<http://eia-fr-ontology.ch/subject/TwitterProfile>\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + strId + ".\n" +
                "?s ?p ?o .\n" +
                "}";
        return query;
    }

    public static String getTweetsListFromAccount(long id, String startDate, String stopDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date start = new Date(Long.parseLong(startDate));
        Date stop = new Date(Long.parseLong(stopDate));
        String startS = sdf.format(start);
        String stopS = sdf.format(stop);
        String query = "SELECT ?item ?id ?date WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Tweet>.\n" +
                "<http://eia-fr-ontology.ch/user/311941092> <http://eia-fr-ontology.ch/predicate/wrote> ?item .\n" +
                "<http://eia-fr-ontology.ch/user/311941092> <http://eia-fr-ontology.ch/predicate/hasId> ?uid.\n" +
                "<http://eia-fr-ontology.ch/status/444433354872877058> <http://eia-fr-ontology.ch/predicate/hasDate> ?date.\n" +
                "\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id .\n" +
                "\n" +
                "FILTER (str(?uid) = \"" + id + "\").\n" +
                "\n" +
                "\n" +
                "FILTER(?date >= \"" + startS + "\"^^xsd:dateTime) .\n" +
                "FILTER(?date <= \"" + stopS + "\"^^xsd:dateTime) .\n" +
                "\n" +
                "}";
        return query;
    }

    public static String getTweet(long id) {
        String query = "SELECT * WHERE {\n" +
                "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n" +
                "<http://eia-fr-ontology.ch/subject/Tweet>\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + id + ".\n" +
                "?s ?p ?o .\n" +
                "}";
        return query;
    }

}
