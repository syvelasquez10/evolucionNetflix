// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import com.facebook.model.GraphMultiResult;
import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import android.text.TextUtils;
import org.json.JSONException;
import android.util.Pair;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import java.text.SimpleDateFormat;
import android.os.Parcelable;
import com.facebook.model.GraphPlace;
import java.util.Locale;
import com.facebook.model.GraphUser;
import java.util.Date;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import java.io.File;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Handler;
import java.util.HashSet;
import java.net.URLConnection;
import com.facebook.internal.Utility;
import java.util.Arrays;
import java.util.Collection;
import com.facebook.internal.Validate;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;
import android.net.Uri$Builder;
import com.facebook.internal.Logger;
import java.util.List;
import java.net.URL;
import android.os.Bundle;
import com.facebook.model.GraphObject;

public class Request
{
    private static final String ACCESS_TOKEN_PARAM = "access_token";
    private static final String ATTACHED_FILES_PARAM = "attached_files";
    private static final String ATTACHMENT_FILENAME_PREFIX = "file";
    private static final String BATCH_APP_ID_PARAM = "batch_app_id";
    private static final String BATCH_BODY_PARAM = "body";
    private static final String BATCH_ENTRY_DEPENDS_ON_PARAM = "depends_on";
    private static final String BATCH_ENTRY_NAME_PARAM = "name";
    private static final String BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM = "omit_response_on_success";
    private static final String BATCH_METHOD_PARAM = "method";
    private static final String BATCH_PARAM = "batch";
    private static final String BATCH_RELATIVE_URL_PARAM = "relative_url";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String FORMAT_JSON = "json";
    private static final String FORMAT_PARAM = "format";
    private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final int MAXIMUM_BATCH_SIZE = 50;
    private static final String ME = "me";
    private static final String MIGRATION_BUNDLE_PARAM = "migration_bundle";
    private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    private static final String MY_FEED = "me/feed";
    private static final String MY_FRIENDS = "me/friends";
    private static final String MY_PHOTOS = "me/photos";
    private static final String MY_VIDEOS = "me/videos";
    private static final String PICTURE_PARAM = "picture";
    private static final String SDK_ANDROID = "android";
    private static final String SDK_PARAM = "sdk";
    private static final String SEARCH = "search";
    private static final String USER_AGENT_BASE = "FBAndroidSDK";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static String defaultBatchApplicationId;
    private static volatile String userAgent;
    private String batchEntryDependsOn;
    private String batchEntryName;
    private boolean batchEntryOmitResultOnSuccess;
    private Callback callback;
    private GraphObject graphObject;
    private String graphPath;
    private HttpMethod httpMethod;
    private String overriddenURL;
    private Bundle parameters;
    private String restMethod;
    private Session session;
    
    public Request() {
        this(null, null, null, null, null);
    }
    
    public Request(final Session session, final String s) {
        this(session, s, null, null, null);
    }
    
    public Request(final Session session, final String s, final Bundle bundle, final HttpMethod httpMethod) {
        this(session, s, bundle, httpMethod, null);
    }
    
    public Request(final Session session, final String graphPath, final Bundle bundle, final HttpMethod httpMethod, final Callback callback) {
        this.batchEntryOmitResultOnSuccess = true;
        this.session = session;
        this.graphPath = graphPath;
        this.callback = callback;
        this.setHttpMethod(httpMethod);
        if (bundle != null) {
            this.parameters = new Bundle(bundle);
        }
        else {
            this.parameters = new Bundle();
        }
        if (!this.parameters.containsKey("migration_bundle")) {
            this.parameters.putString("migration_bundle", "fbsdk:20121026");
        }
    }
    
