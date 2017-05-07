// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

import java.util.List;
import java.util.Date;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import java.util.Calendar;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.HttpVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.impl.client.DefaultHttpClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.netflix.mediaclient.Log;

public final class HttpClientFactory
{
    protected static final String CMS_BEACON_ENDPOINT_URL = "ichnaea.netflix.com";
    protected static final String CUSTOMER_EVENTS_BEACON_ENDPOINT_URL = "customerevents.netflix.com";
    private static final String DOMAIN = ".netflix.com";
    private static final int HTTP_CONN_TIMEOUT = 5000;
    private static final int HTTP_SO_TIMEOUT = 5000;
    private static final String PATH = "/";
    private static final String TAG = "nf-rest";
    protected static final String WEBAPI_ENDPOINT_URL = "api-global.netflix.com";
    private static String cmsBeaconApiEndPoint;
    private static String customerEventBeaconApiEndPoint;
    private static String webApiEndPoint;
    
    static {
        HttpClientFactory.webApiEndPoint = "api-global.netflix.com";
        HttpClientFactory.customerEventBeaconApiEndPoint = "customerevents.netflix.com";
        HttpClientFactory.cmsBeaconApiEndPoint = "ichnaea.netflix.com";
        if (Log.isLoggable()) {
            Logger.getLogger("org.apache.http.wire").setLevel(Level.FINEST);
            Logger.getLogger("org.apache.http.headers").setLevel(Level.FINEST);
            System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
            System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
            System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "debug");
            System.setProperty("log.tag.org.apache.http", "VERBOSE");
            System.setProperty("log.tag.org.apache.http.wire", "VERBOSE");
            System.setProperty("log.tag.org.apache.http.headers", "VERBOSE");
        }
    }
    
    public static String getCmsBeaconApiEndPoint() {
        return HttpClientFactory.cmsBeaconApiEndPoint;
    }
    
    public static String getCustomerEventBeaconApiEndPoint() {
        return HttpClientFactory.customerEventBeaconApiEndPoint;
    }
    
    public static DefaultHttpClient getHttpClient(final String s, final String s2) {
        final BasicHttpParams basicHttpParams = new BasicHttpParams();
        ((HttpParams)basicHttpParams).setParameter("http.protocol.version", (Object)HttpVersion.HTTP_1_1);
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 5000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 5000);
        final DefaultHttpClient defaultHttpClient = new DefaultHttpClient((HttpParams)basicHttpParams);
        final BasicCookieStore cookieStore = new BasicCookieStore();
        final BasicClientCookie basicClientCookie = new BasicClientCookie(WebApiCommand.getNetflixIdName(), s);
        basicClientCookie.setDomain(".netflix.com");
        basicClientCookie.setPath("/");
        final Calendar instance = Calendar.getInstance();
        instance.add(1, 1);
        final Date time = instance.getTime();
        basicClientCookie.setExpiryDate(time);
        final BasicClientCookie basicClientCookie2 = new BasicClientCookie(WebApiCommand.getSecureNetflixIdName(), s2);
        basicClientCookie2.setDomain(".netflix.com");
        basicClientCookie2.setPath("/");
        basicClientCookie2.setExpiryDate(time);
        basicClientCookie2.setSecure(true);
        ((CookieStore)cookieStore).addCookie((Cookie)basicClientCookie);
        ((CookieStore)cookieStore).addCookie((Cookie)basicClientCookie2);
        final List cookies = ((CookieStore)cookieStore).getCookies();
        if (Log.isLoggable()) {
            if (cookies.isEmpty()) {
                Log.d("nf-rest", "No cookies");
            }
            else {
                for (int i = 0; i < cookies.size(); ++i) {
                    Log.d("nf-rest", "Local cookie: " + cookies.get(i));
                }
            }
        }
        defaultHttpClient.setCookieStore((CookieStore)cookieStore);
        return defaultHttpClient;
    }
    
    public static String getWebApiEndPoint() {
        return HttpClientFactory.webApiEndPoint;
    }
    
    public static void setCmsBeaconApiEndPoint(final String cmsBeaconApiEndPoint) {
        HttpClientFactory.cmsBeaconApiEndPoint = cmsBeaconApiEndPoint;
    }
    
    public static void setCustomerEventBeaconApiEndPoint(final String customerEventBeaconApiEndPoint) {
        HttpClientFactory.customerEventBeaconApiEndPoint = customerEventBeaconApiEndPoint;
    }
    
    public static void setWebApiEndPoint(final String webApiEndPoint) {
        HttpClientFactory.webApiEndPoint = webApiEndPoint;
    }
}
