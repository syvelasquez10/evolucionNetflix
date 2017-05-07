// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpUriRequest;
import java.io.IOException;
import org.apache.http.StatusLine;
import org.apache.http.impl.client.DefaultHttpClient;
import com.android.volley.toolbox.InputStreamUtil;
import com.netflix.mediaclient.webapi.HttpClientFactory;
import org.json.JSONException;
import org.apache.http.HttpException;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import com.netflix.mediaclient.Log;
import org.apache.http.cookie.Cookie;
import java.util.List;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;
import com.netflix.mediaclient.webapi.CommonRequestParameters;

public abstract class MdxCeWebApiCommand
{
    private static final String COOKIE_SET_CHECK = "Set-Cookie";
    private static final String CUSTOMEREVENT_ENDPOINT_PROD = "customerevents.netflix.com";
    protected static final String HTTP = "http://";
    protected static final String HTTPS = "https://";
    protected static final int MAX_RE_EXECUTION_TIMES = 5;
    public static final String NETFLIX_ID = "NetflixId";
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
    public static final String SECURE_NETFLIX_ID = "SecureNetflixId";
    protected static final String TAG = "nf_mdxMdxCustomerEventrest";
    protected static final String VERSION_20 = "2.0";
    protected static String mCustomerEventEndPoint;
    protected CommonRequestParameters commonRequestParameters;
    private int count;
    protected AuthorizationCredentials credentials;
    protected String esn;
    protected boolean updated;
    
    static {
        MdxCeWebApiCommand.mCustomerEventEndPoint = "customerevents.netflix.com";
    }
    
    protected MdxCeWebApiCommand(final String s, final AuthorizationCredentials credentials, final CommonRequestParameters commonRequestParameters) {
        if (credentials == null) {
            throw new IllegalArgumentException("Netflix id or secure netflix id is null!");
        }
        this.credentials = credentials;
        this.commonRequestParameters = commonRequestParameters;
    }
    
    private void checkForCredentialUpdate(final List<Cookie> list) {
        if (list.isEmpty()) {
            Log.d("nf_mdxMdxCustomerEventrest", "No cookies in response");
        }
        else {
            for (int i = 0; i < list.size(); ++i) {
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
        if (Log.isLoggable("nf_mdxMdxCustomerEventrest", 3)) {
            int n = 0;
            int n2 = 0;
            final Header[] allHeaders = httpResponse.getAllHeaders();
            if (allHeaders != null && allHeaders.length > 0) {
                Log.d("nf_mdxMdxCustomerEventrest", "We got headers: " + allHeaders.length);
                final int length = allHeaders.length;
                int n3 = 0;
                while (true) {
                    n = n2;
                    if (n3 >= length) {
                        break;
                    }
                    final Header header = allHeaders[n3];
                    Log.d("nf_mdxMdxCustomerEventrest", "" + header);
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
                Log.e("nf_mdxMdxCustomerEventrest", "No headers!!!");
            }
            Log.d("nf_mdxMdxCustomerEventrest", "Cookies set in response: " + n);
            Log.d("nf_mdxMdxCustomerEventrest", "----------------------------------------");
            Log.d("nf_mdxMdxCustomerEventrest", "Response status line: " + httpResponse.getStatusLine());
            final HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                Log.d("nf_mdxMdxCustomerEventrest", "Response content length: " + entity.getContentLength());
            }
        }
    }
    
    private String updateProperty(final Cookie cookie, final String s) {
        final String value = cookie.getValue();
        if (s == null || s.equals(value)) {
            return s;
        }
        this.updated = true;
        return value;
    }
    
    protected boolean checkStatus(final int n, final String s) throws JSONException, HttpException {
        if (n == 410) {
            Log.w("nf_mdxMdxCustomerEventrest", "Received 410: Redirecting... ");
            MdxCeWebApiCommand.mCustomerEventEndPoint = new JSONObject(s).getString("host");
            if (Log.isLoggable("nf_mdxMdxCustomerEventrest", 3)) {
                Log.d("nf_mdxMdxCustomerEventrest", "Response 410: new WebAPI end point received: " + MdxCeWebApiCommand.mCustomerEventEndPoint);
            }
            return true;
        }
        if (n == 200 || n == 202) {
            if (Log.isLoggable("nf_mdxMdxCustomerEventrest", 3)) {
                Log.d("nf_mdxMdxCustomerEventrest", "Response succeed with code " + n);
            }
            return false;
        }
        if (Log.isLoggable("nf_mdxMdxCustomerEventrest", 6)) {
            Log.e("nf_mdxMdxCustomerEventrest", "Response " + n);
        }
        throw new HttpException("Failed with response code " + n);
    }
    
    protected String doExecute() throws IOException, JSONException, HttpException {
        while (true) {
            DefaultHttpClient defaultHttpClient = null;
            DefaultHttpClient httpClient = null;
        Label_0223:
            while (true) {
                try {
                    httpClient = HttpClientFactory.getHttpClient(this.credentials.netflixId, this.credentials.secureNetflixId);
                    final boolean checkStatus = true;
                    if (!checkStatus) {
                        break Label_0223;
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
                final HttpResponse execute = httpClient.execute(this.getHttpMethod());
                final HttpEntity entity = execute.getEntity();
                final StatusLine statusLine = execute.getStatusLine();
                if (statusLine == null) {
                    Log.e("nf_mdxMdxCustomerEventrest", "Status is NULL. It should NOT happen!");
                    throw new NullPointerException("Status is NULL. It should NOT happen!");
                }
                final int statusCode = statusLine.getStatusCode();
                defaultHttpClient = httpClient;
                this.checkForCredentialUpdate(httpClient.getCookieStore().getCookies());
                if (entity != null) {
                    final String convertStreamToString = InputStreamUtil.convertStreamToString(entity.getContent());
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
    
    protected StringBuilder getBaseUrl() {
        final StringBuilder sb = new StringBuilder();
        if (this.isSecure()) {
            sb.append("https://");
        }
        else {
            sb.append("http://");
        }
        sb.append(MdxCeWebApiCommand.mCustomerEventEndPoint);
        final String commandPath = this.getCommandPath();
        if (commandPath == null) {
            Log.e("nf_mdxMdxCustomerEventrest", "Path is NULL!");
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
    
    protected abstract HttpUriRequest getHttpMethod() throws UnsupportedEncodingException;
    
    public String getOuput() {
        return "json";
    }
    
    public String getRouting() {
        return "reject";
    }
    
    protected StringBuilder getUrl() {
        return this.getBaseUrl();
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
