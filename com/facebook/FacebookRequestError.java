// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import org.json.JSONException;
import com.facebook.internal.Utility;
import com.facebook.android.R$string;
import org.json.JSONObject;
import java.net.HttpURLConnection;

public final class FacebookRequestError
{
    private static final FacebookRequestError$Range EC_RANGE_PERMISSION;
    private static final FacebookRequestError$Range HTTP_RANGE_CLIENT_ERROR;
    private static final FacebookRequestError$Range HTTP_RANGE_SERVER_ERROR;
    private static final FacebookRequestError$Range HTTP_RANGE_SUCCESS;
    private final Object batchRequestResult;
    private final FacebookRequestError$Category category;
    private final HttpURLConnection connection;
    private final int errorCode;
    private final boolean errorIsTransient;
    private final String errorMessage;
    private final String errorType;
    private final String errorUserMessage;
    private final String errorUserTitle;
    private final FacebookException exception;
    private final JSONObject requestResult;
    private final JSONObject requestResultBody;
    private final int requestStatusCode;
    private final boolean shouldNotifyUser;
    private final int subErrorCode;
    private final int userActionMessageId;
    
    static {
        EC_RANGE_PERMISSION = new FacebookRequestError$Range(200, 299, null);
        HTTP_RANGE_SUCCESS = new FacebookRequestError$Range(200, 299, null);
        HTTP_RANGE_CLIENT_ERROR = new FacebookRequestError$Range(400, 499, null);
        HTTP_RANGE_SERVER_ERROR = new FacebookRequestError$Range(500, 599, null);
    }
    
    private FacebookRequestError(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final boolean b, final JSONObject jsonObject, final JSONObject jsonObject2, final Object o, final HttpURLConnection httpURLConnection) {
        this(n, n2, n3, s, s2, s3, s4, b, jsonObject, jsonObject2, o, httpURLConnection, null);
    }
    
    private FacebookRequestError(int n, final int errorCode, final int subErrorCode, final String errorType, final String errorMessage, final String errorUserTitle, final String errorUserMessage, final boolean errorIsTransient, final JSONObject requestResultBody, final JSONObject requestResult, final Object batchRequestResult, final HttpURLConnection connection, final FacebookException exception) {
        this.requestStatusCode = n;
        this.errorCode = errorCode;
        this.subErrorCode = subErrorCode;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.requestResultBody = requestResultBody;
        this.requestResult = requestResult;
        this.batchRequestResult = batchRequestResult;
        this.connection = connection;
        this.errorUserTitle = errorUserTitle;
        this.errorUserMessage = errorUserMessage;
        this.errorIsTransient = errorIsTransient;
        int n2;
        if (exception != null) {
            this.exception = exception;
            n2 = 1;
        }
        else {
            this.exception = new FacebookServiceException(this, errorMessage);
            n2 = 0;
        }
        FacebookRequestError$Category category = null;
        final boolean b = false;
        if (n2 != 0) {
            category = FacebookRequestError$Category.CLIENT;
            n = 0;
        }
        else {
            int n3 = 0;
            Label_0178: {
                if (errorCode == 1 || errorCode == 2) {
                    category = FacebookRequestError$Category.SERVER;
                    n3 = (b ? 1 : 0);
                }
                else if (errorCode == 4 || errorCode == 17) {
                    category = FacebookRequestError$Category.THROTTLING;
                    n3 = (b ? 1 : 0);
                }
                else if (errorCode == 10 || FacebookRequestError.EC_RANGE_PERMISSION.contains(errorCode)) {
                    category = FacebookRequestError$Category.PERMISSION;
                    n3 = R$string.com_facebook_requesterror_permissions;
                }
                else {
                    if (errorCode != 102) {
                        n3 = (b ? 1 : 0);
                        if (errorCode != 190) {
                            break Label_0178;
                        }
                    }
                    if (subErrorCode == 459 || subErrorCode == 464) {
                        category = FacebookRequestError$Category.AUTHENTICATION_RETRY;
                        n3 = R$string.com_facebook_requesterror_web_login;
                    }
                    else {
                        category = FacebookRequestError$Category.AUTHENTICATION_REOPEN_SESSION;
                        if (subErrorCode == 458 || subErrorCode == 463) {
                            n3 = R$string.com_facebook_requesterror_relogin;
                        }
                        else if (subErrorCode == 460) {
                            n3 = R$string.com_facebook_requesterror_password_changed;
                        }
                        else {
                            n3 = R$string.com_facebook_requesterror_reconnect;
                        }
                    }
                }
            }
            if (category == null) {
                if (FacebookRequestError.HTTP_RANGE_CLIENT_ERROR.contains(n)) {
                    category = FacebookRequestError$Category.BAD_REQUEST;
                    n = n3;
                }
                else if (FacebookRequestError.HTTP_RANGE_SERVER_ERROR.contains(n)) {
                    category = FacebookRequestError$Category.SERVER;
                    n = n3;
                }
                else {
                    category = FacebookRequestError$Category.OTHER;
                    n = n3;
                }
            }
            else {
                n = n3;
            }
        }
        final boolean shouldNotifyUser = errorUserMessage != null && errorUserMessage.length() > 0;
        this.category = category;
        this.userActionMessageId = n;
        this.shouldNotifyUser = shouldNotifyUser;
    }
    
