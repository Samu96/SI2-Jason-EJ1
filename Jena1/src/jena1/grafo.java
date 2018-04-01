package jena1;

import java.io.StringWriter;
import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.json.JSONException;
import org.json.JSONObject;

class grafo {
    private final Model Modelo;
    private static final String station = "http://si2.samuelrodriguez.es/";
    private static final String aemet = "Http://aemet.linkeddata.es/ontology/";
    private static final String spatialThing = "http://www.w3.org/2003/01/geo/wgs84_pos#";
    private Property EstacionNombre;
    private Property Latitud;
    private Property Altitud;
    private Property Longitud;
    private Property Indicador;
    private Property provincia;
    
    public grafo() {
        LogCtl.setCmdLogging();
        Modelo = ModelFactory.createDefaultModel();
        Modelo.setNsPrefix("station", station);
        Modelo.setNsPrefix("aemet", aemet);
        Modelo.setNsPrefix("spatial", spatialThing);
        EstacionNombre = ResourceFactory.createProperty(aemet , "stationName" );
        Latitud = ResourceFactory.createProperty(spatialThing, "latitud");
        Altitud = ResourceFactory.createProperty(spatialThing , "alitudt" );
        Longitud = ResourceFactory.createProperty(spatialThing , "longitud" );
        Indicador = ResourceFactory.createProperty(aemet , "indsinop" );
        provincia = ResourceFactory.createProperty(aemet , "Province" );
    }

    void addEstation(JSONObject jsonObject) {
        try{
            Resource resource = Modelo.createResource(station + jsonObject.getString("indicativo"));
            resource.addLiteral(EstacionNombre, jsonObject.getString("nombre"));
            resource.addLiteral(Latitud, jsonObject.getString("latitud"));
            resource.addLiteral(Altitud, jsonObject.getString("altitud"));
            resource.addLiteral(Longitud, jsonObject.getString("longitud"));
            resource.addLiteral(Indicador, jsonObject.getString("indisinop"));
            resource.addLiteral(provincia, jsonObject.getString("provincia"));
        }catch(JSONException e){
        }
    }
    public String tripletas(RDFFormat eleccion){
        StringWriter sw = new StringWriter();
        RDFDataMgr.write(sw, Modelo, eleccion);
        return sw.toString();
    }
}
