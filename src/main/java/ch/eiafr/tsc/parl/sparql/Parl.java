package ch.eiafr.tsc.parl.sparql;

/**
 * Created by Julien on 26.05.15.
 */
public class Parl {
    public static String getCantonFilter() {
        String queryCantonFilter = "SELECT DISTINCT ?id ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Canton> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryCantonFilter;
    }

    public static String getCanton(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryCantonFilter = "SELECT DISTINCT * WHERE {\n" +
                "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Canton> .\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + strId + ".\n" +
                "?s ?p ?o\n" +
                "}";
        return queryCantonFilter;
    }

    public static String getCouncilFilter() {
        String queryCouncilFilter = "SELECT DISTINCT ?id ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Councils> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryCouncilFilter;
    }

    public static String getCouncil(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryCouncilFilter = "SELECT DISTINCT * WHERE {\n" +
                "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Councils> .\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + strId + ".\n" +
                "?s ?p ?o\n" +
                "}";
        return queryCouncilFilter;
    }

    public static String getFactionFilter() {
        String queryFactionFilter = "SELECT DISTINCT ?id ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Faction> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryFactionFilter;
    }

    public static String getFaction(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryFactionFilter = "SELECT DISTINCT * WHERE {\n" +
                "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Faction> .\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + strId + ".\n" +
                "?s ?p ?o\n" +
                "}";
        return queryFactionFilter;
    }

    public static String getPartyFilter() {
        String queryPartyFilter = "SELECT DISTINCT ?id ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Party> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryPartyFilter;
    }

    public static String getParty(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryPartyFilter = "SELECT DISTINCT * WHERE {\n" +
                "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Party> .\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + strId + ".\n" +
                "?s ?p ?o\n" +
                "}";
        return queryPartyFilter;
    }


    public static String getLangFilter() {
        String queryLangFilter = "SELECT DISTINCT ?id ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Lang> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryLangFilter;
    }

    public static String getLang(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryLangFilter = "SELECT DISTINCT * WHERE {\n" +
                "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Lang> .\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + strId + ".\n" +
                "?s ?p ?o\n" +
                "}";
        return queryLangFilter;
    }


    public String getCouncillorFilter() {
        String queryCouncillorFilter = "SELECT DISTINCT ?id ?firstname ?lastname ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Councillor> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasFirstName> ?firstname .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasLastName> ?lastname .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id.\n" +
                "?item <http://eia-fr-ontology.ch/predicate/isActive> true .\n" +
                "}";
        return queryCouncillorFilter;
    }

    //TODO: set the rdf-syntax for councillor
    public static String getCouncillor(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryCouncillorFilter = "SELECT DISTINCT * WHERE {\n" +
                "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Councillor> .\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + strId + ".\n" +
                "?s ?p ?o\n" +
                "}";
        return queryCouncillorFilter;
    }
}
