// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import java.util.Locale;
import com.netflix.mediaclient.util.l10n.UserLocale;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.esn.BaseEsnProvider;
import com.netflix.mediaclient.Log;
import com.vailsys.whistleengine.DialExtra;
import android.content.Context;

public final class VoipUtils
{
    protected static final String DELIM = "_";
    protected static final int DEVICE_MODEL_LIMIT = 31;
    protected static final int DEVICE_MODEL_LIMIT_BRAND = 10;
    private static final String DNIS = "NFCLIENT_CTI_";
    private static final String INT = "_test";
    protected static final int MANUFACTURER_LIMIT = 5;
    protected static final int MODEL_LIMIT = 10;
    private static final String SIP_HEADER_CLIENT_VERSION = "X-NFLX-ClientAppVer";
    private static final String SIP_HEADER_UUID = "X-NFLX-SessionID";
    private static final String TAG = "nf_voip";
    
    static DialExtra createDialExtra(final Context context, final String s) {
        final DialExtra dialExtra = new DialExtra();
        if (s != null) {
            dialExtra.addXHeader("X-NFLX-SessionID", s);
        }
        if (Log.isLoggable()) {
            Log.d("nf_voip", "X-NFLX-SessionID:" + s);
        }
        final StringBuilder sb = new StringBuilder();
        final String deviceModel = BaseEsnProvider.findDeviceModel();
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Model:" + deviceModel);
        }
        sb.append(modifyByXHeaderRules(deviceModel));
        sb.append("-android").append(AndroidUtils.getAndroidVersion());
        sb.append('-').append("4.12.2");
        sb.append('-').append(AndroidManifestUtils.getVersionCode(context));
        if (Log.isLoggable()) {
            Log.d("nf_voip", "X-NFLX-ClientAppVer:" + sb.toString());
        }
        dialExtra.addXHeader("X-NFLX-ClientAppVer", sb.toString());
        return dialExtra;
    }
    
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
    
    private static String modifyByXHeaderRules(String upperCase) {
        if (upperCase == null || "".equals(upperCase.trim())) {
            return "";
        }
        upperCase = upperCase.toUpperCase(Locale.ENGLISH);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < upperCase.length(); ++i) {
            final char char1 = upperCase.charAt(i);
            if ((char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9') || char1 == '-' || char1 == '=' || char1 == '!' || char1 == '@' || char1 == '#' || char1 == '$' || char1 == '%' || char1 == '^' || char1 == '&' || char1 == '*' || char1 == '(' || char1 == ')' || char1 == '/' || char1 == '{' || char1 == '}' || char1 == '[' || char1 == ']' || char1 == ':' || char1 == ';' || char1 == '.') {
                sb.append(char1);
            }
            else {
                sb.append('=');
            }
        }
        return sb.toString();
    }
}
