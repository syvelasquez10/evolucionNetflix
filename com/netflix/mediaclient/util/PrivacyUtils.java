// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.user.User;

public final class PrivacyUtils
{
    private static final String TAG = "nf_privacy";
    
    public static boolean isPotentialPrivacyViolationFound(final String s, final User user) {
        if (!StringUtils.isEmpty(s)) {
            if (user == null) {
                Log.e("nf_privacy", "User is NULL!");
                return true;
            }
            if (validatePrivacy(s, user.getEmail())) {
                if (Log.isLoggable()) {
                    Log.e("nf_privacy", "User email privacy violation. Value " + s + ", email " + user.getEmail());
                }
                return true;
            }
        }
        return false;
    }
    
    public static boolean isPotentialPrivacyViolationFound(final String s, final UserProfile userProfile) {
        if (!StringUtils.isEmpty(s)) {
            if (userProfile == null) {
                Log.e("nf_privacy", "User is NULL!");
                return true;
            }
            if (validatePrivacy(s, userProfile.getEmail())) {
                if (Log.isLoggable()) {
                    Log.e("nf_privacy", "User email privacy violation. Value " + s + ", email " + userProfile.getEmail());
                }
                return true;
            }
        }
        return false;
    }
    
    private static boolean validatePrivacy(final String s, final String s2) {
        return !StringUtils.isEmpty(s2) && !StringUtils.isEmpty(s) && s.trim().toLowerCase().contains(s2.toLowerCase());
    }
}
