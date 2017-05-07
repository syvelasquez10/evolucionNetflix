// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.FileInputStream;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import java.io.IOException;
import org.apache.http.conn.ConnectTimeoutException;
import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpRequestBase;
import android.content.Context;
import org.apache.http.impl.client.DefaultHttpClient;

public class k
{
    private final DefaultHttpClient a;
    private final String b;
    private Context c;
    
    public k() {
        this.b = "";
        this.a = a();
    }
    
    public k(final String b, final Context c) {
        this.b = b;
        this.a = a();
        this.c = c;
    }
    
    private String a(final HttpRequestBase httpRequestBase) {
        while (true) {
            while (true) {
                Label_0421: {
                    try {
                        this.a.getConnectionManager().closeExpiredConnections();
                        final HttpResponse execute = this.a.execute((HttpUriRequest)httpRequestBase);
                        final int statusCode = execute.getStatusLine().getStatusCode();
                        switch (statusCode) {
                            case 201: {
                                k.class.getCanonicalName();
                                new StringBuilder("executeHttpRequest error=").append(statusCode).append(" - ").append(execute.getStatusLine().toString());
                                k.class.getCanonicalName();
                                new StringBuilder("executeHttpRequest error=").append(statusCode).append(" - ").append(execute.getStatusLine().toString());
                                execute.getEntity().consumeContent();
                                return "";
                            }
                            case 200: {
                                return EntityUtils.toString(execute.getEntity(), "UTF-8");
                            }
                            case 202: {
                                final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                                hashMap.put("success", 1);
                                return new JSONObject((Map)hashMap).toString();
                            }
                            default: {
                                break Label_0421;
                            }
                        }
                    }
                    catch (ConnectTimeoutException ex3) {
                        k.class.getClass().getCanonicalName();
                        throw new ad("Error: connection timed out, please try again later.", ad$a.b);
                    }
                    catch (IOException ex) {
                        httpRequestBase.abort();
                        for (int i = 0; i < httpRequestBase.getAllHeaders().length; ++i) {
                            final Header header = httpRequestBase.getAllHeaders()[i];
                            new StringBuilder().append(header.getName()).append(": ").append(header.getValue());
                        }
                        k.class.getClass().getCanonicalName();
                        new StringBuilder("Error: Aborting HTTP Request: ").append(ex.getLocalizedMessage());
                        if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("no route to host")) {
                            throw new ad("Error: no internet connection", ad$a.a);
                        }
                        if (ex.getStackTrace()[0].toString().contains("java.net.InetAddress.lookupHostByName")) {
                            k.class.getClass().getCanonicalName();
                            throw new ad("Error: no internet connection", ad$a.a);
                        }
                        throw ex;
                    }
                    catch (Exception ex2) {
                        k.class.getClass().getCanonicalName();
                        new StringBuilder("Neither ConnectTimeoutException nor IOException.  Instead: ").append(ex2.getClass().getName());
                        ex2.printStackTrace();
                        return "";
                    }
                }
                continue;
            }
        }
    }
    
    private static DefaultHttpClient a() {
        final SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
        final X509HostnameVerifier browser_COMPATIBLE_HOSTNAME_VERIFIER = SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        final SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
        socketFactory.setHostnameVerifier((X509HostnameVerifier)browser_COMPATIBLE_HOSTNAME_VERIFIER);
        schemeRegistry.register(new Scheme("https", (SocketFactory)socketFactory, 443));
        final BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled((HttpParams)basicHttpParams, false);
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 20000);
        HttpConnectionParams.setSocketBufferSize((HttpParams)basicHttpParams, 8192);
        HttpClientParams.setRedirecting((HttpParams)basicHttpParams, false);
        return new DefaultHttpClient((ClientConnectionManager)new ThreadSafeClientConnManager((HttpParams)basicHttpParams, schemeRegistry), (HttpParams)basicHttpParams);
    }
    
    public final String a(final String s, final JSONObject jsonObject) {
        k.class.getCanonicalName();
        new StringBuilder("*** executing HTTP Post with JSON : ").append(s);
        final HttpPost httpPost = new HttpPost(s);
        httpPost.addHeader("User-Agent", this.b);
        httpPost.addHeader("Content-Type", "application/json");
        try {
            httpPost.setEntity((HttpEntity)new ByteArrayEntity(jsonObject.toString().getBytes("UTF8")));
            new StringBuilder("JSON Object for request: ").append(jsonObject.toString());
            new StringBuilder("httpPost Entity is: ").append(httpPost.getEntity().getContent().toString());
            return this.a((HttpRequestBase)httpPost);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Error encoding http params");
        }
    }
    
    public final String a(String s, final JSONObject jsonObject, final String[] array, final String s2) {
        String a = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        Label_0508: {
            while (true) {
                int n = 0;
                k.class.getCanonicalName();
                new StringBuilder("*** executing HTTP Post with File : ").append(s);
                new String();
                final byte[] array2 = new byte[30];
                new Random().nextBytes(array2);
                a = ag.a(array2);
                new StringBuilder("boundary = ").append(a);
                s = (String)new HttpPost(s);
                ((HttpPost)s).addHeader("User-Agent", this.b);
                ((HttpPost)s).addHeader("Content-Type", "multipart/form-data; boundary=" + a + "; charset=\"utf-8\"");
                ((HttpPost)s).addHeader("Accept", "text/plain, application/json");
                ((HttpPost)s).addHeader("Content-Disposition", "form-data");
                while (true) {
                    FileInputStream fileInputStream = null;
                    Label_0457: {
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            final StringBuilder sb = new StringBuilder();
                            sb.append("--" + a + "\n");
                            sb.append("Content-Disposition: form-data; name=\"" + s2 + "json\";\n");
                            sb.append("Content-type: application/json\n\n");
                            sb.append(jsonObject.toString() + "\n");
                            byteArrayOutputStream.write(sb.toString().getBytes("UTF-8"));
                            if (n >= array.length) {
                                break Label_0508;
                            }
                            final File file = new File(array[n]);
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("--" + a + "\n");
                            sb2.append("Content-Disposition: form-data; name=\"" + s2 + Integer.toString(n) + "\"; filename=\"" + file.getName() + "\";\n");
                            sb2.append("Content-type: application/octet-stream\n\n");
                            byteArrayOutputStream.write(sb2.toString().getBytes("UTF-8"));
                            fileInputStream = new FileInputStream(file);
                            final byte[] array3 = new byte[8192];
                            while (true) {
                                final int read = fileInputStream.read(array3);
                                if (read < 0) {
                                    break Label_0457;
                                }
                                byteArrayOutputStream.write(array3, 0, read);
                            }
                        }
                        catch (Exception ex) {}
                        break;
                    }
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append("\n");
                    byteArrayOutputStream.write(sb3.toString().getBytes("UTF-8"));
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    ++n;
                    continue;
                }
            }
            return this.a((HttpRequestBase)s);
        }
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("--" + a + "--");
        byteArrayOutputStream.write(sb4.toString().getBytes("UTF-8"));
        ((HttpPost)s).setEntity((HttpEntity)new ByteArrayEntity(byteArrayOutputStream.toByteArray()));
        return this.a((HttpRequestBase)s);
    }
}
