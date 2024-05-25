import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {
    private final StringBuffer response = new StringBuffer();
    private final JSONParser parser = new JSONParser();
    private JSONObject result;
    private final String key = "d47f96efd4814cfbbdb123512242505";

    public void connect(String location) throws IOException, ParseException {
        URL url = new URL("https://api.weatherapi.com/v1/current.json?key=" + key + "&q=" + location + "&aqi=no");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        result = (JSONObject) parser.parse(response.toString());
    }

    public String getTemp() {
        JSONObject current = (JSONObject) result.get("current");
        return String.valueOf(current.get("temp_c"));
    }

    public String getStatus() {
        JSONObject current = (JSONObject) result.get("current");
        JSONObject condition = (JSONObject) current.get("condition");
        return condition.get("text").toString();
    }
}
