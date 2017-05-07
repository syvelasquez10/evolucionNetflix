// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.error;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class CrashReportingFactory
{
    public static final int CRITTERCISM = 1;
    public static final int NCCP = 0;
    private static final String TAG = "nf_crash";
    
    public static CrashReportingService getCrashService(final Context context) {
        final String stringPref = PreferenceUtils.getStringPref(context, "reporting_service", null);
        int int1;
        final int n = int1 = 1;
        while (true) {
            if (stringPref != null) {
                Label_0090: {
                    try {
                        int1 = Integer.parseInt(stringPref);
                        switch (int1) {
                            default: {
                                Log.d("nf_crash", "Default, use CRITTERCISM for crash reporting");
                                return new CrittercismCrashService();
                            }
                            case 0: {
                                break;
                            }
                            case 1: {
                                break Label_0090;
                            }
                        }
                    }
                    catch (Throwable t) {
                        Log.w("nf_crash", "");
                        int1 = n;
                        continue;
                    }
                    Log.d("nf_crash", "Use NCCP for crash reporting");
                    return new NccpCrashService();
                }
                Log.d("nf_crash", "Use CRITTERCISM for crash reporting");
                return new CrittercismCrashService();
            }
            continue;
        }
    }
}
