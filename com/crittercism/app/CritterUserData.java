// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import android.util.Log;
import java.util.Map;

public class CritterUserData
{
    private Map a;
    private final boolean b;
    
    CritterUserData(final Map a, final boolean b) {
        this.a = a;
        this.b = b;
    }
    
    public boolean crashedOnLastLoad() {
        if (!this.a.containsKey("didCrashOnLastAppLoad")) {
            if (this.b) {
                Log.w("Crittercism", "User has opted out of Crittercism.  Returning false.");
            }
            else {
                Log.w("Crittercism", "CritterUserData instance has no value for crashedOnLastLoad().  Defaulting to false.");
            }
            return false;
        }
        return this.a.get("didCrashOnLastAppLoad");
    }
    
    public String getRateMyAppMessage() {
        if (!this.a.containsKey("message")) {
            if (this.b) {
                Log.w("Crittercism", "User has opted out of Crittercism.  Returning null.");
            }
            else {
                Log.w("Crittercism", "CritterUserData instance has no value for getRateMyAppMessage().  Returning null.");
            }
        }
        return this.a.get("message");
    }
    
    public String getRateMyAppTitle() {
        if (!this.a.containsKey("title")) {
            if (this.b) {
                Log.w("Crittercism", "User has opted out of Crittercism.  Returning null.");
            }
            else {
                Log.w("Crittercism", "CritterUserData instance has no value for getRateMyAppTitle().  Returning null.");
            }
        }
        return this.a.get("title");
    }
    
    public String getUserUUID() {
        if (!this.a.containsKey("userUUID")) {
            if (this.b) {
                Log.w("Crittercism", "User has opted out of Crittercism.  Returning null.");
            }
            else {
                Log.w("Crittercism", "CritterUserData instance has no value for getUserUUID().  Returning null.");
            }
        }
        return this.a.get("userUUID");
    }
    
    public boolean isOptedOut() {
        if (!this.a.containsKey("optOutStatus")) {
            return this.b;
        }
        return this.a.get("optOutStatus");
    }
    
    public boolean shouldShowRateMyAppAlert() {
        if (!this.a.containsKey("shouldShowRateAppAlert")) {
            if (this.b) {
                Log.w("Crittercism", "User has opted out of Crittercism.  Returning false.");
            }
            else {
                Log.w("Crittercism", "CritterUserData instance has no value for shouldShowMyRateAppAlert().  Defaulting to false.");
            }
            return false;
        }
        return this.a.get("shouldShowRateAppAlert");
    }
}
