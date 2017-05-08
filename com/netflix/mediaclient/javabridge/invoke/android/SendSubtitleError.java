// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import com.netflix.mediaclient.StatusCode;
import com.android.volley.NetworkResponse;
import com.netflix.mediaclient.android.app.NetworkErrorStatus;
import org.json.JSONException;
import org.json.JSONArray;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SendSubtitleError extends BaseInvoke
{
    private static final String METHOD = "sendSubtitleError";
    private static final String PROPERTY_CDN_ID = "cdnid";
    private static final String PROPERTY_DNS_SERVERS = "nameServers";
    private static final String PROPERTY_DOWNLOADABLE_ID = "did";
    private static final String PROPERTY_ERROR = "error";
    private static final String PROPERTY_ERROR_CAUSE = "cause";
    private static final String PROPERTY_ERROR_CODE = "statusCode";
    private static final String PROPERTY_ERROR_CODE_DESC = "statusCodeDesc";
    private static final String PROPERTY_ERROR_DEEP = "errorDeep";
    private static final String PROPERTY_ERROR_MESSAGE = "message";
    private static final String PROPERTY_ERROR_RAW_RESP = "rawResponse";
    private static final String PROPERTY_ERROR_STACKTRACE = "stackTrace";
    private static final String PROPERTY_LANGUAGE = "language";
    private static final String PROPERTY_LANG_ISO_CODE_639_1 = "iso639_1";
    private static final int PROPERTY_MAX_SIZE = 1000;
    private static final String PROPERTY_REASON = "reason";
    private static final String PROPERTY_RETRY = "retry";
    private static final String PROPERTY_SUBTITLE_ID = "subtitleId";
    private static final String PROPERTY_SUBTITLE_TYPE = "subtitleType";
    private static final String PROPERTY_TRACK_TYPE = "trackType";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_XID = "xid";
    private static final String SUBTITLE_TYPE_IMAGE = "image";
    private static final String SUBTITLE_TYPE_TEXT = "text";
    private static final String TARGET = "android";
    
    public SendSubtitleError(final String s, final SubtitleUrl subtitleUrl, final IMedia$SubtitleFailure media$SubtitleFailure, final boolean b, final Subtitle subtitle, final Status status, final String[] array) {
        super("android", "sendSubtitleError");
        this.setArguments(s, subtitleUrl, media$SubtitleFailure, b, subtitle, status, array);
    }
    
    private void addThrowable(final VolleyError volleyError, final JSONObject jsonObject) {
        if (volleyError.getMessage() != null) {
            jsonObject.put("message", (Object)StringUtils.getSubStringSafely(volleyError.getMessage(), 1000));
        }
        if (volleyError.getStackTrace() != null) {
            final String stackTraceString = Log.getStackTraceString(volleyError, 1000);
            if (StringUtils.isNotEmpty(stackTraceString)) {
                jsonObject.put("stackTrace", (Object)stackTraceString);
            }
        }
        if (volleyError.getCause() != null) {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject.put("cause", (Object)jsonObject2);
            jsonObject2.put("cause", (Object)volleyError.getCause().getClass().getSimpleName());
            if (volleyError.getCause().getMessage() != null) {
                jsonObject2.put("message", (Object)StringUtils.getSubStringSafely(volleyError.getCause().getMessage(), 1000));
            }
            if (volleyError.getCause().getStackTrace() != null) {
                final String stackTraceString2 = Log.getStackTraceString(volleyError.getCause(), 1000);
                if (StringUtils.isNotEmpty(stackTraceString2)) {
                    jsonObject2.put("stackTrace", (Object)stackTraceString2);
                }
            }
        }
    }
    
    private void setArguments(String s, final SubtitleUrl subtitleUrl, final IMedia$SubtitleFailure media$SubtitleFailure, final boolean b, final Subtitle subtitle, final Status status, final String[] array) {
        if (s != null) {
            if (Log.isLoggable()) {
                Log.d("nf_invoke", "Subtitle data: " + subtitle);
            }
            while (true) {
                while (true) {
                    Label_0415: {
                        while (true) {
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject();
                                jsonObject.put("url", (Object)s);
                                jsonObject.put("retry", b);
                                if (media$SubtitleFailure != null) {
                                    jsonObject.put("reason", (Object)media$SubtitleFailure.toString());
                                }
                                if (subtitleUrl != null) {
                                    jsonObject.put("xid", subtitleUrl.getXid());
                                    jsonObject.put("cdnid", subtitleUrl.getCdnId());
                                    if (subtitleUrl.getDownloadableId() != null) {
                                        jsonObject.put("did", (Object)subtitleUrl.getDownloadableId());
                                    }
                                    if (subtitleUrl.getProfile() != null) {
                                        if (subtitleUrl.getProfile() == IMedia$SubtitleProfile.IMAGE) {
                                            break Label_0415;
                                        }
                                        s = "text";
                                        jsonObject.put("subtitleType", (Object)s);
                                    }
                                }
                                if (array == null || array.length <= 0) {
                                    goto Label_0375;
                                }
                                final JSONArray jsonArray = new JSONArray();
                                for (int length = array.length, i = 0; i < length; ++i) {
                                    jsonArray.put((Object)array[i]);
                                }
                                jsonObject.put("nameServers", (Object)jsonArray);
                                if (subtitle != null) {
                                    jsonObject.put("trackType", subtitle.getTrackType());
                                    if (subtitle.getId() != null) {
                                        jsonObject.put("subtitleId", (Object)subtitle.getId());
                                    }
                                    if (subtitle.getLanguageDescription() != null) {
                                        jsonObject.put("language", (Object)subtitle.getLanguageDescription());
                                    }
                                    if (subtitle.getLanguageCodeIso639_1() != null) {
                                        jsonObject.put("iso639_1", (Object)subtitle.getLanguageCodeIso639_1());
                                    }
                                }
                                final JSONObject error = this.toError(status);
                                if (error != null) {
                                    jsonObject.put("error", (Object)error);
                                    this.arguments = jsonObject.toString();
                                    if (Log.isLoggable()) {
                                        Log.d("nf_invoke", "Argument: " + this.arguments);
                                        return;
                                    }
                                    break;
                                }
                            }
                            catch (JSONException ex) {
                                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                                return;
                            }
                            catch (Throwable t) {
                                Log.e("nf_invoke", "Unable to Log failed subtitle ", t);
                                return;
                            }
                            jsonObject.put("error", (Object)new JSONObject());
                            continue;
                        }
                    }
                    s = "image";
                    continue;
                }
            }
        }
    }
    
    private JSONObject toError(final Status status) {
        if (status == null) {
            return null;
        }
        if (status instanceof NetworkErrorStatus) {
            return this.toErrorFromNetworkFailure((NetworkErrorStatus)status);
        }
        return this.toErrorFromStatus(status);
    }
    
    private JSONObject toErrorFromNetworkFailure(final NetworkErrorStatus networkErrorStatus) {
        if (networkErrorStatus.getVolleyError() == null) {
            return this.toErrorFromStatus(networkErrorStatus);
        }
        return this.toErrorFromVolleyError(networkErrorStatus.getVolleyError());
    }
    
    private JSONObject toErrorFromNetworkResponse(final NetworkResponse networkResponse, final VolleyError volleyError) {
        final JSONObject jsonObject = new JSONObject();
        this.addThrowable(volleyError, jsonObject);
        jsonObject.put("statusCode", (Object)String.valueOf(networkResponse.statusCode));
        jsonObject.put("statusCodeDesc", (Object)"HTTP_SC");
        if (networkResponse.data != null) {
            jsonObject.put("rawResponse", (Object)StringUtils.getSubStringSafely(new String(networkResponse.data, "UTF-8"), 1000));
        }
        return jsonObject;
    }
    
    private JSONObject toErrorFromStatus(final Status status) {
        final JSONObject jsonObject = new JSONObject();
        if (status.getStatusCode() != null) {
            jsonObject.put("statusCode", status.getStatusCode().getValue());
            jsonObject.put("statusCodeDesc", (Object)status.getStatusCode().name());
        }
        if (status.getMessage() != null) {
            jsonObject.put("message", (Object)StringUtils.getSubStringSafely(status.getMessage(), 1000));
        }
        return jsonObject;
    }
    
    private JSONObject toErrorFromVolleyError(final VolleyError volleyError) {
        if (volleyError.networkResponse != null) {
            return this.toErrorFromNetworkResponse(volleyError.networkResponse, volleyError);
        }
        return this.toErrorFromVolleyErrorException(volleyError);
    }
    
    private JSONObject toErrorFromVolleyErrorException(final VolleyError volleyError) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCode.NETWORK_ERROR.getValue());
        jsonObject.put("statusCodeDesc", (Object)StatusCode.NETWORK_ERROR.name());
        this.addThrowable(volleyError, jsonObject);
        return jsonObject;
    }
}
