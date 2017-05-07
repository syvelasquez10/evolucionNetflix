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
                if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
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
            if (Log.isLoggable()) {
                Log.d("nf_rest", name + " is not changed");
            }
            return s;
        }
        if (Log.isLoggable()) {
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
            if (Log.isLoggable()) {
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
                if (Log.isLoggable()) {
                    Log.e("nf_rest", "Response " + n);
                }
                throw new HttpException("Failed with response code " + n);
            }
            if (Log.isLoggable()) {
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
        //    27: ifeq            486
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
        //    86: ifnull          217
        //    89: aload           6
        //    91: arraylength    
        //    92: ifle            217
        //    95: iconst_0       
        //    96: istore_1       
        //    97: iload_1        
        //    98: aload           6
        //   100: arraylength    
        //   101: if_icmpge       226
        //   104: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   107: ifeq            164
        //   110: ldc             "nf_rest"
        //   112: new             Ljava/lang/StringBuilder;
        //   115: dup            
        //   116: invokespecial   java/lang/StringBuilder.<init>:()V
        //   119: ldc_w           "Add header "
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: aload           6
        //   127: iload_1        
        //   128: aaload         
        //   129: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   132: checkcast       Ljava/lang/String;
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: ldc_w           " with value: "
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: aload           6
        //   146: iload_1        
        //   147: aaload         
        //   148: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   151: checkcast       Ljava/lang/String;
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   160: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   163: pop            
        //   164: aload           6
        //   166: iload_1        
        //   167: aaload         
        //   168: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   171: ifnull          509
        //   174: aload           6
        //   176: iload_1        
        //   177: aaload         
        //   178: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   181: ifnonnull       187
        //   184: goto            509
        //   187: aload           5
        //   189: aload           6
        //   191: iload_1        
        //   192: aaload         
        //   193: getfield        android/util/Pair.first:Ljava/lang/Object;
        //   196: checkcast       Ljava/lang/String;
        //   199: aload           6
        //   201: iload_1        
        //   202: aaload         
        //   203: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   206: checkcast       Ljava/lang/String;
        //   209: invokeinterface org/apache/http/client/methods/HttpUriRequest.setHeader:(Ljava/lang/String;Ljava/lang/String;)V
        //   214: goto            509
        //   217: ldc             "nf_rest"
        //   219: ldc_w           "No header found"
        //   222: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   225: pop            
        //   226: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   229: ifeq            277
        //   232: ldc             "nf_rest"
        //   234: new             Ljava/lang/StringBuilder;
        //   237: dup            
        //   238: invokespecial   java/lang/StringBuilder.<init>:()V
        //   241: ldc_w           "executing request "
        //   244: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   247: aload           5
        //   249: invokeinterface org/apache/http/client/methods/HttpUriRequest.getURI:()Ljava/net/URI;
        //   254: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   257: ldc_w           ", attempt: "
        //   260: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   263: aload_0        
        //   264: getfield        com/netflix/mediaclient/webapi/WebApiCommand.count:I
        //   267: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   270: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   273: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   276: pop            
        //   277: aload           4
        //   279: aload           5
        //   281: invokevirtual   org/apache/http/impl/client/DefaultHttpClient.execute:(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
        //   284: astore          6
        //   286: aload           6
        //   288: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   293: astore          5
        //   295: aload           6
        //   297: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //   302: astore          7
        //   304: aload           7
        //   306: ifnonnull       329
        //   309: ldc             "nf_rest"
        //   311: ldc_w           "Status is NULL. It should NOT happen!"
        //   314: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   317: pop            
        //   318: new             Ljava/lang/NullPointerException;
        //   321: dup            
        //   322: ldc_w           "Status is NULL. It should NOT happen!"
        //   325: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //   328: athrow         
        //   329: aload           7
        //   331: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //   336: istore_1       
        //   337: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   340: ifeq            407
        //   343: ldc             "nf_rest"
        //   345: new             Ljava/lang/StringBuilder;
        //   348: dup            
        //   349: invokespecial   java/lang/StringBuilder.<init>:()V
        //   352: ldc_w           "Response status: code "
        //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: iload_1        
        //   359: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   362: ldc_w           ", text "
        //   365: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   368: aload           7
        //   370: invokeinterface org/apache/http/StatusLine.getReasonPhrase:()Ljava/lang/String;
        //   375: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   378: ldc_w           ", protocol "
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: aload           7
        //   386: invokeinterface org/apache/http/StatusLine.getProtocolVersion:()Lorg/apache/http/ProtocolVersion;
        //   391: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   394: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   397: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   400: pop            
        //   401: aload_0        
        //   402: aload           6
        //   404: invokespecial   com/netflix/mediaclient/webapi/WebApiCommand.dumpCookies:(Lorg/apache/http/HttpResponse;)V
        //   407: aload_0        
        //   408: aload           4
        //   410: invokevirtual   org/apache/http/impl/client/DefaultHttpClient.getCookieStore:()Lorg/apache/http/client/CookieStore;
        //   413: invokeinterface org/apache/http/client/CookieStore.getCookies:()Ljava/util/List;
        //   418: invokespecial   com/netflix/mediaclient/webapi/WebApiCommand.checkForCredentialUpdate:(Ljava/util/List;)V
        //   421: aload           5
        //   423: ifnull          506
        //   426: aload           5
        //   428: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //   433: invokestatic    com/android/volley/toolbox/InputStreamUtil.convertStreamToString:(Ljava/io/InputStream;)Ljava/lang/String;
        //   436: astore_3       
        //   437: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   440: ifeq            469
        //   443: ldc             "nf_rest"
        //   445: new             Ljava/lang/StringBuilder;
        //   448: dup            
        //   449: invokespecial   java/lang/StringBuilder.<init>:()V
        //   452: ldc_w           "Full content: "
        //   455: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   458: aload_3        
        //   459: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   462: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   465: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   468: pop            
        //   469: aload           5
        //   471: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   476: aload_0        
        //   477: iload_1        
        //   478: aload_3        
        //   479: invokevirtual   com/netflix/mediaclient/webapi/WebApiCommand.checkStatus:(ILjava/lang/String;)Z
        //   482: istore_2       
        //   483: goto            26
        //   486: aload           4
        //   488: invokevirtual   org/apache/http/impl/client/DefaultHttpClient.getConnectionManager:()Lorg/apache/http/conn/ClientConnectionManager;
        //   491: invokeinterface org/apache/http/conn/ClientConnectionManager.shutdown:()V
        //   496: aload_3        
        //   497: areturn        
        //   498: astore_3       
        //   499: aload           5
        //   501: astore          4
        //   503: goto            50
        //   506: goto            483
        //   509: iload_1        
        //   510: iconst_1       
        //   511: iadd           
        //   512: istore_1       
        //   513: goto            97
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  3      22     498    506    Any
        //  30     49     49     50     Any
        //  62     84     49     50     Any
        //  89     95     49     50     Any
        //  97     164    49     50     Any
        //  164    184    49     50     Any
        //  187    214    49     50     Any
        //  217    226    49     50     Any
        //  226    277    49     50     Any
        //  277    304    49     50     Any
        //  309    329    49     50     Any
        //  329    407    49     50     Any
        //  407    421    49     50     Any
        //  426    469    49     50     Any
        //  469    483    49     50     Any
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
