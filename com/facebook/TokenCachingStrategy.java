// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.facebook.internal.Validate;
import java.util.Date;
import android.os.Bundle;

public abstract class TokenCachingStrategy
{
    public static final String EXPIRATION_DATE_KEY = "com.facebook.TokenCachingStrategy.ExpirationDate";
    private static final long INVALID_BUNDLE_MILLISECONDS = Long.MIN_VALUE;
    private static final String IS_SSO_KEY = "com.facebook.TokenCachingStrategy.IsSSO";
    public static final String LAST_REFRESH_DATE_KEY = "com.facebook.TokenCachingStrategy.LastRefreshDate";
    public static final String PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.Permissions";
    public static final String TOKEN_KEY = "com.facebook.TokenCachingStrategy.Token";
    public static final String TOKEN_SOURCE_KEY = "com.facebook.TokenCachingStrategy.AccessTokenSource";
    public static final String USER_FBID_KEY = "com.facebook.TokenCachingStrategy.UserFBID";
    
    static Date getDate(final Bundle bundle, final String s) {
        if (bundle != null) {
            final long long1 = bundle.getLong(s, Long.MIN_VALUE);
            if (long1 != Long.MIN_VALUE) {
                return new Date(long1);
            }
        }
        return null;
    }
    
    public static Date getExpirationDate(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return getDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate");
    }
    
    public static long getExpirationMilliseconds(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate");
    }
    
    public static Date getLastRefreshDate(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return getDate(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate");
    }
    
    public static long getLastRefreshMilliseconds(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong("com.facebook.TokenCachingStrategy.LastRefreshDate");
    }
    
    public static List<String> getPermissions(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return (List<String>)bundle.getStringArrayList("com.facebook.TokenCachingStrategy.Permissions");
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
    
    public static String getToken(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.Token");
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
    
    public static void putExpirationDate(final Bundle bundle, final Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        putDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate", date);
    }
    
    public static void putExpirationMilliseconds(final Bundle bundle, final long n) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong("com.facebook.TokenCachingStrategy.ExpirationDate", n);
    }
    
    public static void putLastRefreshDate(final Bundle bundle, final Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        putDate(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate", date);
    }
    
    public static void putLastRefreshMilliseconds(final Bundle bundle, final long n) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong("com.facebook.TokenCachingStrategy.LastRefreshDate", n);
    }
    
    public static void putPermissions(final Bundle bundle, final List<String> list) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(list, "value");
        ArrayList list2;
        if (list instanceof ArrayList) {
            list2 = (ArrayList<? extends E>)list;
        }
        else {
            list2 = new ArrayList((Collection<? extends E>)list);
        }
        bundle.putStringArrayList("com.facebook.TokenCachingStrategy.Permissions", list2);
    }
    
    public static void putSource(final Bundle bundle, final AccessTokenSource accessTokenSource) {
        Validate.notNull(bundle, "bundle");
        bundle.putSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource", (Serializable)accessTokenSource);
    }
    
    public static void putToken(final Bundle bundle, final String s) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(s, "value");
        bundle.putString("com.facebook.TokenCachingStrategy.Token", s);
    }
    
    public abstract void clear();
    
    public abstract Bundle load();
    
    public abstract void save(final Bundle p0);
}
