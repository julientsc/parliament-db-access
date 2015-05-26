import ch.eiafr.tsc.parl.StoreRDF;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

public class Main {

    public String getCantonFilter() {
        String queryCantonFilter = "SELECT DISTINCT ?id ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Canton> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryCantonFilter;
    }

    public String getCanton(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryCantonFilter = "SELECT DISTINCT ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Canton> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> " + strId +
                "}";
        return queryCantonFilter;
    }

    public String getCouncilFilter() {
        String queryCouncilFilter = "SELECT DISTINCT ?id ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Councils> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryCouncilFilter;
    }

    public String getCouncil(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryCouncilFilter = "SELECT DISTINCT ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Councils> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> " + strId +
                "}";
        return queryCouncilFilter;
    }

    public String getFactionFilter() {
        String queryFactionFilter = "SELECT DISTINCT ?id ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Faction> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryFactionFilter;
    }

    public String getFaction(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryFactionFilter = "SELECT DISTINCT ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Faction> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> " + strId +
                "}";
        return queryFactionFilter;
    }

    public String getPartyFilter() {
        String queryPartyFilter = "SELECT DISTINCT ?id ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Party> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryPartyFilter;
    }

    public String getParty(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryPartyFilter = "SELECT DISTINCT ?name ?abbr ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Party> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasName> ?name .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasAbbr> ?abbr .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> " + strId +
                "}";
        return queryPartyFilter;
    }


    public String getLangFilter() {
        String queryLangFilter = "SELECT DISTINCT ?id ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Lang> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> ?id\n" +
                "}";
        return queryLangFilter;
    }

    public String getLang(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryLangFilter = "SELECT DISTINCT ?item WHERE {\n" +
                "?item <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://eia-fr-ontology.ch/subject/Lang> .\n" +
                "?item <http://eia-fr-ontology.ch/predicate/hasId> " + strId +
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

    public String getCouncillor(int id) {
        String strId = "\"" + id + "\"^^<http://www.w3.org/2001/XMLSchema#int>";
        String queryCouncillorFilter = "SELECT DISTINCT * WHERE {\n" +
                "?s <http://eia-fr-ontology.ch/predicate/hasId> " + strId + ".\n" +
                "?s ?p ?o\n" +
                "}";
        return queryCouncillorFilter;
    }

    public String runParliamentQuery(String query) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
        StoreRDF parliament = StoreRDF.getInstance("prod-parliament");
        return parliament.getJsonFromSparql(query);
    }

    public String runParliamentQueryElt(String query) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
        StoreRDF parliament = StoreRDF.getInstance("prod-parliament");
        return parliament.getJsonFromSparqlAllFields(query);
    }

    public static void main(String[] args) throws RepositoryException, QueryEvaluationException, MalformedQueryException {

//
        Main main = new Main();
        String qry = main.getCouncillor(525);
        System.out.println(main.runParliamentQueryElt(qry));
    }


}
