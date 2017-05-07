// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.internal.Validate;
import java.util.Date;
import android.os.Bundle;

public abstract class TokenCachingStrategy
{
    static Date getDate(final Bundle bundle, final String s) {
        if (bundle != null) {
            final long long1 = bundle.getLong(s, Long.MIN_VALUE);
            if (long1 != Long.MIN_VALUE) {
                return new Date(long1);
            }
        }
        return null;
    }
    
    public static AccessTokenSource getSource(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        if (bundle.containsKey("com.facebook.TokenCachingStrategy.AccessTokenSource")) {
            return (AccessTokenSource)bundle.getSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource");
        }
        if (bundle.getBoolean("com.facebook.TokenCachingStrategy.IsSSO")) {
            return AccessTokenSource.FACEBOOK_APPLICATION_WEB;
        }
        return AccessTokenSource.WEB_VIEW;
    }
    
    public static boolean hasTokenInformation(final Bundle bundle) {
        if (bundle != null) {
            final String string = bundle.getString("com.facebook.TokenCachingStrategy.Token");
            if (string != null && string.length() != 0 && bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate", 0L) != 0L) {
                return true;
            }
        }
        return false;
    }
    
    static void putDate(final Bundle bundle, final String s, final Date date) {
        bundle.putLong(s, date.getTime());
    }
    
    public abstract void clear();
    
    public abstract Bundle load();
    
    public abstract void save(final Bundle p0);
}
