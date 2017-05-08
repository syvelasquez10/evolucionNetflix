// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup.react.manager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactMethod;
import com.netflix.mediaclient.partner.playbilling.PlayBillingCallback;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.signup.ReactSignupActivity;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import com.netflix.mediaclient.partner.playbilling.PlayBilling;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class PaymentManager extends ReactContextBaseJavaModule
{
    private static final String TAG = "PaymentManager";
    private ReactApplicationContext mContext;
    
    public PaymentManager(final ReactApplicationContext mContext) {
        super(mContext);
        this.mContext = mContext;
    }
    
    private boolean canProceedWithPlayBilling(final PlayBilling playBilling) {
        return playBilling != null && playBilling.isPlayBillingReady();
    }
    
    @ReactMethod
    public void fetchPrices(final ReadableArray readableArray, final Promise promise) {
        final PlayBilling playBilling = ((ReactSignupActivity)this.mContext.getCurrentActivity()).getPlayBilling();
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < readableArray.size(); ++i) {
            list.add(readableArray.getString(i));
        }
        if (!this.canProceedWithPlayBilling(playBilling)) {
            promise.resolve(null);
            return;
        }
        playBilling.getSkuDetails(list, (PlayBillingCallback)new PaymentManager$1(this, promise));
    }
    
    @Override
    public String getName() {
        return "PaymentManager";
    }
    
    @ReactMethod
    public void restoreSubscription(final Promise promise) {
        promise.resolve(Arguments.createMap());
    }
}
