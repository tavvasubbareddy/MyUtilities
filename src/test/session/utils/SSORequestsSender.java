package test.session.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * Sends multiple requests under same session. It enables us to send CRUD (Create, Read, Update & Delete) requests under
 * same session. It helps to test SSO based scenarios.
 * 
 */
public class SSORequestsSender
{
    private static String cookie = "";

    /**
     * Sends multiple subsequent requests under same session. It helps to test SSO based scenarios.
     * 
     * @param url
     *            Server url to be invoked
     * @return response
     * @throws IOException
     */
    public static String sendRequestsInSameSession(String url) throws IOException
    {
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod("POST");
        conn.setUseCaches(true);
        conn.setRequestProperty("Cookie", cookie);
        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        conn.getResponseCode();

        String key;
        for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++)
        {
            if (key.equalsIgnoreCase("Set-Cookie"))
            {
                if (cookie.equals(""))
                {
                    cookie = cookie + conn.getHeaderField(i);
                    cookie = cookie.substring(0, cookie.indexOf(";"));
                }
                else
                {
                    cookie = cookie + ";" + conn.getHeaderField(i);
                }
            }
        }

        return reader.readLine();
    }
}
