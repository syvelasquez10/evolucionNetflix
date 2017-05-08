// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.playbilling;

import com.netflix.mediaclient.android.app.BackgroundTask;
import android.app.PendingIntent;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.Bundle;
import com.netflix.mediaclient.util.Base64;
import android.content.Intent;
import java.util.ArrayList;
import org.json.JSONObject;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.content.ServiceConnection;
import com.android.vending.billing.IInAppBillingService;
import android.os.Handler;
import android.content.Context;
import java.util.List;

public class PlayBilling
{
    public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
    public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int BILLING_RESPONSE_RESULT_OK = 0;
    public static final int BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE = 2;
    public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    private static final String BUNDLE_EXTRA_ACCOUNT_ID = "accountId";
    private static final String BUNDLE_EXTRA_TRIAL_PERIOD = "trialPeriod";
    public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    public static final int IABHELPER_ERROR_BASE = -1000;
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBS = "subs";
    private static final String NFLX_KEY_RAW_RESPONSE = "rawData";
    public static final boolean PLAY_BILLING_ENABLED_IN_CODE = true;
    public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";
    private static final String RESPONSE_DETAILS_LIST = "DETAILS_LIST";
    private static final String RESPONSE_INAPP_DEVELOPER_PAYLOAD = "developerPayload";
    public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    private static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    private static final String RESPONSE_INAPP_PURCHASE_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
    public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
    private static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
    private static final List RESPONSE_LIST_PARAMS;
    private static final List RESPONSE_LIST_STRING_PARAMS;
    private static final String TAG = "playBilling";
    volatile boolean mAsyncInProgress;
    String mAsyncOperation;
    Context mContext;
    volatile boolean mDisposeAfterAsync;
    boolean mDisposed;
    Handler mHandler;
    PlayBillingCallback mPurchaseListener;
    String mPurchasingItemType;
    int mRequestCode;
    IInAppBillingService mService;
    ServiceConnection mServiceConn;
    boolean mSetupDone;
    boolean mSubscriptionUpdateSupported;
    boolean mSubscriptionsSupported;
    
    static {
        RESPONSE_LIST_PARAMS = new PlayBilling$1();
        RESPONSE_LIST_STRING_PARAMS = new PlayBilling$2();
    }
    
    public PlayBilling(final Context context, final Handler mHandler) {
        this.mSetupDone = false;
        this.mDisposed = false;
        this.mDisposeAfterAsync = false;
        this.mSubscriptionsSupported = false;
        this.mSubscriptionUpdateSupported = false;
        this.mAsyncInProgress = false;
        this.mAsyncOperation = "";
        this.mContext = context.getApplicationContext();
        this.mHandler = mHandler;
        Log.d("playBilling", "IAB helper created.");
    }
    
