// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

import org.apache.http.client.methods.HttpUriRequest;
import android.util.Pair;
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
        int n = 0;
        int n2 = 0;
        if (Log.isLoggable("nf_rest", 3)) {
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
    
    protected boolean checkStatus(final int n, String string) {
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
    
    protected String doExecute() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload_0        
        //     4: getfield        com/netflix/mediaclient/webapi/WebApiCommand.credentials:Lcom/netflix/mediaclient/webapi/AuthorizationCredentials;
        //     7: getfield        com/netflix/mediaclient/webapi/AuthorizationCredentials.netflixId:Ljava/lang/String;
        //    10: aload_0        
        //    11: getfield        com/netflix/mediaclient/webapi/WebApiCommand.credentials:Lcom/netflix/mediaclient/webapi/AuthorizationCredentials;
        //    14: getfield        com/netflix/mediaclient/webapi/AuthorizationCredentials.secureNetflixId:Ljava/lang/String;
        //    17: invokestatic    com/netflix/mediaclient/webapi/HttpClientFactory.getHttpClient:(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/http/impl/client/DefaultHttpClient;
        //    20: astore          4
        //    22: iconst_1       
        //    23: istore_2       
        //    24: aconst_null    
        //    25: astore_3       
        //    26: iload_2        
        //    27: ifeq            498
        //    30: aload_0        
        //    31: getfield        com/netflix/mediaclient/webapi/WebApiCommand.count:I
        //    34: iconst_5       
        //    35: if_icmple       62
        //    38: new             Lorg/apache/http/HttpException;
        //    41: dup            
        //    42: ldc_w           "Too many retries!"
        //    45: invokespecial   org/apache/http/HttpException.<init>:(Ljava/lang/String;)V
        //    48: athrow         
        //    49: astore_3       
        //    50: aload           4
        //    52: invokevirtual   org/apache/http/impl/client/DefaultHttpClient.getConnectionManager:()Lorg/apache/http/conn/ClientConnectionManager;
        //    55: invokeinterface org/apache/http/conn/ClientConnectionManager.shutdown:()V
        //    60: aload_3        
        //    61: athrow         
        //    62: aload_0        
        //    63: aload_0        
        //    64: getfield        com/netflix/mediaclient/webapi/WebApiCommand.count:I
        //    67: iconst_1       
        //    68: iadd           
        //    69: putfield        com/netflix/mediaclient/webapi/WebApiCommand.count:I
        //    72: aload_0        
        //    73: invokevirtual   com/netflix/mediaclient/webapi/WebApiCommand.getHttpMethod:()Lorg/apache/http/client/methods/HttpUriRequest;
        //    76: astore          5
        //    78: aload_0        
        //    79: invokevirtual   com/netflix/mediaclient/webapi/WebApiCommand.getHeaders:()[Landroid/util/Pair;
        //    82: astore          6
        //    84: aload           6
        //    86: ifnull          220
        //    89: aload           6
        //    91: arraylength    
        //    92: ifle            220
        //    95: iconst_0       
        //    96: istore_1       
        //    97: iload_1        
        //    98: aload           6
        //   100: arraylength    
        //   101: if_icmpge       229
        //   104: ldc             "nf_rest"
        //   106: iconst_3       
        //   107: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   110: ifeq            167
        //   113: ldc             "nf_rest"
        //   115: new             Ljava/lang/StringBuilder;
        //   118: dup            
        //   119: invokespecial   java/lang/StringBuilder.<init>:()V
        //   122: ldc_w           "Add header "
        //   125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   128: aload           6
        //   130: iload_1        
        //   131: aaload         
        //   132: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   135: checkcast       Ljava/lang/String;
        //   138: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   141: ldc_w           " with value: "
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: aload           6
        //   149: iload_1        
        //   150: aaload         
        //   151: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   154: checkcast       Ljava/lang/String;
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   163: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   166: pop            
        //   167: aload           6
        //   169: iload_1        
        //   170: aaload         
        //   171: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   174: ifnull          521
        //   177: aload           6
        //   179: iload_1        
        //   180: aaload         
        //   181: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   184: ifnonnull       190
        //   187: goto            521
        //   190: aload           5
        //   192: aload           6
        //   194: iload_1        
        //   195: aaload         
        //   196: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   199: checkcast       Ljava/lang/String;
        //   202: aload           6
        //   204: iload_1        
        //   205: aaload         
        //   206: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   209: checkcast       Ljava/lang/String;
        //   212: invokeinterface org/apache/http/client/methods/HttpUriRequest.setHeader:(Ljava/lang/String;Ljava/lang/String;)V
        //   217: goto            521
        //   220: ldc             "nf_rest"
        //   222: ldc_w           "No header found"
        //   225: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   228: pop            
        //   229: ldc             "nf_rest"
        //   231: iconst_3       
        //   232: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   235: ifeq            283
        //   238: ldc             "nf_rest"
        //   240: new             Ljava/lang/StringBuilder;
        //   243: dup            
        //   244: invokespecial   java/lang/StringBuilder.<init>:()V
        //   247: ldc_w           "executing request "
        //   250: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   253: aload           5
        //   255: invokeinterface org/apache/http/client/methods/HttpUriRequest.getURI:()Ljava/net/URI;
        //   260: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   263: ldc_w           ", attempt: "
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: aload_0        
        //   270: getfield        com/netflix/mediaclient/webapi/WebApiCommand.count:I
        //   273: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   276: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   279: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   282: pop            
        //   283: aload           4
        //   285: aload           5
        //   287: invokevirtual   org/apache/http/impl/client/DefaultHttpClient.execute:(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
        //   290: astore          6
        //   292: aload           6
        //   294: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   299: astore          5
        //   301: aload           6
        //   303: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //   308: astore          7
        //   310: aload           7
        //   312: ifnonnull       335
        //   315: ldc             "nf_rest"
        //   317: ldc_w           "Status is NULL. It should NOT happen!"
        //   320: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   323: pop            
        //   324: new             Ljava/lang/NullPointerException;
        //   327: dup            
        //   328: ldc_w           "Status is NULL. It should NOT happen!"
        //   331: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //   334: athrow         
        //   335: aload           7
        //   337: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //   342: istore_1       
        //   343: ldc             "nf_rest"
        //   345: iconst_3       
        //   346: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   349: ifeq            416
        //   352: ldc             "nf_rest"
        //   354: new             Ljava/lang/StringBuilder;
        //   357: dup            
        //   358: invokespecial   java/lang/StringBuilder.<init>:()V
        //   361: ldc_w           "Response status: code "
        //   364: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   367: iload_1        
        //   368: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   371: ldc_w           ", text "
        //   374: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   377: aload           7
        //   379: invokeinterface org/apache/http/StatusLine.getReasonPhrase:()Ljava/lang/String;
        //   384: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   387: ldc_w           ", protocol "
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: aload           7
        //   395: invokeinterface org/apache/http/StatusLine.getProtocolVersion:()Lorg/apache/http/ProtocolVersion;
        //   400: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   403: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   406: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   409: pop            
        //   410: aload_0        
        //   411: aload           6
        //   413: invokespecial   com/netflix/mediaclient/webapi/WebApiCommand.dumpCookies:(Lorg/apache/http/HttpResponse;)V
        //   416: aload_0        
        //   417: aload           4
        //   419: invokevirtual   org/apache/http/impl/client/DefaultHttpClient.getCookieStore:()Lorg/apache/http/client/CookieStore;
        //   422: invokeinterface org/apache/http/client/CookieStore.getCookies:()Ljava/util/List;
        //   427: invokespecial   com/netflix/mediaclient/webapi/WebApiCommand.checkForCredentialUpdate:(Ljava/util/List;)V
        //   430: aload           5
        //   432: ifnull          518
        //   435: aload           5
        //   437: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //   442: invokestatic    com/android/volley/toolbox/InputStreamUtil.convertStreamToString:(Ljava/io/InputStream;)Ljava/lang/String;
        //   445: astore_3       
        //   446: ldc             "nf_rest"
        //   448: iconst_3       
        //   449: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   452: ifeq            481
        //   455: ldc             "nf_rest"
        //   457: new             Ljava/lang/StringBuilder;
        //   460: dup            
        //   461: invokespecial   java/lang/StringBuilder.<init>:()V
        //   464: ldc_w           "Full content: "
        //   467: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   470: aload_3        
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   477: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   480: pop            
        //   481: aload           5
        //   483: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   488: aload_0        
        //   489: iload_1        
        //   490: aload_3        
        //   491: invokevirtual   com/netflix/mediaclient/webapi/WebApiCommand.checkStatus:(ILjava/lang/String;)Z
        //   494: istore_2       
        //   495: goto            26
        //   498: aload           4
        //   500: invokevirtual   org/apache/http/impl/client/DefaultHttpClient.getConnectionManager:()Lorg/apache/http/conn/ClientConnectionManager;
        //   503: invokeinterface org/apache/http/conn/ClientConnectionManager.shutdown:()V
        //   508: aload_3        
        //   509: areturn        
        //   510: astore_3       
        //   511: aload           5
        //   513: astore          4
        //   515: goto            50
        //   518: goto            495
        //   521: iload_1        
        //   522: iconst_1       
        //   523: iadd           
        //   524: istore_1       
        //   525: goto            97
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  3      22     510    518    Any
        //  30     49     49     50     Any
        //  62     84     49     50     Any
        //  89     95     49     50     Any
        //  97     167    49     50     Any
        //  167    187    49     50     Any
        //  190    217    49     50     Any
        //  220    229    49     50     Any
        //  229    283    49     50     Any
        //  283    310    49     50     Any
        //  315    335    49     50     Any
        //  335    416    49     50     Any
        //  416    430    49     50     Any
        //  435    481    49     50     Any
        //  481    495    49     50     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
    
    protected abstract HttpUriRequest getHttpMethod();
    
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
