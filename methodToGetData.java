import org.json.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

        if(dataLengthChoice.equals("a"))
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
        int stop = result.indexOf(",\"probableCases\"");
        result = result.substring(0,stop);

        return result;
    }
    public String getDate(String jSON)
    {
        return jSON.substring(jSON.indexOf("date\":") + 5, jSON.indexOf("2") + 7);
    }
    public String getTotalCases(String jSON)
    {
        return jSON.substring(jSON.indexOf("positive") + 9, jSON.indexOf(","));
    }
}