    public FacebookRequestError(final int n, final String s, final String s2) {
        this(-1, n, -1, s, s2, null, null, false, null, null, null, null, null);
    }
    
    FacebookRequestError(final HttpURLConnection httpURLConnection, final Exception ex) {
        FacebookException ex2;
        if (ex instanceof FacebookException) {
            ex2 = (FacebookException)ex;
        }
        else {
            ex2 = new FacebookException(ex);
        }
        this(-1, -1, -1, null, null, null, null, false, null, null, null, httpURLConnection, ex2);
    }
    
    static FacebookRequestError checkResponseAndCreateError(final JSONObject jsonObject, final Object o, final HttpURLConnection httpURLConnection) {
        while (true) {
            while (true) {
                Label_0357: {
                    try {
                        if (jsonObject.has("code")) {
                            final int int1 = jsonObject.getInt("code");
                            final Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jsonObject, "body", "FACEBOOK_NON_JSON_RESULT");
                            if (stringPropertyAsJSON != null && stringPropertyAsJSON instanceof JSONObject) {
                                final JSONObject jsonObject2 = (JSONObject)stringPropertyAsJSON;
                                String s = null;
                                String s2 = null;
                                final String s3 = null;
                                final String s4 = null;
                                final boolean b = false;
                                int n = -1;
                                int n2 = -1;
                                int n3 = 0;
                                String optString = null;
                                String optString2 = null;
                                boolean optBoolean = false;
                                Label_0165: {
                                    if (jsonObject2.has("error")) {
                                        final JSONObject jsonObject3 = (JSONObject)Utility.getStringPropertyAsJSON(jsonObject2, "error", null);
                                        s = jsonObject3.optString("type", (String)null);
                                        s2 = jsonObject3.optString("message", (String)null);
                                        n = jsonObject3.optInt("code", -1);
                                        n2 = jsonObject3.optInt("error_subcode", -1);
                                        optString = jsonObject3.optString("error_user_msg", (String)null);
                                        optString2 = jsonObject3.optString("error_user_title", (String)null);
                                        optBoolean = jsonObject3.optBoolean("is_transient", false);
                                        n3 = 1;
                                    }
                                    else {
                                        if (!jsonObject2.has("error_code") && !jsonObject2.has("error_msg")) {
                                            optString2 = s4;
                                            optString = s3;
                                            optBoolean = b;
                                            if (!jsonObject2.has("error_reason")) {
                                                break Label_0165;
                                            }
                                        }
                                        s = jsonObject2.optString("error_reason", (String)null);
                                        s2 = jsonObject2.optString("error_msg", (String)null);
                                        n = jsonObject2.optInt("error_code", -1);
                                        n2 = jsonObject2.optInt("error_subcode", -1);
                                        n3 = 1;
                                        optString2 = s4;
                                        optString = s3;
                                        optBoolean = b;
                                    }
                                }
                                if (n3 != 0) {
                                    return new FacebookRequestError(int1, n, n2, s, s2, optString2, optString, optBoolean, jsonObject2, jsonObject, o, httpURLConnection);
                                }
                            }
                            if (!FacebookRequestError.HTTP_RANGE_SUCCESS.contains(int1)) {
                                if (jsonObject.has("body")) {
                                    final JSONObject jsonObject4 = (JSONObject)Utility.getStringPropertyAsJSON(jsonObject, "body", "FACEBOOK_NON_JSON_RESULT");
                                    return new FacebookRequestError(int1, -1, -1, null, null, null, null, false, jsonObject4, jsonObject, o, httpURLConnection);
                                }
                                break Label_0357;
                            }
                        }
                    }
                    catch (JSONException ex) {}
                    break;
                }
                final JSONObject jsonObject4 = null;
                continue;
            }
        }
        return null;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorMessage() {
        if (this.errorMessage != null) {
            return this.errorMessage;
        }
        return this.exception.getLocalizedMessage();
    }
    
    public String getErrorType() {
        return this.errorType;
    }
    
    public FacebookException getException() {
        return this.exception;
    }
    
    public int getRequestStatusCode() {
        return this.requestStatusCode;
    }
    
    @Override
    public String toString() {
        return "{HttpStatus: " + this.requestStatusCode + ", errorCode: " + this.errorCode + ", errorType: " + this.errorType + ", errorMessage: " + this.getErrorMessage() + "}";
    }
}
