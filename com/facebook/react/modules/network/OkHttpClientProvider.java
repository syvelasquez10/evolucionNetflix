// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import com.facebook.common.logging.FLog;
import java.util.List;
import java.util.ArrayList;
import okhttp3.TlsVersion;
import okhttp3.ConnectionSpec$Builder;
import okhttp3.ConnectionSpec;
import javax.net.ssl.SSLSocketFactory;
import android.os.Build$VERSION;
import okhttp3.CookieJar;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient$Builder;
import okhttp3.OkHttpClient;

public class OkHttpClientProvider
{
    private static OkHttpClient sClient;
    
    private static OkHttpClient createClient() {
        return enableTls12OnPreLollipop(new OkHttpClient$Builder().connectTimeout(0L, TimeUnit.MILLISECONDS).readTimeout(0L, TimeUnit.MILLISECONDS).writeTimeout(0L, TimeUnit.MILLISECONDS).cookieJar((CookieJar)new ReactCookieJarContainer())).build();
    }
    
    public static OkHttpClient$Builder enableTls12OnPreLollipop(final OkHttpClient$Builder okHttpClient$Builder) {
        if (Build$VERSION.SDK_INT < 16 || Build$VERSION.SDK_INT > 19) {
            return okHttpClient$Builder;
        }
        try {
            okHttpClient$Builder.sslSocketFactory((SSLSocketFactory)new TLSSocketFactory());
            final ConnectionSpec build = new ConnectionSpec$Builder(ConnectionSpec.MODERN_TLS).tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_2 }).build();
            final ArrayList<ConnectionSpec> list = new ArrayList<ConnectionSpec>();
            list.add(build);
            list.add(ConnectionSpec.COMPATIBLE_TLS);
            list.add(ConnectionSpec.CLEARTEXT);
            okHttpClient$Builder.connectionSpecs((List)list);
            return okHttpClient$Builder;
        }
        catch (Exception ex) {
            FLog.e("OkHttpClientProvider", "Error while enabling TLS 1.2", ex);
            return okHttpClient$Builder;
        }
    }
    
    public static OkHttpClient getOkHttpClient() {
        if (OkHttpClientProvider.sClient == null) {
            OkHttpClientProvider.sClient = createClient();
        }
        return OkHttpClientProvider.sClient;
    }
    
    public static void replaceOkHttpClient(final OkHttpClient sClient) {
        OkHttpClientProvider.sClient = sClient;
    }
}
