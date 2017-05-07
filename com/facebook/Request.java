// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.text.TextUtils;
import android.os.Handler;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.ParcelFileDescriptor;
import android.graphics.Bitmap;
import java.util.regex.Matcher;
import java.util.HashSet;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import com.facebook.internal.Validate;
import java.util.List;
import java.util.Locale;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import android.net.Uri$Builder;
import android.util.Log;
import com.facebook.internal.Utility;
import com.facebook.internal.Logger;
import com.facebook.internal.ServerProtocol;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import java.util.regex.Pattern;

public class Request
{
    public static final String TAG;
    private static String defaultBatchApplicationId;
    private static volatile String userAgent;
    private static Pattern versionPattern;
    private String batchEntryDependsOn;
    private String batchEntryName;
    private boolean batchEntryOmitResultOnSuccess;
    private Request$Callback callback;
    private GraphObject graphObject;
    private String graphPath;
    private HttpMethod httpMethod;
    private String overriddenURL;
    private Bundle parameters;
    private Session session;
    private boolean skipClientToken;
    private Object tag;
    private String version;
    
    static {
        TAG = Request.class.getSimpleName();
        Request.versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    }
    
    public Request() {
        this(null, null, null, null, null);
    }
    
    public Request(final Session session, final String s, final Bundle bundle, final HttpMethod httpMethod, final Request$Callback request$Callback) {
        this(session, s, bundle, httpMethod, request$Callback, null);
    }
    
    public Request(final Session session, final String graphPath, final Bundle bundle, final HttpMethod httpMethod, final Request$Callback callback, final String version) {
        this.batchEntryOmitResultOnSuccess = true;
        this.skipClientToken = false;
        this.session = session;
        this.graphPath = graphPath;
        this.callback = callback;
        this.version = version;
        this.setHttpMethod(httpMethod);
        if (bundle != null) {
            this.parameters = new Bundle(bundle);
        }
        else {
            this.parameters = new Bundle();
        }
        if (this.version == null) {
            this.version = ServerProtocol.getAPIVersion();
        }
    }
    
    private void addCommonParameters() {
        if (this.session != null) {
            if (!this.session.isOpened()) {
                throw new FacebookException("Session provided to a Request in un-opened state.");
            }
            if (!this.parameters.containsKey("access_token")) {
                final String accessToken = this.session.getAccessToken();
                Logger.registerAccessToken(accessToken);
                this.parameters.putString("access_token", accessToken);
            }
        }
        else if (!this.skipClientToken && !this.parameters.containsKey("access_token")) {
            final String applicationId = Settings.getApplicationId();
            final String clientToken = Settings.getClientToken();
            if (!Utility.isNullOrEmpty(applicationId) && !Utility.isNullOrEmpty(clientToken)) {
                this.parameters.putString("access_token", applicationId + "|" + clientToken);
            }
            else {
                Log.d(Request.TAG, "Warning: Sessionless Request needs token but missing either application ID or client token.");
            }
        }
        this.parameters.putString("sdk", "android");
        this.parameters.putString("format", "json");
    }
    
    private String appendParametersToBaseUrl(String value) {
        final Uri$Builder encodedPath = new Uri$Builder().encodedPath(value);
        for (final String s : this.parameters.keySet()) {
            if ((value = (String)this.parameters.get(s)) == null) {
                value = "";
            }
            if (isSupportedParameterType(value)) {
                encodedPath.appendQueryParameter(s, parameterToString(value).toString());
            }
            else {
                if (this.httpMethod == HttpMethod.GET) {
                    throw new IllegalArgumentException(String.format("Unsupported parameter type for GET request: %s", value.getClass().getSimpleName()));
                }
                continue;
            }
        }
        return encodedPath.toString();
    }
    
    static HttpURLConnection createConnection(final URL url) {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", getUserAgent());
        httpURLConnection.setRequestProperty("Content-Type", getMimeContentType());
        httpURLConnection.setRequestProperty("Accept-Language", Locale.getDefault().toString());
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }
    
