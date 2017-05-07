// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import org.json.JSONObject;

public final class SubmitPaymentEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String ERROR_CODE = "errorCode";
    public static final String PAYMENT_TYPE = "paymentType";
    public static final String SUCCESS = "success";
    private static final String UIA_NAME = "submitPayment";
    private JSONObject mErrorCode;
    private UserActionLogging.PaymentType mPaymentType;
    private boolean mSuccess;
    
    public SubmitPaymentEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView modalView, final UserActionLogging.CommandName commandName, final IClientLogging.CompletionReason completionReason, final UIError uiError, final boolean mSuccess, final UserActionLogging.PaymentType mPaymentType, final JSONObject mErrorCode) {
        super("submitPayment", deviceUniqueId, n, modalView, commandName, completionReason, uiError);
        this.mSuccess = mSuccess;
        this.mPaymentType = mPaymentType;
        this.mErrorCode = mErrorCode;
    }
    
    public SubmitPaymentEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.mPaymentType = UserActionLogging.PaymentType.valueOf(JsonUtils.getString(jsonObject, "paymentType", null));
        this.mSuccess = JsonUtils.getBoolean(jsonObject, "success", false);
        this.mErrorCode = JsonUtils.getJSONObject(jsonObject, "errorCode", null);
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        final JSONObject data = super.getData();
        if (this.mPaymentType != null) {
            data.put("paymentType", (Object)this.mPaymentType.name());
        }
        if (this.mErrorCode != null) {
            data.put("errorCode", (Object)this.mErrorCode);
        }
        data.put("success", this.mSuccess);
        return data;
    }
    
    public JSONObject getErrorCode() {
        return this.mErrorCode;
    }
    
    public UserActionLogging.PaymentType getPaymentType() {
        return this.mPaymentType;
    }
    
    @Override
    public boolean isMemberEvent() {
        return false;
    }
    
    public boolean isSuccess() {
        return this.mSuccess;
    }
}
