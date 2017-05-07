// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.reference;

import java.util.Iterator;
import android.os.Bundle;
import com.netflix.mediaclient.partner.ExternalUserConfirmationSuccess;
import com.netflix.mediaclient.partner.ExternalUserDataSuccess;
import com.netflix.mediaclient.partner.BaseResponse;
import com.netflix.mediaclient.partner.SsoNoUser;
import com.netflix.mediaclient.partner.SsoUserCancel;
import org.json.JSONObject;
import com.netflix.mediaclient.partner.SsoSuccess;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.partner.SignupFailure;
import com.netflix.mediaclient.partner.SsoFailure;
import com.netflix.mediaclient.partner.PartnerRequestType;
import com.netflix.mediaclient.partner.Response;
import android.content.ComponentName;
import com.netflix.mediaclient.partner.PartnerRequest;

public final class ResponseFactory
{
    public static final String ERROR_CODE_COMPONENT_NULL = "100";
    public static final String ERROR_CODE_RECEPIENT_UKNOWN = "102";
    public static final String ERROR_CODE_SERVICE_MISMATCH = "101";
    public static final String ERROR_CODE_UKNOWN_RESULT_CODE = "103";
    public static final String JSPI_billing_zip = "billingzip";
    public static final String JSPI_email = "email";
    public static final String JSPI_first_name = "firstname";
    public static final String JSPI_last_name = "lastname";
    public static final String JSPI_payments = "payments";
    public static final String RESPONSE_billing_zip = "billing_zip";
    public static final String RESPONSE_client = "client";
    public static final String RESPONSE_confirmed = "confirmed";
    public static final String RESPONSE_email = "email";
    public static final String RESPONSE_error_code = "error_code";
    public static final String RESPONSE_error_msg = "error_msg";
    public static final String RESPONSE_external_user_token = "external_user_token";
    public static final String RESPONSE_first_name = "first_name";
    public static final String RESPONSE_internal_token = "internal_token";
    public static final String RESPONSE_last_name = "last_name";
    public static final String RESPONSE_payments = "payments";
    public static final String RESPONSE_token = "token";
    public static final String RESPONSE_user_id = "user_id";
    public static final int RESULT_ERROR = 102;
    public static final int RESULT_NONE = 101;
    private static final String TAG = "nf_partner";
    
    private static Response createErrorResponse(final PartnerRequest partnerRequest, final String s, final String s2, final ComponentName componentName) {
        if (PartnerRequestType.iSso(partnerRequest.getRequestType())) {
            return new SsoFailure(partnerRequest.getService(), String.valueOf(partnerRequest.getIdx()), s, s2, componentName);
        }
        if (PartnerRequestType.iSignup(partnerRequest.getRequestType())) {
            return new SignupFailure(partnerRequest.getService(), String.valueOf(partnerRequest.getIdx()), partnerRequest.getUserId(), s, s2, componentName);
        }
        Log.e("nf_partner", "Uknown request type " + partnerRequest.getRequestType());
        return new SsoFailure(partnerRequest.getService(), String.valueOf(partnerRequest.getIdx()), s, s2, componentName);
    }
    
    public static Response createErrorResponseForPartnerCommunicationHandleIsNull(final PartnerRequest partnerRequest) {
        return createErrorResponse(partnerRequest, "100", "", null);
    }
    
    public static Response createErrorResponseForRecepientUknown(final PartnerRequest partnerRequest, final String s) {
        return createErrorResponse(partnerRequest, "102", "", null);
    }
    
    public static Response createErrorResponseForServiceMismatch(final PartnerRequest partnerRequest, final String s) {
        return createErrorResponse(partnerRequest, "101", "", null);
    }
    
