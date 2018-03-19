
package ejercicio1;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;


public class Ejercicio1 {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
        .url("https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW11ZWwucm9kcmlndWV6MTIwQGFsdS51bHBnYy5lcyIsImp0aSI6Ijc4YzUzMDNkLTJjMGEtNGYxNS1hODMwLTAyOWNlZTFhOGRjMSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNTIxNDU2Mjg5LCJ1c2VySWQiOiI3OGM1MzAzZC0yYzBhLTRmMTUtYTgzMC0wMjljZWUxYThkYzEiLCJyb2xlIjoiIn0.Frgyb_uYxCWOcn-mABb37b3FPyxkc_6cL840ST-WCfE")
        .get()
        .addHeader("cache-control", "no-cache")
        .build();

        Response response = client.newCall(request).execute();
        LogCtl.setCmdLogging();
        Model modelo = ModelFactory.createDefaultModel();
        
    }
    
}
