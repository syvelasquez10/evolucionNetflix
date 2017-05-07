// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.repository;

import java.util.StringTokenizer;
import com.netflix.mediaclient.Log;
import android.content.Context;
import java.util.Locale;

public final class UserLocale
{
    private static final String RAW_DELIMITER = "-";
    private static final String TAG = "nf_user_locale";
    private String language;
    private String languageDescription;
    private Locale locale;
    private String raw;
    private String region;
    
    public UserLocale(final String s) {
        this.validateNonEmpty(s, "raw");
        this.raw = s.trim();
        this.parseRaw();
        this.init();
    }
    
    public UserLocale(final String s, final String region, final String s2) {
        this.validateNonEmpty(s, "language");
        this.validateNonEmpty(s2, "languageDescription");
        this.language = s.trim().toLowerCase();
        this.region = region;
        if (this.region != null) {
            this.region = this.region.trim().toUpperCase();
        }
        this.languageDescription = s2.trim();
        this.createRaw();
        this.init();
    }
    
    private void createRaw() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.language);
        if (this.region != null) {
            sb.append("-");
            sb.append(this.region);
        }
        this.raw = sb.toString();
    }
    
    public static Locale getDeviceLocale(final Context context) {
        final Locale locale = context.getApplicationContext().getResources().getConfiguration().locale;
        if (Log.isLoggable("nf_user_locale", 3)) {
            Log.d("nf_user_locale", "Current device locale is " + locale);
        }
        return locale;
    }
    
    public static String getRawDeviceLocale(final Context context) {
        return toUserLocale(getDeviceLocale(context));
    }
    
    private void init() {
        if (this.region == null || "".equals(this.region)) {
            this.locale = new Locale(this.language);
            return;
        }
        this.locale = new Locale(this.language, this.region);
    }
    
    private void parseRaw() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.raw, "-");
        if (stringTokenizer.countTokens() < 1 || stringTokenizer.countTokens() > 2) {
            throw new IllegalArgumentException("Invalid format of raw: " + this.raw);
        }
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (n == 0) {
                this.language = nextToken.toLowerCase();
            }
            else if (n == 1) {
                this.region = nextToken.toUpperCase();
            }
            else {
                Log.w("nf_user_locale", "Unexpected token in given prefered language. Token " + n + ": " + nextToken);
            }
            ++n;
        }
    }
    
    public static String toUserLocale(final Locale locale) {
        if (locale == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage());
        if (locale.getCountry() != null && !"".equals(locale.getCountry().trim())) {
            sb.append("-");
            sb.append(locale.getCountry());
        }
        return sb.toString();
    }
    
    private void validateNonEmpty(final String s, final String s2) {
        if (s == null || "".equals(s.trim())) {
            throw new IllegalArgumentException(s2 + " argument can not be empty!");
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof UserLocale)) {
                return false;
            }
            final UserLocale userLocale = (UserLocale)o;
            if (this.raw == null) {
                if (userLocale.raw != null) {
                    return false;
                }
            }
            else if (!this.raw.equals(userLocale.raw)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean equalsByLanguage(final UserLocale userLocale) {
        if (userLocale != null) {
            if (this.language == null) {
                if (userLocale.language != null) {
                    return false;
                }
            }
            else if (!this.language.equalsIgnoreCase(userLocale.language)) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public String getLanguageDescription() {
        return this.languageDescription;
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public String getRaw() {
        return this.raw;
    }
    
    public String getRegion() {
        return this.region;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.raw == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.raw.hashCode();
        }
        return hashCode + 31;
    }
    
    @Override
    public String toString() {
        return "UserLocale [language=" + this.language + ", languageDescription=" + this.languageDescription + ", locale=" + this.locale + ", raw=" + this.raw + ", region=" + this.region + "]";
    }
}
