package ch.eiafr.tsc.parl.sparql;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


    private static String getTweets(ArrayList<Integer> ids) {
        String queryPart = "";

        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            queryPart += "\n{\n" +

                    "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Tweet> .\n" +
                    "?item <http://eia-fr-ontology.ch/predicate/hasDate> ?date .\n" +
                    "<http://eia-fr-ontology.ch/user/311941092> <http://eia-fr-ontology.ch/predicate/wrote> ?item .\n" +


                    "SERVICE <http://localhost:8080/openrdf-sesame/repositories/prod-linkParlSocial> {\n" +
                    "?councillor <http://eia-fr-ontology.ch/predicate/hasSocialProfile> ?item\n" +
                    "}\n\n" +

                    "SERVICE <http://localhost:8080/openrdf-sesame/repositories/prod-parliament> {\n" +
                    "?councillor <http://eia-fr-ontology.ch/predicate/hasId> " + id + " . \n" +
                    "}\n\n" +

                    "}\n\n\n";

            if (i + 1 != ids.size())
                queryPart += "\n UNION \n";
        }

        return queryPart;
    }

    public static String getTweetsCountYear(ArrayList<Integer> ids, String startDate, String stopDate) {
        String query = "SELECT (COUNT(?item) as ?total) ?displayDate\n" +
                "WHERE {\n" +
                getTweets(ids) +
                "}\n" +
                "group by ((STR(YEAR(?date))) as ?displayDate)";

        return query;
    }

    public static String getTweetsCountMonth(ArrayList<Integer> ids, String startDate, String stopDate) {
        String query = "SELECT (COUNT(?item) as ?total) ?displayDate\n" +
                "WHERE {\n" +
                getTweets(ids) +
                "}\n" +
                "group by ((CONCAT(STR(MONTH(?date)), \"-\", STR(YEAR(?date))) as ?displayDate))";

        return query;
    }

    public static String getTweetsCountDay(ArrayList<Integer> ids, String startDate, String stopDate) {
        String query = "SELECT (COUNT(?item) as ?total) ?displayDate\n" +
                "WHERE {\n" +
                getTweets(ids) +
                "}\n" +
                "group by ((CONCAT( STR(DAY(?date)), \"-\", STR(MONTH(?date)), \"-\", STR(YEAR(?date))) as ?displayDate))";

        return query;
    }

}
