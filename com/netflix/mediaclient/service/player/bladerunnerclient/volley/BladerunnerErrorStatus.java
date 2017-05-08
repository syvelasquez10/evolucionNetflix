// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.NumberUtils;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.StatusCode;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.BaseStatus;

public class BladerunnerErrorStatus extends BaseStatus
{
    private static final int BLADERUNNER_UNKNOWN_ERROR = -1;
    private static final int EXCEPTION_LOG_SIZE = 200;
    private static final String TAG = "nf_bladerunner";
    private int mActionId;
    private JSONObject mErrorBlob;
    private int mErrorCode;
    private String mRawErrorCodeString;
    private BladerunnerErrorStatus$BrRequestType mRequestType;
    private String mUserDisplayErrorMessage;
    
    public BladerunnerErrorStatus(final JSONObject jsonObject, final BladerunnerErrorStatus$BrRequestType mRequestType) {
        this.mRequestType = mRequestType;
        this.mErrorBlob = new JSONObject();
        this.mActionId = -1;
        this.mErrorCode = -1;
        if (!hasErrors(jsonObject)) {
            this.mStatusCode = StatusCode.OK;
            return;
        }
        this.mStatusCode = StatusCode.BLADERUNNER_FAILURE;
        this.parseErrorObject(jsonObject);
    }
    
    private void buildErrorBlobForLogging(final JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                this.mErrorBlob.putOpt("bladeRunnerCode", (Object)this.mRawErrorCodeString);
                this.mErrorBlob.putOpt("errorDisplayMessage", (Object)this.mUserDisplayErrorMessage);
                this.mErrorBlob.putOpt("errorActionId", (Object)this.mActionId);
                this.mErrorBlob.putOpt("bladeRunnerExceptionType", (Object)jsonObject.optString("bladeRunnerExceptionType"));
                this.mErrorBlob.putOpt("apkStatusCode", (Object)this.mStatusCode.toString());
                final String optString = jsonObject.optString("bladeRunnerMessage");
                if (StringUtils.isNotEmpty(optString)) {
                    String substring = optString;
                    if (optString.length() > 200) {
                        substring = optString.substring(0, 199);
                    }
                    this.mErrorBlob.putOpt("bladeRunnerMessage", (Object)substring);
                }
            }
            catch (JSONException ex) {
                Log.e("nf_bladerunner", "error creating logging blob");
            }
        }
    }
    
    public static boolean hasErrors(final JSONObject jsonObject) {
        return jsonObject == null || jsonObject.has("error") || jsonObject.has("innerErrors");
    }
    
    public static boolean hasLinksInPayload(final JSONObject jsonObject) {
        return jsonObject != null && jsonObject.has("links");
    }
    
    private static StatusCode mapBladeRunnerErrorCodeToStatusCode(final int n) {
        Log.i("nf_bladerunner", "mapBladeRunnerErrorCodeToStatusCode bladeRunnerErrorCode=%d", n);
        final int value = StatusCode.BLADERUNNER_FAILURE.getValue();
        final LaseOfflineError byValue = LaseOfflineError.getByValue(n);
        int n2 = value;
        Label_0178: {
            switch (BladerunnerErrorStatus$1.$SwitchMap$com$netflix$mediaclient$service$player$bladerunnerclient$volley$LaseOfflineError[byValue.ordinal()]) {
                default: {
                    n2 = value;
                    break Label_0178;
                }
                case 23: {
                    n2 = StatusCode.DL_LIMIT_TOO_MANY_DEVICES_PLAN_OPTION.getValue();
                    break Label_0178;
                }
                case 11:
                case 12: {
                    n2 = StatusCode.DL_LIMIT_CANT_DOWNLOAD_TILL_DATE.getValue();
                    break Label_0178;
                }
                case 10: {
                    n2 = StatusCode.DL_LIMIT_TOO_MANY_DOWNLOADED_DELETE_SOME.getValue();
                }
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30: {
                    Log.i("nf_bladerunner", "mapBladeRunnerErrorCodeToStatusCode returnErrorCode=%d", n2);
                    if (n2 == StatusCode.BLADERUNNER_FAILURE.getValue()) {
                        Log.e("nf_bladerunner", "unmapped error code :%d", n);
                    }
                    StatusCode statusCode;
                    if ((statusCode = StatusCode.getStatusCodeByValue(n2)) == null) {
                        statusCode = StatusCode.BLADERUNNER_FAILURE;
                    }
                    return statusCode;
                }
            }
        }
    }
    
    private void parseErrorObject(final JSONObject jsonObject) {
        if (jsonObject != null) {
            final JSONObject optJSONObject = jsonObject.optJSONObject("error");
            if (optJSONObject != null && optJSONObject.has("bladeRunnerCode")) {
                this.mRawErrorCodeString = optJSONObject.optString("bladeRunnerCode", (String)null);
                this.mErrorCode = NumberUtils.toIntegerSafely(this.mRawErrorCodeString, -1);
                if (this.mErrorCode == -1) {
                    this.mStatusCode = StatusCode.BLADERUNNER_FAILURE;
                    this.mErrorCode = StatusCode.BLADERUNNER_FAILURE.getValue();
                }
                else {
                    final ClientActionFromLase create = ClientActionFromLase.create(optJSONObject.optInt("clientAction", ClientActionFromLase.NO_ACTION.getValue()));
                    StatusCode mStatusCode;
                    if (create.isRecoverable()) {
                        mStatusCode = create.getStatusCode();
                    }
                    else {
                        mStatusCode = mapBladeRunnerErrorCodeToStatusCode(this.mErrorCode);
                    }
                    this.mStatusCode = mStatusCode;
                }
                this.mUserDisplayErrorMessage = optJSONObject.optString("errorDisplayMessage");
                this.mActionId = optJSONObject.optInt("errorActionId");
                Log.e("nf_bladerunner", "mStatusCode: %s, mUserDisplayErrorMessage:%s, actionId:%d", this.mStatusCode, this.mUserDisplayErrorMessage, this.mActionId);
                this.buildErrorBlobForLogging(optJSONObject);
            }
        }
    }
    
    @Override
    public Error getError() {
        return null;
    }
    
    public String getErrorCodeForLogging() {
        return "BR" + this.mRequestType.getValue() + "." + this.mRawErrorCodeString;
    }
    
    public String getErrorMessageForLogging() {
        return this.mErrorBlob.toString();
    }
    
    @Override
    public String getMessage() {
        final StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(this.mUserDisplayErrorMessage)) {
            sb.append(this.mUserDisplayErrorMessage);
        }
        if (this.mErrorCode != -1) {
            sb.append(" (").append(this.getErrorCodeForLogging()).append(")");
        }
        return sb.toString();
    }
    
    @Override
    public int getRequestId() {
        return 0;
    }
    
    @Override
    public boolean shouldDisplayMessage() {
        return StringUtils.isNotEmpty(this.mUserDisplayErrorMessage);
    }
    
    @Override
    public String toString() {
        return "BladerunnerErrorStatus, " + this.mStatusCode;
    }
}
