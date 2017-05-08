// 
// Decompiled by Procyon v0.5.30
// 

package com.android.vending.billing;

import java.util.List;
import android.os.Bundle;
import android.os.IInterface;

public interface IInAppBillingService extends IInterface
{
    int consumePurchase(final int p0, final String p1, final String p2);
    
    Bundle getBuyIntent(final int p0, final String p1, final String p2, final String p3, final String p4);
    
    Bundle getBuyIntentExtraParams(final int p0, final String p1, final String p2, final String p3, final String p4, final Bundle p5);
    
    Bundle getBuyIntentToReplaceSkus(final int p0, final String p1, final List<String> p2, final String p3, final String p4, final String p5);
    
    Bundle getPurchaseHistory(final int p0, final String p1, final String p2, final String p3, final Bundle p4);
    
    Bundle getPurchases(final int p0, final String p1, final String p2, final String p3);
    
    Bundle getSkuDetails(final int p0, final String p1, final String p2, final Bundle p3);
    
    int isBillingSupported(final int p0, final String p1, final String p2);
    
    int isPromoEligible(final int p0, final String p1, final String p2);
}
