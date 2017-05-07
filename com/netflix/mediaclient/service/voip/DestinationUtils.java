// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.repository.UserLocale;

public final class DestinationUtils
{
    private static final String DNIS = "NFCLIENT_CTI_";
    private static final String INT = "_test";
    private static final String TAG = "nf_voip";
    
    static String getCustomerServiceNumber(final UserLocale userLocale) {
        String language;
        if (userLocale == null || userLocale.getLanguage() == null) {
            Log.e("nf_voip", "App locale is NOT know, default to en");
            language = "en";
        }
        else {
            language = userLocale.getLanguage();
        }
        final StringBuilder sb = new StringBuilder("NFCLIENT_CTI_");
        sb.append(language);
        final String string = sb.toString();
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Use customer support number " + string);
        }
        return string;
    }
}
