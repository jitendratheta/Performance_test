package com.flipkart;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jitendra.k on 13/07/16.
 */
public class HttpHelper {

    private static final String USER_AGENT = "Java_app";

    // HTTP GET request
    public static Response sendGet(String url) throws Exception {

        //String url = "http://ekart-flash-stage-0002.stage.nm.flipkart.com:21004/api/v1/serviceability/560034/pickup";

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        long startTime = System.currentTimeMillis();

        HttpResponse response = client.execute(request);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        //System.out.println(totalTime);

        Response res = new Response();
        res.setResponseTime(totalTime);
        res.setResponseCode(response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        res.setResponseBody(result.toString());

        return res;
    }
}
