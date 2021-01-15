import org.json.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class methodToGetData
{

    private static URL connect;
    private static HttpURLConnection connection;
    private static final int FIVE_SECONDS = 5000;
    private static StringBuffer stringBuffer;
    private static String temp;

    public methodToGetData() {
    }
    public String getData(String stateChoice, String dataLengthChoice) throws IOException {

        if(dataLengthChoice.equals("Historic"))
        {
            connect = new URL("https://api.covidtracking.com/v1/states/" + stateChoice + "/daily.json");
        }
        else
        {
            connect = new URL("https://api.covidtracking.com/v1/states/" + stateChoice + "/current.json");
        }
        connection = (HttpURLConnection) connect.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(FIVE_SECONDS);
        connection.setReadTimeout(FIVE_SECONDS);

        Scanner fromWebsite = new Scanner(connect.openStream());
        StringBuffer stringBuffer = new StringBuffer();

        while(fromWebsite.hasNext())
        {
            stringBuffer.append(fromWebsite.next() + " ");
        }
        String result = stringBuffer.toString();
        result = result.replaceAll("<br>", "\n");
        result = result.replaceAll("<[^>]*>", "");
        try {
            if(dataLengthChoice.equals("Current")) {
                JSONObject obj = new JSONObject(result);
                String date = "" + obj.getInt("date");
                date = date.substring(0,4) + "/" + date.substring(4,6) + "/" + date.substring(6,8);
                result = "Date:  " + date + "   Deaths this Day: " + obj.getInt("deathIncrease") + "   Total positive: " + obj.getInt("positive") +   "   Total Deaths: " + obj.getInt("death") + "   Positive Increase: " + obj.getInt("positiveIncrease") + "\n";
            }
            else
            {
                JSONArray objArray = new JSONArray(result);
                result = "";
                for(int i = 0; i < objArray.length(); i++)
                {
                    JSONObject temp = objArray.getJSONObject(i);
                    String date = "" + temp.get("date");
                    date = date.substring(0,4) + "/" + date.substring(4,6) + "/" + date.substring(6,8);
                    result += "Date: " + date + "   Deaths this Day: " + temp.get("deathIncrease") + "   Total Positive: " + temp.get("positive") + "   Total Deaths: " + temp.get("death") + "   Positive Increase: " + temp.get("positiveIncrease") + "\n";

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
