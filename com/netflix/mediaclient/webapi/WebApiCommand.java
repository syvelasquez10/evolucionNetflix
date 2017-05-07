// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

import java.io.IOException;
import org.apache.http.StatusLine;
import android.util.Pair;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import com.android.volley.toolbox.InputStreamUtil;
import org.json.JSONException;
import org.apache.http.HttpException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import com.netflix.mediaclient.Log;
import org.apache.http.cookie.Cookie;
import java.util.List;

public abstract class WebApiCommand
{
    private static final String COOKIE_SET_CHECK = "Set-Cookie";
    protected static final String HTTP = "http://";
    protected static final String HTTPS = "https://";
    protected static final int MAX_RE_EXECUTION_TIMES = 5;
    private static final String NETFLIX_ID = "NetflixId";
    private static final String NETFLIX_ID_TEST = "NetflixIdTest";
    protected static final String OUTPUT_JSON = "json";
    protected static final String OUTPUT_XML = "xml";
    protected static final String PARAMETER_APP_VERSION = "app_version";
    protected static final String PARAMETER_COUNTRY = "country";
    protected static final String PARAMETER_DEVICE_CAT = "device_cat";
    protected static final String PARAMETER_DEVICE_TYPE = "device_type";
    protected static final String PARAMETER_ESN = "esn";
    protected static final String PARAMETER_GEOLOCATION_COUNTRY = "geolocation_country";
    protected static final String PARAMETER_LANGUAGES = "languages";
    protected static final String PARAMETER_OS_VERSION = "os_version";
    protected static final String PARAMETER_OUTPUT = "output";
    protected static final String PARAMETER_ROUTING = "routing";
    protected static final String PARAMETER_TIMESTAMP = "timestamp";
    protected static final String PARAMETER_UI_VERSION = "ui_version";
    protected static final String PARAMETER_UUID = "uuid";
    protected static final String PARAMETER_VERSION = "v";
    protected static final String PARAMETER_WITH_CREDENTIALS = "withCredentials";
    protected static final String PATH_USERS = "/users";
    protected static final String PATH_USERS_CURRENT = "/users/current";
    protected static final String ROUTING_EMPTY = "reject-empty";
    protected static final String ROUTING_REDIRECT = "redirect";
    protected static final String ROUTING_REJECT = "reject";
    private static final String SECURE_NETFLIX_ID = "SecureNetflixId";
    private static final String SECURE_NETFLIX_ID_TEST = "SecureNetflixIdTest";
    protected static final String TAG = "nf_rest";
    protected static final String VERSION_20 = "2.0";
    protected CommonRequestParameters commonRequestParameters;
    private int count;
    protected AuthorizationCredentials credentials;
    protected String esn;
    protected boolean updated;
    
    protected WebApiCommand(final String s, final AuthorizationCredentials credentials, final CommonRequestParameters commonRequestParameters) {
        if (credentials == null) {
            throw new IllegalArgumentException("Netflix id or secure netflix id is null!");
        }
        this.credentials = credentials;
        this.commonRequestParameters = commonRequestParameters;
    }
    
    private void checkForCredentialUpdate(final List<Cookie> list) {
        if (list.isEmpty()) {
            Log.d("nf_rest", "No cookies in response");
        }
        else {
            for (int i = 0; i < list.size(); ++i) {
                if (Log.isLoggable("nf_rest", 3)) {
                    Log.d("nf_rest", "Response cookie: " + list.get(i));
                }
                if ("NetflixId".equals(list.get(i).getName())) {
                    this.credentials.netflixId = this.updateProperty(list.get(i), this.credentials.netflixId);
                }
                if ("SecureNetflixId".equals(list.get(i).getName())) {
                    this.credentials.secureNetflixId = this.updateProperty(list.get(i), this.credentials.secureNetflixId);
                }
            }
        }
    }
    