    private boolean canBindWithService(final Intent intent) {
        boolean b2;
        final boolean b = b2 = false;
        if (this.mContext != null) {
            b2 = b;
            if (this.mContext.getPackageManager() != null) {
                b2 = b;
                if (this.mContext.getPackageManager().queryIntentServices(intent, 0) != null) {
                    b2 = b;
                    if (!this.mContext.getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    private boolean canProceedWithRequest() {
        this.checkNotDisposed();
        final boolean b = this.mSubscriptionsSupported && !this.mAsyncInProgress;
        if (!b) {
            Log.e("playBilling", "getSkuDetails mSubscriptionsSupported is false or mAsyncInProgress is true");
        }
        return b;
    }
    
    private void checkNotDisposed() {
        if (this.mDisposed) {
            throw new IllegalStateException("PlayBilling was disposed of, so it cannot be used.");
        }
    }
    
    private String getBase64EncodedString(String encodeBytes) {
        try {
            encodeBytes = Base64.encodeBytes(encodeBytes.getBytes());
            return encodeBytes;
        }
        catch (Exception ex) {
            Log.e("playBilling", "error encoding to base64", ex);
            return null;
        }
    }
    
    private JSONObject getJsonObjFromBundle(final Bundle bundle) {
        if (bundle == null) {
            Log.d("playBilling", "bundle is null");
            return null;
        }
        final JSONObject jsonObject = new JSONObject();
        final Iterator<String> iterator = (Iterator<String>)bundle.keySet().iterator();
        String string;
        String s;
        Object value = null;
        ArrayList stringArrayList = null;
        Object wrap;
        JSONArray jsonArray;
        Iterator<String> iterator2;
        JSONObject sanitizePurchaseData;
        Label_0105_Outer:Label_0176_Outer:
        while (true) {
            while (true) {
                Label_0376: {
                    if (!iterator.hasNext()) {
                        while (true) {
                            try {
                                jsonObject.put("rawData", (Object)this.getBase64EncodedString(bundle.toString()));
                                string = jsonObject.toString();
                                if (Log.isLoggable()) {
                                    Log.d("playBilling", "raw Bundle - " + bundle);
                                    Log.d("playBilling", "result fromBundle - " + string);
                                }
                                return jsonObject;
                            }
                            catch (JSONException ex) {
                                Log.e("playBilling", "error adding raw message", (Throwable)ex);
                                continue Label_0105_Outer;
                            }
                            break;
                        }
                        break Label_0376;
                    }
                    s = iterator.next();
                Label_0176:
                    while (true) {
                        while (true) {
                            Label_0229: {
                                Label_0162: {
                                    try {
                                        value = bundle.get(s);
                                        if (PlayBilling.RESPONSE_LIST_PARAMS.contains(s)) {
                                            stringArrayList = bundle.getStringArrayList(s);
                                            if (!PlayBilling.RESPONSE_LIST_STRING_PARAMS.contains(s)) {
                                                break Label_0162;
                                            }
                                            value = new JSONArray((Collection)stringArrayList);
                                        }
                                        wrap = JSONObject.wrap(value);
                                        if (wrap == null) {
                                            break Label_0229;
                                        }
                                        jsonObject.put(s, wrap);
                                    }
                                    catch (JSONException ex2) {
                                        if (!Log.isLoggable()) {
                                            continue Label_0105_Outer;
                                        }
                                        Log.d("playBilling", "failed in converting bundle. e:" + ex2);
                                    }
                                    continue Label_0105_Outer;
                                }
                                jsonArray = new JSONArray();
                                iterator2 = stringArrayList.iterator();
                                break Label_0176;
                            }
                            if (Log.isLoggable()) {
                                Log.d("playBilling", String.format("wrapping failed for key: %s, obj: %s", s, value));
                            }
                            if (value != null) {
                                jsonObject.put(s, (Object)value.toString());
                                continue Label_0105_Outer;
                            }
                            continue Label_0105_Outer;
                            if (!iterator2.hasNext()) {
                                value = jsonArray;
                                continue Label_0176_Outer;
                            }
                            break;
                        }
                        sanitizePurchaseData = new JSONObject((String)iterator2.next());
                        if ("INAPP_PURCHASE_DATA_LIST".equals(s)) {
                            sanitizePurchaseData = this.sanitizePurchaseData(sanitizePurchaseData);
                        }
                        jsonArray.put((Object)sanitizePurchaseData);
                        continue Label_0176;
                    }
                }
                continue;
            }
        }
    }
    
    private JSONObject getPurchaseHistoryFromPlayBilling(final String s) {
        if (Log.isLoggable()) {
            Log.d("playBilling", "fetching purchase history");
        }
        try {
            final Bundle bundle = this.mService.getPurchaseHistory(6, this.mContext.getPackageName(), "subs", s, null);
            return this.getJsonObjFromBundle(bundle);
        }
        catch (Exception ex) {
            Log.d("playBilling", "getPurchaseHistoryFromPlayBilling failed", ex);
            final Bundle bundle = this.getErrorBundle();
            return this.getJsonObjFromBundle(bundle);
        }
    }
    
    private JSONObject getPurchasesFromPlayBilling(final String s) {
        if (Log.isLoggable()) {
            Log.d("playBilling", "fetching purchases");
        }
        try {
            final Bundle bundle = this.mService.getPurchases(3, this.mContext.getPackageName(), "subs", s);
            return this.getJsonObjFromBundle(bundle);
        }
        catch (Exception ex) {
            Log.d("playBilling", "getPurchasesFromPlayBilling failed", ex);
            final Bundle bundle = this.getErrorBundle();
            return this.getJsonObjFromBundle(bundle);
        }
    }
    
    public static String getResponseDesc(final int n) {
        final String[] split = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        final String[] split2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (n <= -1000) {
            final int n2 = -1000 - n;
            if (n2 >= 0 && n2 < split2.length) {
                return split2[n2];
            }
            return String.valueOf(n) + ":Unknown IAB Helper Error";
        }
        else {
            if (n < 0 || n >= split.length) {
                return String.valueOf(n) + ":Unknown";
            }
            return split[n];
        }
    }
    
    private JSONObject getSkuDetailsFromPlayBilling(final ArrayList<String> list) {
        if (Log.isLoggable()) {
            Log.d("playBilling", "fetching skuDetails skus:" + list);
        }
        if (list == null || list.size() > 20) {
            if (Log.isLoggable()) {
                Log.e("playBilling", "bad skus: " + list);
            }
            return null;
        }
        final Bundle bundle = new Bundle();
        bundle.putStringArrayList("ITEM_ID_LIST", (ArrayList)list);
        Log.d("playBilling", "getSkuDetailsFromPlayBilling bundle:" + bundle);
        try {
            final Bundle bundle2 = this.mService.getSkuDetails(5, this.mContext.getPackageName(), "subs", bundle);
            return this.getJsonObjFromBundle(bundle2);
        }
        catch (Exception ex) {
            Log.d("playBilling", "getSkuDetailsFromPlayBilling failed", ex);
            final Bundle bundle2 = this.getErrorBundle();
            return this.getJsonObjFromBundle(bundle2);
        }
    }
    
    private JSONObject initiatePurchasePlayBilling(final NetflixActivity netflixActivity, final String s, final String s2, int responseCodeFromBundle, final String s3, final int mRequestCode) {
        if (Log.isLoggable()) {
            Log.d("playBilling", String.format("initiatePurchase sku:%s, payload:%s, trialPeriod:%d, accountId:%s", s, s2, responseCodeFromBundle, s3));
        }
        try {
            final Bundle bundle = new Bundle();
            bundle.putInt("trialPeriod", responseCodeFromBundle);
            if (StringUtils.isNotEmpty(s3)) {
                bundle.putString("accountId", s3);
            }
            Log.d("playBilling", String.format("extras :%s", bundle));
            final Bundle buyIntentExtraParams = this.mService.getBuyIntentExtraParams(6, this.mContext.getPackageName(), s, "subs", s2, bundle);
            responseCodeFromBundle = this.getResponseCodeFromBundle(buyIntentExtraParams);
            if (responseCodeFromBundle != 0) {
                Log.e("playBilling", "Unable to buy item, Error response: " + getResponseDesc(responseCodeFromBundle));
                return this.getJsonObjFromBundle(buyIntentExtraParams);
            }
            final PendingIntent pendingIntent = (PendingIntent)buyIntentExtraParams.getParcelable("BUY_INTENT");
            Log.d("playBilling", "Launching buy intent for " + s + ". Request code: " + mRequestCode);
            this.mRequestCode = mRequestCode;
            this.mPurchasingItemType = "subs";
            netflixActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), mRequestCode, new Intent(), (int)Integer.valueOf(0), (int)Integer.valueOf(0), (int)Integer.valueOf(0));
            return null;
        }
        catch (Exception ex) {
            Log.d("playBilling", "getPurchasesFromPlayBilling failed", ex);
            return this.getJsonObjFromBundle(this.getErrorBundle());
        }
    }
    
    private JSONObject sanitizePurchaseData(final JSONObject jsonObject) {
        if (jsonObject.has("developerPayload")) {
            if (Log.isLoggable()) {
                Log.d("playBilling", String.format("sanitizePurchaseData bf4: %s", jsonObject));
            }
            while (true) {
                try {
                    final String string = jsonObject.getString("developerPayload");
                    if (StringUtils.isNotEmpty(string) && !StringUtils.safeEquals(string, "undefined")) {
                        jsonObject.put("developerPayload", (Object)new JSONObject(string));
                    }
                    if (Log.isLoggable()) {
                        Log.d("playBilling", String.format("sanitizePurchaseData aftr: %s", jsonObject));
                        return jsonObject;
                    }
                }
                catch (Exception ex) {
                    Log.e("playBilling", "error sanitizing payment data", ex);
                    continue;
                }
                break;
            }
        }
        return jsonObject;
    }
    
    private void sendToCallback(final PlayBillingCallback playBillingCallback, final JSONObject jsonObject) {
        if (!this.mDisposed && playBillingCallback != null) {
            this.mHandler.post((Runnable)new PlayBilling$8(this, playBillingCallback, jsonObject));
        }
    }
    
    void checkSetupDone(final String s) {
        if (!this.mSetupDone) {
            Log.e("playBilling", "Illegal state for operation (" + s + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + s);
        }
    }
    
    public void dispose() {
        if (this.mAsyncInProgress) {
            Log.d("playBilling", "Will dispose after async operation finishes.");
            this.mDisposeAfterAsync = true;
            return;
        }
        Log.d("playBilling", "Disposing.");
        this.mSetupDone = false;
        while (true) {
            if (this.mServiceConn == null) {
                break Label_0070;
            }
            Log.d("playBilling", "Unbinding from service.");
            if (this.mContext == null) {
                break Label_0070;
            }
            try {
                this.mContext.unbindService(this.mServiceConn);
                this.mDisposed = true;
                this.mContext = null;
                this.mServiceConn = null;
                this.mService = null;
                this.mPurchaseListener = null;
            }
            catch (Exception ex) {
                if (Log.isLoggable()) {
                    Log.e("playBilling", "unbinding playBilling faile", ex);
                }
                continue;
            }
            break;
        }
    }
    
    void flagEndAsync() {
        Log.d("playBilling", "Ending async operation: " + this.mAsyncOperation);
        this.mAsyncOperation = "";
        this.mAsyncInProgress = false;
        if (this.mDisposeAfterAsync) {
            this.dispose();
        }
    }
    
    void flagStartAsync(final String mAsyncOperation) {
        if (this.mAsyncInProgress && Log.isLoggable()) {
            throw new IllegalStateException("Can't start async operation (" + mAsyncOperation + ") because another async operation(" + this.mAsyncOperation + ") is in progress.");
        }
        this.mAsyncOperation = mAsyncOperation;
        this.mAsyncInProgress = true;
        Log.d("playBilling", "Starting async operation: " + mAsyncOperation);
    }
    
    Bundle getErrorBundle() {
        final Bundle bundle = new Bundle();
        bundle.putInt("RESPONSE_CODE", 5);
        return bundle;
    }
    
    public void getPurchaseHistory(final String s, final PlayBillingCallback playBillingCallback) {
        if (!this.canProceedWithRequest()) {
            playBillingCallback.onResult(null);
            return;
        }
        this.flagStartAsync("locking for getPurchaseHistory");
        new BackgroundTask().execute(new PlayBilling$5(this, s, playBillingCallback));
    }
    
    public void getPurchases(final String s, final PlayBillingCallback playBillingCallback) {
        if (!this.canProceedWithRequest()) {
            playBillingCallback.onResult(null);
            return;
        }
        this.flagStartAsync("locking for getPurchases");
        new BackgroundTask().execute(new PlayBilling$6(this, s, playBillingCallback));
    }
    
    int getResponseCodeFromBundle(final Bundle bundle) {
        final Object value = bundle.get("RESPONSE_CODE");
        if (value == null) {
            Log.d("playBilling", "Bundle with null response code, assuming OK (known issue)");
            return 0;
        }
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof Long) {
            return (int)(long)value;
        }
        Log.e("playBilling", "Unexpected type for bundle response code.");
        Log.e("playBilling", ((Long)value).getClass().getName());
        throw new RuntimeException("Unexpected type for bundle response code: " + ((Long)value).getClass().getName());
    }
    
    public void getSkuDetails(final ArrayList<String> list, final PlayBillingCallback playBillingCallback) {
        if (!this.canProceedWithRequest()) {
            playBillingCallback.onResult(null);
            return;
        }
        this.flagStartAsync("locking for getSkuDetails");
        new BackgroundTask().execute(new PlayBilling$7(this, list, playBillingCallback));
    }
    
    public void handleActivityResult(int intExtra, final int n, Intent intent) {
        final int n2 = 0;
        if (intExtra != this.mRequestCode) {
            return;
        }
        this.checkNotDisposed();
        this.checkSetupDone("handleActivityResult");
        if (this.mPurchaseListener == null) {
            Log.e("playBilling", "purchase listerner null");
            return;
        }
        final PlayBillingCallback mPurchaseListener = this.mPurchaseListener;
        this.flagEndAsync();
        if (intent == null) {
            Log.e("playBilling", "Null data in IAB activity result.");
            mPurchaseListener.onResult(this.getJsonObjFromBundle(this.getErrorBundle()));
            return;
        }
        final String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
        final String stringExtra2 = intent.getStringExtra("INAPP_DATA_SIGNATURE");
        intExtra = intent.getIntExtra("RESPONSE_CODE", 0);
        if (Log.isLoggable()) {
            Log.d("playBilling", String.format("data.extra: %s", intent.getExtras()));
            Log.d("playBilling", String.format("handleActivityResult purchaseData:%s, dataSignature:%s", stringExtra, stringExtra2));
        }
        intent = (Intent)new JSONObject();
        Label_0224: {
            if (n != -1) {
                break Label_0224;
            }
            intExtra = n2;
            while (true) {
                try {
                    while (true) {
                        ((JSONObject)intent).put("RESPONSE_CODE", intExtra);
                        if (StringUtils.isNotEmpty(stringExtra)) {
                            ((JSONObject)intent).put("INAPP_PURCHASE_DATA", (Object)this.sanitizePurchaseData(new JSONObject(stringExtra)));
                        }
                        ((JSONObject)intent).put("INAPP_DATA_SIGNATURE", (Object)stringExtra2);
                        mPurchaseListener.onResult((JSONObject)intent);
                        return;
                        continue;
                    }
                }
                catch (Exception ex) {
                    Log.e("playBilling", "error processing payment data", ex);
                    continue;
                }
                break;
            }
        }
    }
    
    public boolean isPlayBillingReady() {
        if (Log.isLoggable()) {
            Log.d("playBilling", String.format("playBillingReady? %b, mSetupDone:%b, mSubscriptionsSupported:%b, mSubscriptionUpdateSupported: %b", this.mSetupDone && this.mSubscriptionsSupported && this.mSubscriptionUpdateSupported, this.mSetupDone, this.mSubscriptionsSupported, this.mSubscriptionUpdateSupported));
        }
        return this.mSetupDone && this.mSubscriptionsSupported && this.mSubscriptionUpdateSupported;
    }
    
    public void purchase(final NetflixActivity netflixActivity, final String s, final String s2, final int n, final String s3, final int n2, final PlayBillingCallback playBillingCallback) {
        if (!this.canProceedWithRequest()) {
            playBillingCallback.onResult(null);
            return;
        }
        this.flagStartAsync("locking for purchase");
        new BackgroundTask().execute(new PlayBilling$4(this, playBillingCallback, netflixActivity, s, s2, n, s3, n2));
    }
    
    public void startSetup(final PlayBilling$OnSetupFinishedListener playBilling$OnSetupFinishedListener) {
        this.checkNotDisposed();
        if (this.mSetupDone) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        Log.d("playBilling", "Starting play billing setup.");
        this.mServiceConn = (ServiceConnection)new PlayBilling$3(this, playBilling$OnSetupFinishedListener);
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        if (this.canBindWithService(intent)) {
            this.mContext.bindService(intent, this.mServiceConn, 1);
        }
        else if (playBilling$OnSetupFinishedListener != null) {
            playBilling$OnSetupFinishedListener.onSetupFinished(false);
        }
    }
}
