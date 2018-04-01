package jena1;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Jena1 {
    static String URI = "https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcmcuZGlvbmlzb0BnbWFpbC5jb20iLCJqdGkiOiI3ZDgyMTIxZC05MDQ1LTRlOTQtYmU0Ny00Y2U4ODc4ODNjNTQiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTUyMTU0NzQyNSwidXNlcklkIjoiN2Q4MjEyMWQtOTA0NS00ZTk0LWJlNDctNGNlODg3ODgzYzU0Iiwicm9sZSI6IiJ9.SCuy27CoNWXztjFOHxgcnFVC2LIz_IT8fVxdUM8WgNA";
    OkHttpClient client= new OkHttpClient();
    private grafo grafo = new grafo();

    public grafo creaGrafo() throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "F:\\Users\\srgdi\\keystore.jks");
        Jena1 ejercicio = new Jena1();
        JSONObject respObject = new JSONObject(ejercicio.getResponse(URI));
        
        String Datos = respObject.get("datos").toString();
        
        JSONArray array = new JSONArray(ejercicio.getResponse(Datos));
        return grafo= addEstation(array);
    }
    
    public String getResponse(String ini){
        Request request = new Request.Builder()
        .url(ini)
        .get()
        .addHeader("cache-control", "no-cache")
        .build();
        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        }catch(IOException ex){
            return null;
        }
    }
    
    public grafo addEstation(JSONArray datos){
        try{
            for (int i = 0; i < datos.length(); i++) {
                grafo.addEstation(datos.getJSONObject(i));
            }
            return grafo;
        }catch(JSONException e){
            return null;
        }
    }
}
