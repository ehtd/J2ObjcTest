package io.ernesto.hn.shared;

import com.google.j2objc.annotations.ObjectiveCName;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Fetcher {
    private static final Class<?> unused0 = HttpsURLConnection.class;
    private String baseURL;

    public Fetcher(String baseURL) {
        this.baseURL = baseURL;
    }

    @ObjectiveCName("fetch:")
    public @Nullable String fetch(@Nonnull String segment) {
//    public String fetch(String segment) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(this.baseURL + segment);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream in = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            return buffer.toString();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return null;
    }
}
