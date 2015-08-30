package com.ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * Created by dima on 8/30/2015.
 */
public class Login {
/*
    private static DefaultHttpClient httpClient;

    private static DefaultHttpClient getHttpClient() {
        if (httpClient == null) {
            PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
            cm.setMaxTotal(100);
            httpClient = new DefaultHttpClient(cm);
        }
        return httpClient;
    }
*/
    //HttpClient
    public static CloseableHttpClient httpclient = null;

    //Response handler
    public static ResponseHandler<String> mResponseHandler = new ResponseHandler<String>()
    {
        @Override
        public String handleResponse(final HttpResponse response) throws IOException
        {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300)
            {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            }
            return "\nERROR No " + status + "!!!\n" + EntityUtils.toString(response.getEntity()) + "\n^^^^^^\n";
        }
    };


    public static void login() throws IOException {
        httpclient = HttpClients.createDefault();

        //HttpPost loginRequest = new HttpPost("http://cells.org.ua/scrum-selenium/admin/pageHome.php");
        HttpPost loginRequest = new HttpPost("http://vlay.pythonanywhere.com/test_site/default/user/login");
        //HttpGet loginRequest = new HttpGet("http://vlay.pythonanywhere.com/test_site/default/user/login");
        List<NameValuePair> credentials = new ArrayList<NameValuePair>();
        //credentials.add(new BasicNameValuePair("username", "admin"));
        //credentials.add(new BasicNameValuePair("password", "admin"));
        credentials.add(new BasicNameValuePair("email", "admin"));
        credentials.add(new BasicNameValuePair("password", "admin"));
        loginRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));
        //
        // HttpResponse response = getHttpClient().execute(loginRequest);
        //Creating response for login request
        // CloseableHttpResponse response = httpclient.execute(loginRequest);
        String response = httpclient.execute(loginRequest,mResponseHandler);
        System.out.println(response.toString());
        //return response.getStatusLine().getStatusCode();
    }

    protected static void setParamsToAuth(HttpPost msg)
    {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("LOGIN", "admin"));
        nvps.add(new BasicNameValuePair("PWD", "admin"));
        nvps.add(new BasicNameValuePair("SYSTEM", "ELVIS"));
        nvps.add(new BasicNameValuePair("PAGE", "1"));
        nvps.add(new BasicNameValuePair("LANG", "0"));
        msg.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
    }

}