    Request(final Session session, final URL url) {
        this.batchEntryOmitResultOnSuccess = true;
        this.session = session;
        this.overriddenURL = url.toString();
        this.setHttpMethod(HttpMethod.GET);
        this.parameters = new Bundle();
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
    
    static HttpURLConnection createConnection(final URL url) throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", getUserAgent());
        httpURLConnection.setRequestProperty("Content-Type", getMimeContentType());
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
    
    public static List<Response> executeConnectionAndWait(final HttpURLConnection httpURLConnection, final Collection<Request> collection) {
        return executeConnectionAndWait(httpURLConnection, new RequestBatch(collection));
    }
    
    public static RequestAsyncTask executeConnectionAsync(final Handler callbackHandler, final HttpURLConnection httpURLConnection, final RequestBatch requestBatch) {
        Validate.notNull(httpURLConnection, "connection");
        final RequestAsyncTask requestAsyncTask = new RequestAsyncTask(httpURLConnection, requestBatch);
        requestBatch.setCallbackHandler(callbackHandler);
        requestAsyncTask.executeOnSettingsExecutor();
        return requestAsyncTask;
    }
    
    public static RequestAsyncTask executeConnectionAsync(final HttpURLConnection httpURLConnection, final RequestBatch requestBatch) {
        return executeConnectionAsync(null, httpURLConnection, requestBatch);
    }
    
    public static RequestAsyncTask executeGraphPathRequestAsync(final Session session, final String s, final Callback callback) {
        return newGraphPathRequest(session, s, callback).executeAsync();
    }
    
    public static RequestAsyncTask executeMeRequestAsync(final Session session, final GraphUserCallback graphUserCallback) {
        return newMeRequest(session, graphUserCallback).executeAsync();
    }
    
    public static RequestAsyncTask executeMyFriendsRequestAsync(final Session session, final GraphUserListCallback graphUserListCallback) {
        return newMyFriendsRequest(session, graphUserListCallback).executeAsync();
    }
    
    public static RequestAsyncTask executePlacesSearchRequestAsync(final Session session, final Location location, final int n, final int n2, final String s, final GraphPlaceListCallback graphPlaceListCallback) {
        return newPlacesSearchRequest(session, location, n, n2, s, graphPlaceListCallback).executeAsync();
    }
    
    public static RequestAsyncTask executePostRequestAsync(final Session session, final String s, final GraphObject graphObject, final Callback callback) {
        return newPostRequest(session, s, graphObject, callback).executeAsync();
    }
    
    public static RequestAsyncTask executeRestRequestAsync(final Session session, final String s, final Bundle bundle, final HttpMethod httpMethod) {
        return newRestRequest(session, s, bundle, httpMethod).executeAsync();
    }
    
    public static RequestAsyncTask executeStatusUpdateRequestAsync(final Session session, final String s, final Callback callback) {
        return newStatusUpdateRequest(session, s, callback).executeAsync();
    }
    
    public static RequestAsyncTask executeUploadPhotoRequestAsync(final Session session, final Bitmap bitmap, final Callback callback) {
        return newUploadPhotoRequest(session, bitmap, callback).executeAsync();
    }
    
    public static RequestAsyncTask executeUploadPhotoRequestAsync(final Session session, final File file, final Callback callback) throws FileNotFoundException {
        return newUploadPhotoRequest(session, file, callback).executeAsync();
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
    
    public static final String getDefaultBatchApplicationId() {
        return Request.defaultBatchApplicationId;
    }
    
    private static String getMimeContentType() {
        return String.format("multipart/form-data; boundary=%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
    }
    
    private static String getUserAgent() {
        if (Request.userAgent == null) {
            Request.userAgent = String.format("%s.%s", "FBAndroidSDK", "3.0.0");
        }
        return Request.userAgent;
    }
    
    private static boolean isSupportedAttachmentType(final Object o) {
        return o instanceof Bitmap || o instanceof byte[] || o instanceof ParcelFileDescriptor;
    }
    
    private static boolean isSupportedParameterType(final Object o) {
        return o instanceof String || o instanceof Boolean || o instanceof Number || o instanceof Date;
    }
    
    public static Request newGraphPathRequest(final Session session, final String s, final Callback callback) {
        return new Request(session, s, null, null, callback);
    }
    
    public static Request newMeRequest(final Session session, final GraphUserCallback graphUserCallback) {
        return new Request(session, "me", null, null, (Callback)new Callback() {
            @Override
            public void onCompleted(final Response response) {
                if (graphUserCallback != null) {
                    graphUserCallback.onCompleted(response.getGraphObjectAs(GraphUser.class), response);
                }
            }
        });
    }
    
    public static Request newMyFriendsRequest(final Session session, final GraphUserListCallback graphUserListCallback) {
        return new Request(session, "me/friends", null, null, (Callback)new Callback() {
            @Override
            public void onCompleted(final Response response) {
                if (graphUserListCallback != null) {
                    graphUserListCallback.onCompleted(typedListFromResponse(response, (Class<GraphObject>)GraphUser.class), response);
                }
            }
        });
    }
    
    public static Request newPlacesSearchRequest(final Session session, final Location location, final int n, final int n2, final String s, final GraphPlaceListCallback graphPlaceListCallback) {
        if (location == null && Utility.isNullOrEmpty(s)) {
            throw new FacebookException("Either location or searchText must be specified.");
        }
        final Bundle bundle = new Bundle(5);
        bundle.putString("type", "place");
        bundle.putInt("limit", n2);
        if (location != null) {
            bundle.putString("center", String.format(Locale.US, "%f,%f", location.getLatitude(), location.getLongitude()));
            bundle.putInt("distance", n);
        }
        if (!Utility.isNullOrEmpty(s)) {
            bundle.putString("q", s);
        }
        return new Request(session, "search", bundle, HttpMethod.GET, (Callback)new Callback() {
            @Override
            public void onCompleted(final Response response) {
                if (graphPlaceListCallback != null) {
                    graphPlaceListCallback.onCompleted(typedListFromResponse(response, (Class<GraphObject>)GraphPlace.class), response);
                }
            }
        });
    }
    
    public static Request newPostRequest(final Session session, final String s, final GraphObject graphObject, final Callback callback) {
        final Request request = new Request(session, s, null, HttpMethod.POST, callback);
        request.setGraphObject(graphObject);
        return request;
    }
    
    public static Request newRestRequest(final Session session, final String restMethod, final Bundle bundle, final HttpMethod httpMethod) {
        final Request request = new Request(session, null, bundle, httpMethod);
        request.setRestMethod(restMethod);
        return request;
    }
    
    public static Request newStatusUpdateRequest(final Session session, final String s, final Callback callback) {
        final Bundle bundle = new Bundle();
        bundle.putString("message", s);
        return new Request(session, "me/feed", bundle, HttpMethod.POST, callback);
    }
    
    public static Request newUploadPhotoRequest(final Session session, final Bitmap bitmap, final Callback callback) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("picture", (Parcelable)bitmap);
        return new Request(session, "me/photos", bundle, HttpMethod.POST, callback);
    }
    
    public static Request newUploadPhotoRequest(final Session session, final File file, final Callback callback) throws FileNotFoundException {
        final ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("picture", (Parcelable)open);
        return new Request(session, "me/photos", bundle, HttpMethod.POST, callback);
    }
    
    public static Request newUploadVideoRequest(final Session session, final File file, final Callback callback) throws FileNotFoundException {
        final ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable(file.getName(), (Parcelable)open);
        return new Request(session, "me/videos", bundle, HttpMethod.POST, callback);
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
    
    private static void processGraphObject(final GraphObject graphObject, final String s, final KeyValueSerializer keyValueSerializer) throws IOException {
        boolean b = false;
        if (s.startsWith("me/") || s.startsWith("/me/")) {
            final int index = s.indexOf(":");
            final int index2 = s.indexOf("?");
            if (index > 3 && (index2 == -1 || index < index2)) {
                b = true;
            }
            else {
                b = false;
            }
        }
        for (final Map.Entry<String, Object> entry : graphObject.asMap().entrySet()) {
            processGraphObjectProperty(entry.getKey(), entry.getValue(), keyValueSerializer, b && entry.getKey().equalsIgnoreCase("image"));
        }
    }
    
    private static void processGraphObjectProperty(final String s, final Object o, final KeyValueSerializer keyValueSerializer, final boolean b) throws IOException {
        final Class<?> class1 = o.getClass();
        Object o2;
        Class<?> clazz;
        if (GraphObject.class.isAssignableFrom(class1)) {
            o2 = ((GraphObject)o).getInnerJSONObject();
            clazz = ((JSONObject)o2).getClass();
        }
        else {
            clazz = class1;
            o2 = o;
            if (GraphObjectList.class.isAssignableFrom(class1)) {
                o2 = ((GraphObjectList)o).getInnerJSONArray();
                clazz = ((JSONObject)o2).getClass();
            }
        }
        if (JSONObject.class.isAssignableFrom(clazz)) {
            final JSONObject jsonObject = (JSONObject)o2;
            if (b) {
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    processGraphObjectProperty(String.format("%s[%s]", s, s2), jsonObject.opt(s2), keyValueSerializer, b);
                }
            }
            else if (jsonObject.has("id")) {
                processGraphObjectProperty(s, jsonObject.optString("id"), keyValueSerializer, b);
            }
            else if (jsonObject.has("url")) {
                processGraphObjectProperty(s, jsonObject.optString("url"), keyValueSerializer, b);
            }
        }
        else if (JSONArray.class.isAssignableFrom(clazz)) {
            final JSONArray jsonArray = (JSONArray)o2;
            for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                processGraphObjectProperty(String.format("%s[%d]", s, i), jsonArray.opt(i), keyValueSerializer, b);
            }
        }
        else {
            if (String.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz)) {
                keyValueSerializer.writeString(s, o2.toString());
                return;
            }
            if (Date.class.isAssignableFrom(clazz)) {
                keyValueSerializer.writeString(s, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date)o2));
            }
        }
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
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (final Pair pair : list2) {
                        ((Callback)pair.first).onCompleted((Response)pair.second);
                    }
                    final Iterator<RequestBatch.Callback> iterator2 = requestBatch.getCallbacks().iterator();
                    while (iterator2.hasNext()) {
                        ((RequestBatch.Callback)iterator2.next()).onBatchCompleted(requestBatch);
                    }
                }
            };
            final Handler callbackHandler = requestBatch.getCallbackHandler();
            if (callbackHandler != null) {
                callbackHandler.post((Runnable)runnable);
                return;
            }
            runnable.run();
        }
    }
    
    private static void serializeAttachments(final Bundle bundle, final Serializer serializer) throws IOException {
        for (final String s : bundle.keySet()) {
            final Object value = bundle.get(s);
            if (isSupportedAttachmentType(value)) {
                serializer.writeObject(s, value);
            }
        }
    }
    
    private static void serializeParameters(final Bundle bundle, final Serializer serializer) throws IOException {
        for (final String s : bundle.keySet()) {
            final Object value = bundle.get(s);
            if (isSupportedParameterType(value)) {
                serializer.writeObject(s, value);
            }
        }
    }
    
    private static void serializeRequestsAsJSON(final Serializer serializer, final Collection<Request> collection, final Bundle bundle) throws JSONException, IOException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<Request> iterator = collection.iterator();
        while (iterator.hasNext()) {
            iterator.next().serializeToBatch(jsonArray, bundle);
        }
        serializer.writeString("batch", jsonArray.toString());
    }
    
    private void serializeToBatch(final JSONArray jsonArray, final Bundle bundle) throws JSONException, IOException {
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
                final String format = String.format("%s%d", "file", bundle.size());
                list.add(format);
                Utility.putObjectInBundle(bundle, format, value);
            }
        }
        if (!list.isEmpty()) {
            jsonObject.put("attached_files", (Object)TextUtils.join((CharSequence)",", (Iterable)list));
        }
        if (this.graphObject != null) {
            final ArrayList list2 = new ArrayList();
            processGraphObject(this.graphObject, urlForBatchedRequest, (KeyValueSerializer)new KeyValueSerializer() {
                @Override
                public void writeString(final String s, final String s2) throws IOException {
                    list2.add(String.format("%s=%s", s, URLEncoder.encode(s2, "UTF-8")));
                }
            });
            jsonObject.put("body", (Object)TextUtils.join((CharSequence)"&", (Iterable)list2));
        }
        jsonArray.put((Object)jsonObject);
    }
    
    static final void serializeToUrlConnection(final RequestBatch requestBatch, HttpURLConnection httpURLConnection) throws IOException, JSONException {
        boolean b = false;
        final Logger logger = new Logger(LoggingBehavior.REQUESTS, "Request");
        final int size = requestBatch.size();
        Object o;
        if (size == 1) {
            o = requestBatch.get(0).httpMethod;
        }
        else {
            o = HttpMethod.POST;
        }
        httpURLConnection.setRequestMethod(((Enum)o).name());
        Object o2 = httpURLConnection.getURL();
        logger.append("Request:\n");
        logger.appendKeyValue("Id", requestBatch.getId());
        logger.appendKeyValue("URL", o2);
        logger.appendKeyValue("Method", httpURLConnection.getRequestMethod());
        logger.appendKeyValue("User-Agent", httpURLConnection.getRequestProperty("User-Agent"));
        logger.appendKeyValue("Content-Type", httpURLConnection.getRequestProperty("Content-Type"));
        httpURLConnection.setConnectTimeout(requestBatch.getTimeout());
        httpURLConnection.setReadTimeout(requestBatch.getTimeout());
        if (o == HttpMethod.POST) {
            b = true;
        }
        if (!b) {
            logger.log();
            return;
        }
        while (true) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection = (HttpURLConnection)new BufferedOutputStream(httpURLConnection.getOutputStream());
            while (true) {
                try {
                    o = new Serializer((BufferedOutputStream)httpURLConnection, logger);
                    if (size == 1) {
                        final Request value = requestBatch.get(0);
                        logger.append("  Parameters:\n");
                        serializeParameters(value.parameters, (Serializer)o);
                        logger.append("  Attachments:\n");
                        serializeAttachments(value.parameters, (Serializer)o);
                        if (value.graphObject != null) {
                            processGraphObject(value.graphObject, ((URL)o2).getPath(), (KeyValueSerializer)o);
                        }
                        ((FilterOutputStream)httpURLConnection).close();
                        logger.log();
                        return;
                    }
                    o2 = getBatchAppId(requestBatch);
                    if (Utility.isNullOrEmpty((String)o2)) {
                        throw new FacebookException("At least one request in a batch must have an open Session, or a default app ID must be specified.");
                    }
                }
                finally {
                    ((FilterOutputStream)httpURLConnection).close();
                }
                ((Serializer)o).writeString("batch_app_id", (String)o2);
                o2 = new Bundle();
                final Collection<Request> collection;
                serializeRequestsAsJSON((Serializer)o, collection, (Bundle)o2);
                logger.append("  Attachments:\n");
                serializeAttachments((Bundle)o2, (Serializer)o);
                continue;
            }
        }
    }
    
    public static final void setDefaultBatchApplicationId(final String defaultBatchApplicationId) {
        Request.defaultBatchApplicationId = defaultBatchApplicationId;
    }
    
    public static HttpURLConnection toHttpConnection(final RequestBatch p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/facebook/RequestBatch.iterator:()Ljava/util/Iterator;
        //     4: astore_1       
        //     5: aload_1        
        //     6: invokeinterface java/util/Iterator.hasNext:()Z
        //    11: ifeq            29
        //    14: aload_1        
        //    15: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    20: checkcast       Lcom/facebook/Request;
        //    23: invokespecial   com/facebook/Request.validate:()V
        //    26: goto            5
        //    29: aload_0        
        //    30: invokevirtual   com/facebook/RequestBatch.size:()I
        //    33: iconst_1       
        //    34: if_icmpne       65
        //    37: new             Ljava/net/URL;
        //    40: dup            
        //    41: aload_0        
        //    42: iconst_0       
        //    43: invokevirtual   com/facebook/RequestBatch.get:(I)Lcom/facebook/Request;
        //    46: invokevirtual   com/facebook/Request.getUrlForSingleRequest:()Ljava/lang/String;
        //    49: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    52: astore_1       
        //    53: aload_1        
        //    54: invokestatic    com/facebook/Request.createConnection:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //    57: astore_1       
        //    58: aload_0        
        //    59: aload_1        
        //    60: invokestatic    com/facebook/Request.serializeToUrlConnection:(Lcom/facebook/RequestBatch;Ljava/net/HttpURLConnection;)V
        //    63: aload_1        
        //    64: areturn        
        //    65: new             Ljava/net/URL;
        //    68: dup            
        //    69: ldc_w           "https://graph.facebook.com"
        //    72: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    75: astore_1       
        //    76: goto            53
        //    79: astore_0       
        //    80: new             Lcom/facebook/FacebookException;
        //    83: dup            
        //    84: ldc_w           "could not construct URL for request"
        //    87: aload_0        
        //    88: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    91: athrow         
        //    92: astore_0       
        //    93: new             Lcom/facebook/FacebookException;
        //    96: dup            
        //    97: ldc_w           "could not construct request body"
        //   100: aload_0        
        //   101: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   104: athrow         
        //   105: astore_0       
        //   106: new             Lcom/facebook/FacebookException;
        //   109: dup            
        //   110: ldc_w           "could not construct request body"
        //   113: aload_0        
        //   114: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   117: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  29     53     79     92     Ljava/net/MalformedURLException;
        //  53     63     92     105    Ljava/io/IOException;
        //  53     63     105    118    Lorg/json/JSONException;
        //  65     76     79     92     Ljava/net/MalformedURLException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0053:
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
    
    public static HttpURLConnection toHttpConnection(final Collection<Request> collection) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)collection, "requests");
        return toHttpConnection(new RequestBatch(collection));
    }
    
    public static HttpURLConnection toHttpConnection(final Request... array) {
        return toHttpConnection(Arrays.asList(array));
    }
    
    private static <T extends GraphObject> List<T> typedListFromResponse(final Response response, final Class<T> clazz) {
        final GraphMultiResult graphMultiResult = response.getGraphObjectAs(GraphMultiResult.class);
        if (graphMultiResult != null) {
            final GraphObjectList<GraphObject> data = graphMultiResult.getData();
            if (data != null) {
                return data.castToListOf(clazz);
            }
        }
        return null;
    }
    
    private void validate() {
        if (this.graphPath != null && this.restMethod != null) {
            throw new IllegalArgumentException("Only one of a graph path or REST method may be specified per request.");
        }
    }
    
    public final Response executeAndWait() {
        return executeAndWait(this);
    }
    
    public final RequestAsyncTask executeAsync() {
        return executeBatchAsync(this);
    }
    
    public final String getBatchEntryDependsOn() {
        return this.batchEntryDependsOn;
    }
    
    public final String getBatchEntryName() {
        return this.batchEntryName;
    }
    
    public final boolean getBatchEntryOmitResultOnSuccess() {
        return this.batchEntryOmitResultOnSuccess;
    }
    
    public final Callback getCallback() {
        return this.callback;
    }
    
    public final GraphObject getGraphObject() {
        return this.graphObject;
    }
    
    public final String getGraphPath() {
        return this.graphPath;
    }
    
    public final HttpMethod getHttpMethod() {
        return this.httpMethod;
    }
    
    public final Bundle getParameters() {
        return this.parameters;
    }
    
    public final String getRestMethod() {
        return this.restMethod;
    }
    
    public final Session getSession() {
        return this.session;
    }
    
    final String getUrlForBatchedRequest() {
        if (this.overriddenURL != null) {
            throw new FacebookException("Can't override URL for a batch request");
        }
        String s;
        if (this.restMethod != null) {
            s = "method/" + this.restMethod;
        }
        else {
            s = this.graphPath;
        }
        this.addCommonParameters();
        return this.appendParametersToBaseUrl(s);
    }
    
    final String getUrlForSingleRequest() {
        if (this.overriddenURL != null) {
            return this.overriddenURL.toString();
        }
        String s;
        if (this.restMethod != null) {
            s = "https://api.facebook.com/method/" + this.restMethod;
        }
        else {
            s = "https://graph.facebook.com/" + this.graphPath;
        }
        this.addCommonParameters();
        return this.appendParametersToBaseUrl(s);
    }
    
    public final void setBatchEntryDependsOn(final String batchEntryDependsOn) {
        this.batchEntryDependsOn = batchEntryDependsOn;
    }
    
    public final void setBatchEntryName(final String batchEntryName) {
        this.batchEntryName = batchEntryName;
    }
    
    public final void setBatchEntryOmitResultOnSuccess(final boolean batchEntryOmitResultOnSuccess) {
        this.batchEntryOmitResultOnSuccess = batchEntryOmitResultOnSuccess;
    }
    
    public final void setCallback(final Callback callback) {
        this.callback = callback;
    }
    
    public final void setGraphObject(final GraphObject graphObject) {
        this.graphObject = graphObject;
    }
    
    public final void setGraphPath(final String graphPath) {
        this.graphPath = graphPath;
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
    
    public final void setRestMethod(final String restMethod) {
        this.restMethod = restMethod;
    }
    
    public final void setSession(final Session session) {
        this.session = session;
    }
    
    @Override
    public String toString() {
        return "{Request: " + " session: " + this.session + ", graphPath: " + this.graphPath + ", graphObject: " + this.graphObject + ", restMethod: " + this.restMethod + ", httpMethod: " + this.httpMethod + ", parameters: " + this.parameters + "}";
    }
    
    public interface Callback
    {
        void onCompleted(final Response p0);
    }
    
    public interface GraphPlaceListCallback
    {
        void onCompleted(final List<GraphPlace> p0, final Response p1);
    }
    
    public interface GraphUserCallback
    {
        void onCompleted(final GraphUser p0, final Response p1);
    }
    
    public interface GraphUserListCallback
    {
        void onCompleted(final List<GraphUser> p0, final Response p1);
    }
    
    private interface KeyValueSerializer
    {
        void writeString(final String p0, final String p1) throws IOException;
    }
    
    private static class Serializer implements KeyValueSerializer
    {
        private boolean firstWrite;
        private final Logger logger;
        private final BufferedOutputStream outputStream;
        
        public Serializer(final BufferedOutputStream outputStream, final Logger logger) {
            this.firstWrite = true;
            this.outputStream = outputStream;
            this.logger = logger;
        }
        
        public void write(final String s, final Object... array) throws IOException {
            if (this.firstWrite) {
                this.outputStream.write("--".getBytes());
                this.outputStream.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
                this.outputStream.write("\r\n".getBytes());
                this.firstWrite = false;
            }
            this.outputStream.write(String.format(s, array).getBytes());
        }
        
        public void writeBitmap(final String s, final Bitmap bitmap) throws IOException {
            this.writeContentDisposition(s, s, "image/png");
            bitmap.compress(Bitmap$CompressFormat.PNG, 100, (OutputStream)this.outputStream);
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            this.logger.appendKeyValue("    " + s, "<Image>");
        }
        
        public void writeBytes(final String s, final byte[] array) throws IOException {
            this.writeContentDisposition(s, s, "content/unknown");
            this.outputStream.write(array);
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            this.logger.appendKeyValue("    " + s, String.format("<Data: %d>", array.length));
        }
        
        public void writeContentDisposition(final String s, final String s2, final String s3) throws IOException {
            this.write("Content-Disposition: form-data; name=\"%s\"", s);
            if (s2 != null) {
                this.write("; filename=\"%s\"", s2);
            }
            this.writeLine("", new Object[0]);
            if (s3 != null) {
                this.writeLine("%s: %s", "Content-Type", s3);
            }
            this.writeLine("", new Object[0]);
        }
        
        public void writeFile(final String p0, final ParcelFileDescriptor p1) throws IOException {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: aload_1        
            //     2: aload_1        
            //     3: ldc             "content/unknown"
            //     5: invokevirtual   com/facebook/Request$Serializer.writeContentDisposition:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
            //     8: aconst_null    
            //     9: astore          6
            //    11: aconst_null    
            //    12: astore          5
            //    14: iconst_0       
            //    15: istore_3       
            //    16: new             Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
            //    19: dup            
            //    20: aload_2        
            //    21: invokespecial   android/os/ParcelFileDescriptor$AutoCloseInputStream.<init>:(Landroid/os/ParcelFileDescriptor;)V
            //    24: astore_2       
            //    25: new             Ljava/io/BufferedInputStream;
            //    28: dup            
            //    29: aload_2        
            //    30: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
            //    33: astore          7
            //    35: sipush          8192
            //    38: newarray        B
            //    40: astore          5
            //    42: aload           7
            //    44: aload           5
            //    46: invokevirtual   java/io/BufferedInputStream.read:([B)I
            //    49: istore          4
            //    51: iload           4
            //    53: iconst_m1      
            //    54: if_icmpeq       77
            //    57: aload_0        
            //    58: getfield        com/facebook/Request$Serializer.outputStream:Ljava/io/BufferedOutputStream;
            //    61: aload           5
            //    63: iconst_0       
            //    64: iload           4
            //    66: invokevirtual   java/io/BufferedOutputStream.write:([BII)V
            //    69: iload_3        
            //    70: iload           4
            //    72: iadd           
            //    73: istore_3       
            //    74: goto            42
            //    77: aload           7
            //    79: ifnull          87
            //    82: aload           7
            //    84: invokevirtual   java/io/BufferedInputStream.close:()V
            //    87: aload_2        
            //    88: ifnull          95
            //    91: aload_2        
            //    92: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseInputStream.close:()V
            //    95: aload_0        
            //    96: ldc             ""
            //    98: iconst_0       
            //    99: anewarray       Ljava/lang/Object;
            //   102: invokevirtual   com/facebook/Request$Serializer.writeLine:(Ljava/lang/String;[Ljava/lang/Object;)V
            //   105: aload_0        
            //   106: invokevirtual   com/facebook/Request$Serializer.writeRecordBoundary:()V
            //   109: aload_0        
            //   110: getfield        com/facebook/Request$Serializer.logger:Lcom/facebook/internal/Logger;
            //   113: new             Ljava/lang/StringBuilder;
            //   116: dup            
            //   117: invokespecial   java/lang/StringBuilder.<init>:()V
            //   120: ldc             "    "
            //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   125: aload_1        
            //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   129: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   132: ldc             "<Data: %d>"
            //   134: iconst_1       
            //   135: anewarray       Ljava/lang/Object;
            //   138: dup            
            //   139: iconst_0       
            //   140: iload_3        
            //   141: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //   144: aastore        
            //   145: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   148: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
            //   151: return         
            //   152: astore_2       
            //   153: aload           6
            //   155: astore_1       
            //   156: aload           5
            //   158: ifnull          166
            //   161: aload           5
            //   163: invokevirtual   java/io/BufferedInputStream.close:()V
            //   166: aload_1        
            //   167: ifnull          174
            //   170: aload_1        
            //   171: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseInputStream.close:()V
            //   174: aload_2        
            //   175: athrow         
            //   176: astore          6
            //   178: aload_2        
            //   179: astore_1       
            //   180: aload           6
            //   182: astore_2       
            //   183: goto            156
            //   186: astore          6
            //   188: aload           7
            //   190: astore          5
            //   192: aload_2        
            //   193: astore_1       
            //   194: aload           6
            //   196: astore_2       
            //   197: goto            156
            //    Exceptions:
            //  throws java.io.IOException
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  16     25     152    156    Any
            //  25     35     176    186    Any
            //  35     42     186    200    Any
            //  42     51     186    200    Any
            //  57     69     186    200    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 105, Size: 105
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
            //     at java.util.ArrayList.get(ArrayList.java:429)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public void writeLine(final String s, final Object... array) throws IOException {
            this.write(s, array);
            this.write("\r\n", new Object[0]);
        }
        
        public void writeObject(final String s, final Object o) throws IOException {
            if (isSupportedParameterType(o)) {
                this.writeString(s, parameterToString(o));
                return;
            }
            if (o instanceof Bitmap) {
                this.writeBitmap(s, (Bitmap)o);
                return;
            }
            if (o instanceof byte[]) {
                this.writeBytes(s, (byte[])o);
                return;
            }
            if (o instanceof ParcelFileDescriptor) {
                this.writeFile(s, (ParcelFileDescriptor)o);
                return;
            }
            throw new IllegalArgumentException("value is not a supported type: String, Bitmap, byte[]");
        }
        
        public void writeRecordBoundary() throws IOException {
            this.writeLine("--%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
        }
        
        @Override
        public void writeString(final String s, final String s2) throws IOException {
            this.writeContentDisposition(s, null, null);
            this.writeLine("%s", s2);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, s2);
            }
        }
    }
}