    private static Response createGetExternalSignUpServiceResponse(final PartnerRequest partnerRequest, final Intent intent, final int n) {
        Log.d("nf_partner", "createGetExternalSignUpServiceResponse start");
        if (n == -1) {
            return new SsoSuccess(partnerRequest.getService(), partnerRequest.getId(), partnerRequest.getUserId(), null, intent.getComponent());
        }
        if (n == 0) {
            return new SsoUserCancel(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 101) {
            return new SsoNoUser(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 102) {
            return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("error_code")), BaseResponse.noNull(intent.getStringExtra("error_msg")), intent.getComponent());
        }
        Log.e("nf_partner", "Uknown result code, report error");
        return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), "103", "", intent.getComponent());
    }
    
    private static Response createGetExternalSsoServiceResponse(final PartnerRequest partnerRequest, final Intent intent, final int n) {
        Log.d("nf_partner", "createGetExternalSsoServiceResponse start");
        if (n == -1) {
            return new SsoSuccess(partnerRequest.getService(), partnerRequest.getId(), partnerRequest.getUserId(), null, intent.getComponent());
        }
        if (n == 0) {
            return new SsoUserCancel(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 101) {
            return new SsoNoUser(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 102) {
            return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("error_code")), BaseResponse.noNull(intent.getStringExtra("error_msg")), intent.getComponent());
        }
        Log.e("nf_partner", "Uknown result code, report error");
        return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), "103", "", intent.getComponent());
    }
    
    private static Response createGetExternalUserDataResponse(final PartnerRequest partnerRequest, final Intent intent, final int n) {
        Log.d("nf_partner", "createGetExternalUserDataResponse start");
        if (n == -1) {
            final String noNull = BaseResponse.noNull(intent.getStringExtra("user_id"));
            final String noNull2 = BaseResponse.noNull(intent.getStringExtra("first_name"));
            final String noNull3 = BaseResponse.noNull(intent.getStringExtra("last_name"));
            final String noNull4 = BaseResponse.noNull(intent.getStringExtra("email"));
            final String noNull5 = BaseResponse.noNull(intent.getStringExtra("billing_zip"));
            final String noNull6 = BaseResponse.noNull(intent.getStringExtra("payments"));
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("firstname", (Object)noNull2);
                jsonObject.put("lastname", (Object)noNull3);
                jsonObject.put("email", (Object)noNull4);
                jsonObject.put("billingzip", (Object)noNull5);
                jsonObject.put("payments", (Object)noNull6);
                return new ExternalUserDataSuccess(partnerRequest.getService(), partnerRequest.getId(), noNull, jsonObject, intent.getComponent());
            }
            catch (Exception ex) {
                Log.e("nf_partner", "JSON failed", ex);
                return new ExternalUserDataSuccess(partnerRequest.getService(), partnerRequest.getId(), noNull, jsonObject, intent.getComponent());
            }
        }
        if (n == 0) {
            return new SsoUserCancel(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 101) {
            return new SsoNoUser(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 102) {
            return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("error_code")), BaseResponse.noNull(intent.getStringExtra("error_msg")), intent.getComponent());
        }
        Log.e("nf_partner", "Uknown result code, report error");
        return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), "103", "", intent.getComponent());
    }
    
    private static Response createGetExternalUserTokenResponse(final PartnerRequest partnerRequest, final Intent intent, final int n) {
        Log.d("nf_partner", "createGetExternalUserTokenResponse start");
        if (n == -1) {
            return new SsoSuccess(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("user_id")), BaseResponse.toJSon(intent.getStringExtra("external_user_token")), intent.getComponent());
        }
        if (n == 0) {
            return new SsoUserCancel(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 101) {
            return new SsoNoUser(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 102) {
            return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("error_code")), BaseResponse.noNull(intent.getStringExtra("error_msg")), intent.getComponent());
        }
        Log.e("nf_partner", "Uknown result code, report error");
        return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), "103", "", intent.getComponent());
    }
    
    private static Response createRequestExternalUserAuthResponse(final PartnerRequest partnerRequest, final Intent intent, final int n) {
        Log.d("nf_partner", "createRequestExternalUserAuthResponse start");
        if (n == -1) {
            return new SsoSuccess(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("user_id")), BaseResponse.toJSon(intent.getStringExtra("external_user_token")), intent.getComponent());
        }
        if (n == 0) {
            return new SsoUserCancel(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 102) {
            return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("error_code")), BaseResponse.noNull(intent.getStringExtra("error_msg")), intent.getComponent());
        }
        Log.e("nf_partner", "Uknown result code, report error");
        return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), "103", "", intent.getComponent());
    }
    
    private static Response createRequestExternalUserConfirmationResponse(final PartnerRequest partnerRequest, final Intent intent, final int n) {
        Log.d("nf_partner", "createRequestExternalUserConfirmationResponse start");
        if (n == -1) {
            return new ExternalUserConfirmationSuccess(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("user_id")), intent.getBooleanExtra("confirmed", false), BaseResponse.toJSon(intent.getStringExtra("token")), intent.getComponent());
        }
        if (n == 0) {
            return new SsoUserCancel(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 101) {
            return new SsoNoUser(partnerRequest.getService(), partnerRequest.getId(), intent.getComponent());
        }
        if (n == 102) {
            return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), BaseResponse.noNull(intent.getStringExtra("error_code")), BaseResponse.noNull(intent.getStringExtra("error_msg")), intent.getComponent());
        }
        Log.e("nf_partner", "Uknown result code, report error");
        return new SsoFailure(partnerRequest.getService(), partnerRequest.getId(), "103", "", intent.getComponent());
    }
    
    public static Response createResponse(final PartnerRequest partnerRequest, final Intent intent, final int n) {
        if (Log.isLoggable("nf_partner", 3)) {
            Log.d("nf_partner", "createResponse:: request " + partnerRequest + ", response " + intent + ", resultCode " + n);
            if (intent != null) {
                final Bundle extras = intent.getExtras();
                if (extras != null) {
                    for (final String s : extras.keySet()) {
                        if ("confirmed".equals(s)) {
                            Log.d("nf_partner", "extras for key " + s + " is " + extras.getBoolean(s));
                        }
                        else {
                            Log.d("nf_partner", "extras for key " + s + " is " + extras.getString(s));
                        }
                    }
                }
            }
        }
        if (PartnerRequestType.getExternalSignUpService.equals(partnerRequest.getRequestType())) {
            return createGetExternalSignUpServiceResponse(partnerRequest, intent, n);
        }
        if (PartnerRequestType.getExternalSsoService.equals(partnerRequest.getRequestType())) {
            return createGetExternalSsoServiceResponse(partnerRequest, intent, n);
        }
        if (PartnerRequestType.getExternalUserData.equals(partnerRequest.getRequestType())) {
            return createGetExternalUserDataResponse(partnerRequest, intent, n);
        }
        if (PartnerRequestType.getExternalUserToken.equals(partnerRequest.getRequestType())) {
            return createGetExternalUserTokenResponse(partnerRequest, intent, n);
        }
        if (PartnerRequestType.requestExternalUserAuth.equals(partnerRequest.getRequestType())) {
            return createRequestExternalUserAuthResponse(partnerRequest, intent, n);
        }
        if (PartnerRequestType.requestExternalUserConfirmation.equals(partnerRequest.getRequestType())) {
            return createRequestExternalUserConfirmationResponse(partnerRequest, intent, n);
        }
        Log.e("nf_partner", "Uknown request type " + partnerRequest.getRequestType());
        return null;
    }
}