    public static Response executeAndWait(final Request request) {
        final List<Response> executeBatchAndWait = executeBatchAndWait(request);
        if (executeBatchAndWait == null || executeBatchAndWait.size() != 1) {
            throw new FacebookException("invalid state: expected a single response");
        }
        return executeBatchAndWait.get(0);
    }
    
    public static List<Response> executeBatchAndWait(final RequestBatch requestBatch) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)requestBatch, "requests");
        try {
            return executeConnectionAndWait(toHttpConnection(requestBatch), requestBatch);
        }
        catch (Exception ex) {
            final List<Response> constructErrorResponses = Response.constructErrorResponses(requestBatch.getRequests(), null, new FacebookException(ex));
            runCallbacks(requestBatch, constructErrorResponses);
            return constructErrorResponses;
        }
    }
    
    public static List<Response> executeBatchAndWait(final Collection<Request> collection) {
        return executeBatchAndWait(new RequestBatch(collection));
    }
    
    public static List<Response> executeBatchAndWait(final Request... array) {
        Validate.notNull(array, "requests");
        return executeBatchAndWait(Arrays.asList(array));
    }
    
    public static RequestAsyncTask executeBatchAsync(final RequestBatch requestBatch) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)requestBatch, "requests");
        final RequestAsyncTask requestAsyncTask = new RequestAsyncTask(requestBatch);
        requestAsyncTask.executeOnSettingsExecutor();
        return requestAsyncTask;
    }
    
    public static RequestAsyncTask executeBatchAsync(final Collection<Request> collection) {
        return executeBatchAsync(new RequestBatch(collection));
    }
    
    public static RequestAsyncTask executeBatchAsync(final Request... array) {
        Validate.notNull(array, "requests");
        return executeBatchAsync(Arrays.asList(array));
    }
    
    public static List<Response> executeConnectionAndWait(final HttpURLConnection httpURLConnection, final RequestBatch requestBatch) {
        final List<Response> fromHttpConnection = Response.fromHttpConnection(httpURLConnection, requestBatch);
        Utility.disconnectQuietly(httpURLConnection);
        final int size = requestBatch.size();
        if (size != fromHttpConnection.size()) {
            throw new FacebookException(String.format("Received %d responses while expecting %d", fromHttpConnection.size(), size));
        }
        runCallbacks(requestBatch, fromHttpConnection);
        final HashSet<Session> set = new HashSet<Session>();
        for (final Request request : requestBatch) {
            if (request.session != null) {
                set.add(request.session);
            }
        }
        final Iterator<Session> iterator2 = set.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().extendAccessTokenIfNeeded();
        }
        return fromHttpConnection;
    }
    
    private static String getBatchAppId(final RequestBatch requestBatch) {
        if (!Utility.isNullOrEmpty(requestBatch.getBatchApplicationId())) {
            return requestBatch.getBatchApplicationId();
        }
        final Iterator<Request> iterator = requestBatch.iterator();
        while (iterator.hasNext()) {
            final Session session = iterator.next().session;
            if (session != null) {
                return session.getApplicationId();
            }
        }
        return Request.defaultBatchApplicationId;
    }
    
    private String getGraphPathWithVersion() {
        if (Request.versionPattern.matcher(this.graphPath).matches()) {
            return this.graphPath;
        }
        return String.format("%s/%s", this.version, this.graphPath);
    }
    
    private static String getMimeContentType() {
        return String.format("multipart/form-data; boundary=%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
    }
    
    private static String getUserAgent() {
        if (Request.userAgent == null) {
            Request.userAgent = String.format("%s.%s", "FBAndroidSDK", "3.21.1");
        }
        return Request.userAgent;
    }
    
    private static boolean hasOnProgressCallbacks(final RequestBatch requestBatch) {
        final Iterator<RequestBatch$Callback> iterator = requestBatch.getCallbacks().iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof RequestBatch$OnProgressCallback) {
                return true;
            }
        }
        final Iterator<Request> iterator2 = requestBatch.iterator();
        while (iterator2.hasNext()) {
            if (iterator2.next().getCallback() instanceof Request$OnProgressCallback) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isMeRequest(String group) {
        final Matcher matcher = Request.versionPattern.matcher(group);
        if (matcher.matches()) {
            group = matcher.group(1);
        }
        return group.startsWith("me/") || group.startsWith("/me/");
    }
    
    private static boolean isSupportedAttachmentType(final Object o) {
        return o instanceof Bitmap || o instanceof byte[] || o instanceof ParcelFileDescriptor || o instanceof Request$ParcelFileDescriptorWithMimeType;
    }
    
    private static boolean isSupportedParameterType(final Object o) {
        return o instanceof String || o instanceof Boolean || o instanceof Number || o instanceof Date;
    }
    
    public static Request newGraphPathRequest(final Session session, final String s, final Request$Callback request$Callback) {
        return new Request(session, s, null, null, request$Callback);
    }
    
    public static Request newMeRequest(final Session session, final Request$GraphUserCallback request$GraphUserCallback) {
        return new Request(session, "me", null, null, new Request$1(request$GraphUserCallback));
    }
    
    public static Request newPostRequest(final Session session, final String s, final GraphObject graphObject, final Request$Callback request$Callback) {
        final Request request = new Request(session, s, null, HttpMethod.POST, request$Callback);
        request.setGraphObject(graphObject);
        return request;
    }
    
    private static String parameterToString(final Object o) {
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof Boolean || o instanceof Number) {
            return o.toString();
        }
        if (o instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(o);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }
    
    private static void processGraphObject(final GraphObject graphObject, final String s, final Request$KeyValueSerializer request$KeyValueSerializer) {
        boolean b;
        if (isMeRequest(s)) {
            final int index = s.indexOf(":");
            final int index2 = s.indexOf("?");
            if (index > 3 && (index2 == -1 || index < index2)) {
                b = true;
            }
            else {
                b = false;
            }
        }
        else {
            b = false;
        }
        for (final Map.Entry<String, Object> entry : graphObject.asMap().entrySet()) {
            processGraphObjectProperty(entry.getKey(), entry.getValue(), request$KeyValueSerializer, b && entry.getKey().equalsIgnoreCase("image"));
        }
    }
    
    private static void processGraphObjectProperty(final String s, Object o, final Request$KeyValueSerializer request$KeyValueSerializer, final boolean b) {
        Class<?> clazz = o.getClass();
        if (GraphObject.class.isAssignableFrom(clazz)) {
            o = ((GraphObject)o).getInnerJSONObject();
            clazz = o.getClass();
        }
        else if (GraphObjectList.class.isAssignableFrom(clazz)) {
            o = ((GraphObjectList)o).getInnerJSONArray();
            clazz = o.getClass();
        }
        if (JSONObject.class.isAssignableFrom(clazz)) {
            final JSONObject jsonObject = (JSONObject)o;
            if (b) {
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    processGraphObjectProperty(String.format("%s[%s]", s, s2), jsonObject.opt(s2), request$KeyValueSerializer, b);
                }
            }
            else if (jsonObject.has("id")) {
                processGraphObjectProperty(s, jsonObject.optString("id"), request$KeyValueSerializer, b);
            }
            else {
                if (jsonObject.has("url")) {
                    processGraphObjectProperty(s, jsonObject.optString("url"), request$KeyValueSerializer, b);
                    return;
                }
                if (jsonObject.has("fbsdk:create_object")) {
                    processGraphObjectProperty(s, jsonObject.toString(), request$KeyValueSerializer, b);
                }
            }
        }
        else if (JSONArray.class.isAssignableFrom(clazz)) {
            final JSONArray jsonArray = (JSONArray)o;
            for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                processGraphObjectProperty(String.format("%s[%d]", s, i), jsonArray.opt(i), request$KeyValueSerializer, b);
            }
        }
        else {
            if (String.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz)) {
                request$KeyValueSerializer.writeString(s, o.toString());
                return;
            }
            if (Date.class.isAssignableFrom(clazz)) {
                request$KeyValueSerializer.writeString(s, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date)o));
            }
        }
    }
    
    private static void processRequest(final RequestBatch requestBatch, final Logger logger, final int n, final URL url, final OutputStream outputStream) {
        final Request$Serializer request$Serializer = new Request$Serializer(outputStream, logger);
        if (n == 1) {
            final Request value = requestBatch.get(0);
            final HashMap<String, Request$Attachment> hashMap = new HashMap<String, Request$Attachment>();
            for (final String s : value.parameters.keySet()) {
                final Object value2 = value.parameters.get(s);
                if (isSupportedAttachmentType(value2)) {
                    hashMap.put(s, new Request$Attachment(value, value2));
                }
            }
            if (logger != null) {
                logger.append("  Parameters:\n");
            }
            serializeParameters(value.parameters, request$Serializer, value);
            if (logger != null) {
                logger.append("  Attachments:\n");
            }
            serializeAttachments(hashMap, request$Serializer);
            if (value.graphObject != null) {
                processGraphObject(value.graphObject, url.getPath(), request$Serializer);
            }
            return;
        }
        final String batchAppId = getBatchAppId(requestBatch);
        if (Utility.isNullOrEmpty(batchAppId)) {
            throw new FacebookException("At least one request in a batch must have an open Session, or a default app ID must be specified.");
        }
        request$Serializer.writeString("batch_app_id", batchAppId);
        final HashMap<String, Request$Attachment> hashMap2 = new HashMap<String, Request$Attachment>();
        serializeRequestsAsJSON(request$Serializer, requestBatch, hashMap2);
        if (logger != null) {
            logger.append("  Attachments:\n");
        }
        serializeAttachments(hashMap2, request$Serializer);
    }
    
    static void runCallbacks(final RequestBatch requestBatch, final List<Response> list) {
        final int size = requestBatch.size();
        final ArrayList<Pair> list2 = new ArrayList<Pair>();
        for (int i = 0; i < size; ++i) {
            final Request value = requestBatch.get(i);
            if (value.callback != null) {
                list2.add(new Pair((Object)value.callback, (Object)list.get(i)));
            }
        }
        if (list2.size() > 0) {
            final Request$4 request$4 = new Request$4(list2, requestBatch);
            final Handler callbackHandler = requestBatch.getCallbackHandler();
            if (callbackHandler != null) {
                callbackHandler.post((Runnable)request$4);
                return;
            }
            request$4.run();
        }
    }
    
    private static void serializeAttachments(final Map<String, Request$Attachment> map, final Request$Serializer request$Serializer) {
        for (final String s : map.keySet()) {
            final Request$Attachment request$Attachment = map.get(s);
            if (isSupportedAttachmentType(request$Attachment.getValue())) {
                request$Serializer.writeObject(s, request$Attachment.getValue(), request$Attachment.getRequest());
            }
        }
    }
    
    private static void serializeParameters(final Bundle bundle, final Request$Serializer request$Serializer, final Request request) {
        for (final String s : bundle.keySet()) {
            final Object value = bundle.get(s);
            if (isSupportedParameterType(value)) {
                request$Serializer.writeObject(s, value, request);
            }
        }
    }
    
    private static void serializeRequestsAsJSON(final Request$Serializer request$Serializer, final Collection<Request> collection, final Map<String, Request$Attachment> map) {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<Request> iterator = collection.iterator();
        while (iterator.hasNext()) {
            iterator.next().serializeToBatch(jsonArray, map);
        }
        request$Serializer.writeRequestsAsJson("batch", jsonArray, collection);
    }
    
    private void serializeToBatch(final JSONArray jsonArray, final Map<String, Request$Attachment> map) {
        final JSONObject jsonObject = new JSONObject();
        if (this.batchEntryName != null) {
            jsonObject.put("name", (Object)this.batchEntryName);
            jsonObject.put("omit_response_on_success", this.batchEntryOmitResultOnSuccess);
        }
        if (this.batchEntryDependsOn != null) {
            jsonObject.put("depends_on", (Object)this.batchEntryDependsOn);
        }
        final String urlForBatchedRequest = this.getUrlForBatchedRequest();
        jsonObject.put("relative_url", (Object)urlForBatchedRequest);
        jsonObject.put("method", (Object)this.httpMethod);
        if (this.session != null) {
            Logger.registerAccessToken(this.session.getAccessToken());
        }
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.parameters.keySet().iterator();
        while (iterator.hasNext()) {
            final Object value = this.parameters.get((String)iterator.next());
            if (isSupportedAttachmentType(value)) {
                final String format = String.format("%s%d", "file", map.size());
                list.add(format);
                map.put(format, new Request$Attachment(this, value));
            }
        }
        if (!list.isEmpty()) {
            jsonObject.put("attached_files", (Object)TextUtils.join((CharSequence)",", (Iterable)list));
        }
        if (this.graphObject != null) {
            final ArrayList list2 = new ArrayList();
            processGraphObject(this.graphObject, urlForBatchedRequest, new Request$5(this, list2));
            jsonObject.put("body", (Object)TextUtils.join((CharSequence)"&", (Iterable)list2));
        }
        jsonArray.put((Object)jsonObject);
    }
    
    static final void serializeToUrlConnection(final RequestBatch p0, final HttpURLConnection p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/facebook/internal/Logger;
        //     3: dup            
        //     4: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //     7: ldc_w           "Request"
        //    10: invokespecial   com/facebook/internal/Logger.<init>:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;)V
        //    13: astore          5
        //    15: aload_0        
        //    16: invokevirtual   com/facebook/RequestBatch.size:()I
        //    19: istore_3       
        //    20: iload_3        
        //    21: iconst_1       
        //    22: if_icmpne       154
        //    25: aload_0        
        //    26: iconst_0       
        //    27: invokevirtual   com/facebook/RequestBatch.get:(I)Lcom/facebook/Request;
        //    30: getfield        com/facebook/Request.httpMethod:Lcom/facebook/HttpMethod;
        //    33: astore          4
        //    35: aload_1        
        //    36: aload           4
        //    38: invokevirtual   com/facebook/HttpMethod.name:()Ljava/lang/String;
        //    41: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    44: aload_1        
        //    45: invokevirtual   java/net/HttpURLConnection.getURL:()Ljava/net/URL;
        //    48: astore          6
        //    50: aload           5
        //    52: ldc_w           "Request:\n"
        //    55: invokevirtual   com/facebook/internal/Logger.append:(Ljava/lang/String;)V
        //    58: aload           5
        //    60: ldc_w           "Id"
        //    63: aload_0        
        //    64: invokevirtual   com/facebook/RequestBatch.getId:()Ljava/lang/String;
        //    67: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //    70: aload           5
        //    72: ldc_w           "URL"
        //    75: aload           6
        //    77: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //    80: aload           5
        //    82: ldc_w           "Method"
        //    85: aload_1        
        //    86: invokevirtual   java/net/HttpURLConnection.getRequestMethod:()Ljava/lang/String;
        //    89: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //    92: aload           5
        //    94: ldc             "User-Agent"
        //    96: aload_1        
        //    97: ldc             "User-Agent"
        //    99: invokevirtual   java/net/HttpURLConnection.getRequestProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   102: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   105: aload           5
        //   107: ldc             "Content-Type"
        //   109: aload_1        
        //   110: ldc             "Content-Type"
        //   112: invokevirtual   java/net/HttpURLConnection.getRequestProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   115: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   118: aload_1        
        //   119: aload_0        
        //   120: invokevirtual   com/facebook/RequestBatch.getTimeout:()I
        //   123: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   126: aload_1        
        //   127: aload_0        
        //   128: invokevirtual   com/facebook/RequestBatch.getTimeout:()I
        //   131: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   134: aload           4
        //   136: getstatic       com/facebook/HttpMethod.POST:Lcom/facebook/HttpMethod;
        //   139: if_acmpne       162
        //   142: iconst_1       
        //   143: istore_2       
        //   144: iload_2        
        //   145: ifne            167
        //   148: aload           5
        //   150: invokevirtual   com/facebook/internal/Logger.log:()V
        //   153: return         
        //   154: getstatic       com/facebook/HttpMethod.POST:Lcom/facebook/HttpMethod;
        //   157: astore          4
        //   159: goto            35
        //   162: iconst_0       
        //   163: istore_2       
        //   164: goto            144
        //   167: aload_1        
        //   168: iconst_1       
        //   169: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   172: aload_0        
        //   173: invokestatic    com/facebook/Request.hasOnProgressCallbacks:(Lcom/facebook/RequestBatch;)Z
        //   176: ifeq            263
        //   179: new             Lcom/facebook/ProgressNoopOutputStream;
        //   182: dup            
        //   183: aload_0        
        //   184: invokevirtual   com/facebook/RequestBatch.getCallbackHandler:()Landroid/os/Handler;
        //   187: invokespecial   com/facebook/ProgressNoopOutputStream.<init>:(Landroid/os/Handler;)V
        //   190: astore          4
        //   192: aload_0        
        //   193: aconst_null    
        //   194: iload_3        
        //   195: aload           6
        //   197: aload           4
        //   199: invokestatic    com/facebook/Request.processRequest:(Lcom/facebook/RequestBatch;Lcom/facebook/internal/Logger;ILjava/net/URL;Ljava/io/OutputStream;)V
        //   202: aload           4
        //   204: invokevirtual   com/facebook/ProgressNoopOutputStream.getMaxProgress:()I
        //   207: istore_2       
        //   208: aload           4
        //   210: invokevirtual   com/facebook/ProgressNoopOutputStream.getProgressMap:()Ljava/util/Map;
        //   213: astore          4
        //   215: new             Lcom/facebook/ProgressOutputStream;
        //   218: dup            
        //   219: new             Ljava/io/BufferedOutputStream;
        //   222: dup            
        //   223: aload_1        
        //   224: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   227: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   230: aload_0        
        //   231: aload           4
        //   233: iload_2        
        //   234: i2l            
        //   235: invokespecial   com/facebook/ProgressOutputStream.<init>:(Ljava/io/OutputStream;Lcom/facebook/RequestBatch;Ljava/util/Map;J)V
        //   238: astore_1       
        //   239: aload_0        
        //   240: aload           5
        //   242: iload_3        
        //   243: aload           6
        //   245: aload_1        
        //   246: invokestatic    com/facebook/Request.processRequest:(Lcom/facebook/RequestBatch;Lcom/facebook/internal/Logger;ILjava/net/URL;Ljava/io/OutputStream;)V
        //   249: aload_1        
        //   250: ifnull          257
        //   253: aload_1        
        //   254: invokevirtual   java/io/OutputStream.close:()V
        //   257: aload           5
        //   259: invokevirtual   com/facebook/internal/Logger.log:()V
        //   262: return         
        //   263: new             Ljava/io/BufferedOutputStream;
        //   266: dup            
        //   267: aload_1        
        //   268: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   271: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   274: astore_1       
        //   275: goto            239
        //   278: astore_0       
        //   279: aconst_null    
        //   280: astore_1       
        //   281: aload_1        
        //   282: ifnull          289
        //   285: aload_1        
        //   286: invokevirtual   java/io/OutputStream.close:()V
        //   289: aload_0        
        //   290: athrow         
        //   291: astore_0       
        //   292: goto            281
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  172    239    278    281    Any
        //  239    249    291    295    Any
        //  263    275    278    281    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0239:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public static HttpURLConnection toHttpConnection(final RequestBatch p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/facebook/RequestBatch.size:()I
        //     4: iconst_1       
        //     5: if_icmpne       36
        //     8: new             Ljava/net/URL;
        //    11: dup            
        //    12: aload_0        
        //    13: iconst_0       
        //    14: invokevirtual   com/facebook/RequestBatch.get:(I)Lcom/facebook/Request;
        //    17: invokevirtual   com/facebook/Request.getUrlForSingleRequest:()Ljava/lang/String;
        //    20: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    23: astore_1       
        //    24: aload_1        
        //    25: invokestatic    com/facebook/Request.createConnection:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //    28: astore_1       
        //    29: aload_0        
        //    30: aload_1        
        //    31: invokestatic    com/facebook/Request.serializeToUrlConnection:(Lcom/facebook/RequestBatch;Ljava/net/HttpURLConnection;)V
        //    34: aload_1        
        //    35: areturn        
        //    36: new             Ljava/net/URL;
        //    39: dup            
        //    40: invokestatic    com/facebook/internal/ServerProtocol.getGraphUrlBase:()Ljava/lang/String;
        //    43: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    46: astore_1       
        //    47: goto            24
        //    50: astore_0       
        //    51: new             Lcom/facebook/FacebookException;
        //    54: dup            
        //    55: ldc_w           "could not construct URL for request"
        //    58: aload_0        
        //    59: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    62: athrow         
        //    63: astore_0       
        //    64: new             Lcom/facebook/FacebookException;
        //    67: dup            
        //    68: ldc_w           "could not construct request body"
        //    71: aload_0        
        //    72: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    75: athrow         
        //    76: astore_0       
        //    77: new             Lcom/facebook/FacebookException;
        //    80: dup            
        //    81: ldc_w           "could not construct request body"
        //    84: aload_0        
        //    85: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    88: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      24     50     63     Ljava/net/MalformedURLException;
        //  24     34     63     76     Ljava/io/IOException;
        //  24     34     76     89     Lorg/json/JSONException;
        //  36     47     50     63     Ljava/net/MalformedURLException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public final Response executeAndWait() {
        return executeAndWait(this);
    }
    
    public final Request$Callback getCallback() {
        return this.callback;
    }
    
    public final GraphObject getGraphObject() {
        return this.graphObject;
    }
    
    public final HttpMethod getHttpMethod() {
        return this.httpMethod;
    }
    
    public final Bundle getParameters() {
        return this.parameters;
    }
    
    public final Session getSession() {
        return this.session;
    }
    
    public final Object getTag() {
        return this.tag;
    }
    
    final String getUrlForBatchedRequest() {
        if (this.overriddenURL != null) {
            throw new FacebookException("Can't override URL for a batch request");
        }
        final String graphPathWithVersion = this.getGraphPathWithVersion();
        this.addCommonParameters();
        return this.appendParametersToBaseUrl(graphPathWithVersion);
    }
    
    final String getUrlForSingleRequest() {
        if (this.overriddenURL != null) {
            return this.overriddenURL.toString();
        }
        String s;
        if (this.getHttpMethod() == HttpMethod.POST && this.graphPath != null && this.graphPath.endsWith("/videos")) {
            s = ServerProtocol.getGraphVideoUrlBase();
        }
        else {
            s = ServerProtocol.getGraphUrlBase();
        }
        final String format = String.format("%s/%s", s, this.getGraphPathWithVersion());
        this.addCommonParameters();
        return this.appendParametersToBaseUrl(format);
    }
    
    public final void setCallback(final Request$Callback callback) {
        this.callback = callback;
    }
    
    public final void setGraphObject(final GraphObject graphObject) {
        this.graphObject = graphObject;
    }
    
    public final void setHttpMethod(HttpMethod get) {
        if (this.overriddenURL != null && get != HttpMethod.GET) {
            throw new FacebookException("Can't change HTTP method on request with overridden URL.");
        }
        if (get == null) {
            get = HttpMethod.GET;
        }
        this.httpMethod = get;
    }
    
    public final void setParameters(final Bundle parameters) {
        this.parameters = parameters;
    }
    
    public final void setSkipClientToken(final boolean skipClientToken) {
        this.skipClientToken = skipClientToken;
    }
    
    public final void setTag(final Object tag) {
        this.tag = tag;
    }
    
    @Override
    public String toString() {
        return "{Request: " + " session: " + this.session + ", graphPath: " + this.graphPath + ", graphObject: " + this.graphObject + ", httpMethod: " + this.httpMethod + ", parameters: " + this.parameters + "}";
    }
}
