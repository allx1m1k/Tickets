package com.ticket;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 8/30/2015.
 */
public class LoginOriginal {

    private static DefaultHttpClient httpClient;

    private static DefaultHttpClient getHttpClient() {
        if (httpClient == null) {
            PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
            cm.setMaxTotal(100);
            httpClient = new DefaultHttpClient(cm);
        }
        return httpClient;
    }

    public static int login() throws IOException {
        //CloseableHttpClient httpclient = HttpClients.createDefault();

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
        HttpResponse response = getHttpClient().execute(loginRequest);
        //Creating response for login request
        //CloseableHttpResponse response = httpclient.execute(loginRequest);
        System.out.println(response.toString());
        return response.getStatusLine().getStatusCode();
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
