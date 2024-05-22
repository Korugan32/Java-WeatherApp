import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {

    private StringBuffer response = new StringBuffer();
    private JSONParser parser = new JSONParser();
    private JSONObject result;

    String key = "api_key";

    public void ConnectionUrl(String location) throws IOException, ParseException {
        URL url = new URL("https://api.weatherapi.com/v1/current.json?key="+key+"&q=" + location + "&aqi=no");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        result = (JSONObject) parser.parse(response.toString());
    }

    public String getTemp() {
        JSONObject current = (JSONObject) result.get("current");
        double temp = (double) current.get("temp_c");
        return String.valueOf(temp);
    }

    public String getStatus() {
        JSONObject current = (JSONObject) result.get("current");
        JSONObject condition = (JSONObject) current.get("condition");
        return condition.get("text").toString();
    }
}