    private void dumpCookies(final HttpResponse httpResponse) {
        if (Log.isLoggable("nf_rest", 3)) {
            int n = 0;
            int n2 = 0;
            final Header[] allHeaders = httpResponse.getAllHeaders();
            if (allHeaders != null && allHeaders.length > 0) {
                Log.d("nf_rest", "We got headers: " + allHeaders.length);
                final int length = allHeaders.length;
                int n3 = 0;
                while (true) {
                    n = n2;
                    if (n3 >= length) {
                        break;
                    }
                    final Header header = allHeaders[n3];
                    Log.d("nf_rest", "" + header);
                    int n4 = n2;
                    if (header != null) {
                        n4 = n2;
                        if (header.getValue().contains("Set-Cookie")) {
                            n4 = n2 + 1;
                        }
                    }
                    ++n3;
                    n2 = n4;
                }
            }
            else {
                Log.e("nf_rest", "No headers!!!");
            }
            Log.d("nf_rest", "Cookies set in response: " + n);
            Log.d("nf_rest", "----------------------------------------");
            Log.d("nf_rest", "Response status line: " + httpResponse.getStatusLine());
            final HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                Log.d("nf_rest", "Response content length: " + entity.getContentLength());
            }
        }
    }
    
    public static String getNetflixIdName() {
        return "NetflixId";
    }
    
    public static String getSecureNetflixIdName() {
        return "SecureNetflixId";
    }
    
    private String updateProperty(final Cookie cookie, final String s) {
        final String name = cookie.getName();
        final String value = cookie.getValue();
        if (s == null || s.equals(value)) {
            if (Log.isLoggable("nf_rest", 3)) {
                Log.d("nf_rest", name + " is not changed");
            }
            return s;
        }
        if (Log.isLoggable("nf_rest", 3)) {
            Log.d("nf_rest", name + " is now " + value);
        }
        this.updated = true;
        return value;
    }
    
    protected void addIfNotNull(final StringBuilder sb, final String s, final Boolean b) {
        if (b != null) {
            sb.append('&').append(s).append('=').append(b);
        }
    }
    
    protected void addIfNotNull(final StringBuilder sb, final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        try {
            sb.append('&').append(s).append('=').append(URLEncoder.encode(s2, "utf-8"));
        }
        catch (UnsupportedEncodingException ex) {
            Log.e("nf_rest", "Failed with encoding", ex);
        }
    }
    
    protected boolean checkStatus(final int n, String string) throws JSONException, HttpException {
        boolean b = false;
        if (n == 410) {
            Log.w("nf_rest", "Received 410: Redirecting... ");
            string = new JSONObject(string).getString("host");
            if (Log.isLoggable("nf_rest", 3)) {
                Log.d("nf_rest", "Response 410: new WebAPI end point received: " + string);
            }
            HttpClientFactory.setWebApiEndPoint(string);
            b = true;
        }
        else {
            if (n == 200) {
                Log.d("nf_rest", "Response 200. All good");
                return false;
            }
            if (n <= 200 || n > 399) {
                if (Log.isLoggable("nf_rest", 6)) {
                    Log.e("nf_rest", "Response " + n);
                }
                throw new HttpException("Failed with response code " + n);
            }
            if (Log.isLoggable("nf_rest", 3)) {
                Log.d("nf_rest", "Response " + n);
                return false;
            }
        }
        return b;
    }
    
    protected String doExecute() throws IOException, JSONException, HttpException {
        while (true) {
            DefaultHttpClient defaultHttpClient = null;
            DefaultHttpClient httpClient = null;
        Label_0592:
            while (true) {
                try {
                    httpClient = HttpClientFactory.getHttpClient(this.credentials.netflixId, this.credentials.secureNetflixId);
                    final boolean checkStatus = true;
                    if (!checkStatus) {
                        break Label_0592;
                    }
                    defaultHttpClient = httpClient;
                    if (this.count > 5) {
                        defaultHttpClient = httpClient;
                        throw new HttpException("Too many retries!");
                    }
                }
                finally {
                    defaultHttpClient.getConnectionManager().shutdown();
                }
                ++this.count;
                final HttpUriRequest httpMethod = this.getHttpMethod();
                final Pair<String, String>[] headers = this.getHeaders();
                if (headers != null && headers.length > 0) {
                    for (int i = 0; i < headers.length; ++i) {
                        if (Log.isLoggable("nf_rest", 3)) {
                            Log.d("nf_rest", "Add header " + (String)headers[i].first + " with value: " + (String)headers[i].second);
                        }
                        if (headers[i].first != null) {
                            if (headers[i].second != null) {
                                httpMethod.setHeader((String)headers[i].first, (String)headers[i].second);
                            }
                        }
                    }
                }
                else {
                    Log.d("nf_rest", "No header found");
                }
                if (Log.isLoggable("nf_rest", 3)) {
                    Log.d("nf_rest", "executing request " + httpMethod.getURI() + ", attempt: " + this.count);
                }
                final HttpResponse execute = httpClient.execute(httpMethod);
                final HttpEntity entity = execute.getEntity();
                final StatusLine statusLine = execute.getStatusLine();
                if (statusLine == null) {
                    Log.e("nf_rest", "Status is NULL. It should NOT happen!");
                    throw new NullPointerException("Status is NULL. It should NOT happen!");
                }
                final int statusCode = statusLine.getStatusCode();
                if (Log.isLoggable("nf_rest", 3)) {
                    Log.d("nf_rest", "Response status: code " + statusCode + ", text " + statusLine.getReasonPhrase() + ", protocol " + statusLine.getProtocolVersion());
                    this.dumpCookies(execute);
                }
                defaultHttpClient = httpClient;
                this.checkForCredentialUpdate(httpClient.getCookieStore().getCookies());
                if (entity != null) {
                    final String convertStreamToString = InputStreamUtil.convertStreamToString(entity.getContent());
                    if (Log.isLoggable("nf_rest", 3)) {
                        Log.d("nf_rest", "Full content: " + convertStreamToString);
                    }
                    entity.consumeContent();
                    defaultHttpClient = httpClient;
                    final boolean checkStatus = this.checkStatus(statusCode, convertStreamToString);
                    continue;
                }
                continue;
            }
            httpClient.getConnectionManager().shutdown();
            return;
        }
    }
    
    protected StringBuilder getBaseCmsBeaconUrl() {
        final StringBuilder sb = new StringBuilder();
        if (this.isSecure()) {
            sb.append("https://");
        }
        else {
            sb.append("http://");
        }
        sb.append(HttpClientFactory.getCmsBeaconApiEndPoint());
        final String commandPath = this.getCommandPath();
        if (commandPath == null) {
            Log.e("nf_rest", "Path is NULL!");
            return null;
        }
        final String trim = commandPath.trim();
        if (!trim.startsWith("/")) {
            sb.append('/');
        }
        sb.append(trim);
        return sb;
    }
    
    protected StringBuilder getBaseCustomerEventBeaconUrl() {
        final StringBuilder sb = new StringBuilder();
        if (this.isSecure()) {
            sb.append("https://");
        }
        else {
            sb.append("http://");
        }
        sb.append(HttpClientFactory.getCustomerEventBeaconApiEndPoint());
        final String commandPath = this.getCommandPath();
        if (commandPath == null) {
            Log.e("nf_rest", "Path is NULL!");
            return null;
        }
        final String trim = commandPath.trim();
        if (!trim.startsWith("/")) {
            sb.append('/');
        }
        sb.append(trim);
        return sb;
    }
    
    protected StringBuilder getBaseWebAPiUrl() {
        final StringBuilder sb = new StringBuilder();
        if (this.isSecure()) {
            sb.append("https://");
        }
        else {
            sb.append("http://");
        }
        sb.append(HttpClientFactory.getWebApiEndPoint());
        final String commandPath = this.getCommandPath();
        if (commandPath == null) {
            Log.e("nf_rest", "Path is NULL!");
            return null;
        }
        final String trim = commandPath.trim();
        if (!trim.startsWith("/")) {
            sb.append('/');
        }
        sb.append(trim);
        return sb;
    }
    
    public abstract String getCommandPath();
    
    public CommonRequestParameters getCommonRequestParameters() {
        return this.commonRequestParameters;
    }
    
    public AuthorizationCredentials getCredentials() {
        return this.credentials;
    }
    
    public Pair<String, String>[] getHeaders() {
        return (Pair<String, String>[])new Pair[0];
    }
    
    protected abstract HttpUriRequest getHttpMethod() throws UnsupportedEncodingException;
    
    public String getOuput() {
        return "json";
    }
    
    public String getRouting() {
        return "reject";
    }
    
    protected StringBuilder getUrl() {
        return this.getBaseWebAPiUrl();
    }
    
    public String getVersion() {
        return "2.0";
    }
    
    public boolean isSecure() {
        return true;
    }
    
    public boolean isUpdated() {
        return this.updated;
    }
    
    protected void verifyNotNull(final Object o, final String s) {
        if (o == null) {
            throw new IllegalArgumentException(s + " can not be null!");
        }
    }
}
