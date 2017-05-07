// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Locale;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.repository.UserLocale;

public final class UserLocaleRepository
{
    private static final String LANGUAGE_DELIMITER = ",";
    private static final String TAG = "nf_loc";
    private UserLocale currentAppLocale;
    private UserLocale defaultAppLocale;
    private String preferredLanguages;
    private UserLocale[] supportedLocales;
    
    public UserLocaleRepository() {
        this.initSupportedLocales();
    }
    
    private UserLocale findBestMatch(final String s) {
        final UserLocale userLocale = null;
        UserLocale userLocale2 = null;
        final UserLocale[] userLocales = this.toUserLocales(s);
        if (userLocales.length < 1) {
            Log.w("nf_loc", "Empty list of preferred languages, set default");
        }
        else {
            int i = 0;
            UserLocale userLocale3 = userLocale;
            while (i < userLocales.length) {
                if (Log.isLoggable()) {
                    Log.d("nf_loc", "Choice #" + i + ": " + userLocales[i]);
                }
                UserLocale userLocale4;
                for (int j = 0; j < this.supportedLocales.length; ++j, userLocale3 = userLocale4) {
                    if (Log.isLoggable()) {
                        Log.d("nf_loc", "Try to match by locale with #" + j + ": " + this.supportedLocales[j]);
                    }
                    userLocale4 = userLocale3;
                    if (userLocales[i] != null) {
                        if (!userLocales[i].equals(this.supportedLocales[j])) {
                            userLocale4 = userLocale3;
                            if (!userLocales[i].equalsByLanguage(this.supportedLocales[j])) {
                                continue;
                            }
                        }
                        if (Log.isLoggable()) {
                            Log.d("nf_loc", "Match by locale with #" + j + ": " + this.supportedLocales[j]);
                        }
                        if (userLocales[i].equals(this.supportedLocales[j])) {
                            if (Log.isLoggable()) {
                                Log.d("nf_loc", "Perfect Match by locale with #" + j + ": " + this.supportedLocales[j]);
                            }
                            return this.supportedLocales[j];
                        }
                        if ((userLocale4 = userLocale3) == null) {
                            userLocale4 = this.supportedLocales[j];
                        }
                    }
                }
                ++i;
            }
            userLocale2 = userLocale3;
            if (Log.isLoggable()) {
                Log.d("nf_loc", "findBestMatch: " + userLocale3);
                return userLocale3;
            }
        }
        return userLocale2;
    }
    
    private void initSupportedLocales() {
        final int n = 0;
        this.supportedLocales = new UserLocale[28];
        this.defaultAppLocale = new UserLocale(Locale.ENGLISH.getLanguage(), null, "English");
        this.supportedLocales[0] = this.defaultAppLocale;
        this.supportedLocales[1] = new UserLocale(Locale.FRENCH.getLanguage(), null, "Fran\u00e7ais");
        this.supportedLocales[2] = new UserLocale("es", null, "Espa\u00f1ol");
        this.supportedLocales[3] = new UserLocale("pt", null, "Portugu\u00eas");
        this.supportedLocales[4] = new UserLocale(Locale.UK.getLanguage(), Locale.UK.getCountry(), "English-GB");
        this.supportedLocales[5] = new UserLocale(Locale.ENGLISH.getLanguage(), "IE", "English-IE");
        this.supportedLocales[6] = new UserLocale("sv", null, "Svenskt");
        this.supportedLocales[7] = new UserLocale("nb", null, "Norske");
        this.supportedLocales[8] = new UserLocale("da", null, "Dansk");
        this.supportedLocales[9] = new UserLocale("fi", null, "Suomi");
        this.supportedLocales[10] = new UserLocale("nl", null, "Nederlands");
        this.supportedLocales[11] = new UserLocale(Locale.FRENCH.getLanguage(), Locale.CANADA.getCountry(), "Fran\u00e7ais-CA");
        this.supportedLocales[12] = new UserLocale(Locale.GERMAN.getLanguage(), null, "Deutsch");
        this.supportedLocales[13] = new UserLocale(Locale.ENGLISH.getLanguage(), "AU", "English-AU");
        this.supportedLocales[14] = new UserLocale(Locale.ENGLISH.getLanguage(), "NZ", "English-NZ");
        this.supportedLocales[15] = new UserLocale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry(), "\u65e5\u672c\u8a9e");
        this.supportedLocales[16] = new UserLocale(Locale.ITALY.getLanguage(), Locale.ITALY.getCountry(), "italiano");
        this.supportedLocales[17] = new UserLocale("pt", "PT", "Portugu\u00eas-PT");
        this.supportedLocales[18] = new UserLocale("es", "ES", "espa\u00f1ol-ES");
        this.supportedLocales[19] = new UserLocale("ar", null, "\u0639\u064e\u0631\u064e\u0628\u0650\u064a\u0629\u064f");
        this.supportedLocales[20] = new UserLocale(Locale.KOREAN.getLanguage(), null, "\ud55c\uad6d\uc5b4/\uc870\uc120\ub9d0");
        this.supportedLocales[21] = new UserLocale(Locale.SIMPLIFIED_CHINESE.getLanguage(), Locale.SIMPLIFIED_CHINESE.getCountry(), "\u7b80\u5316\u5b57");
        this.supportedLocales[22] = new UserLocale(Locale.TRADITIONAL_CHINESE.getLanguage(), Locale.TRADITIONAL_CHINESE.getCountry(), "\u6b63\u9ad4\u5b57/\u7e41\u9ad4\u5b57;");
        this.supportedLocales[23] = new UserLocale(Locale.SIMPLIFIED_CHINESE.getLanguage(), "SG", "\u7b80\u5316\u5b57");
        this.supportedLocales[24] = new UserLocale(Locale.TRADITIONAL_CHINESE.getLanguage(), "MO", "\u6b63\u9ad4\u5b57/\u7e41\u9ad4\u5b57;");
        this.supportedLocales[25] = new UserLocale(Locale.TRADITIONAL_CHINESE.getLanguage(), "HK", "\u6b63\u9ad4\u5b57/\u7e41\u9ad4\u5b57;");
        this.supportedLocales[26] = new UserLocale(Locale.CHINESE.getLanguage(), null, "\u7b80\u5316\u5b57");
        this.supportedLocales[27] = new UserLocale("es", "AD", "espa\u00f1ol-AD");
        if (Log.isLoggable()) {
            final StringBuilder sb = new StringBuilder();
            final UserLocale[] supportedLocales = this.supportedLocales;
            for (int length = supportedLocales.length, i = 0; i < length; ++i) {
                final UserLocale userLocale = supportedLocales[i];
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(userLocale.getRaw());
            }
            Log.i("nf_loc", "List of supported locales: [" + sb.toString() + "]");
            final UserLocale[] supportedLocales2 = this.supportedLocales;
            for (int length2 = supportedLocales2.length, j = n; j < length2; ++j) {
                Log.d("nf_loc", "" + supportedLocales2[j]);
            }
        }
    }
    
    private String[] toArray(final String s) {
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (stringTokenizer.countTokens() < 1) {
            return new String[0];
        }
        final String[] array = new String[stringTokenizer.countTokens()];
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = stringTokenizer.nextToken();
            ++n;
        }
        return array;
    }
    
    private UserLocale[] toUserLocales(final String s) {
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (stringTokenizer.countTokens() < 1) {
            return new UserLocale[0];
        }
        final UserLocale[] array = new UserLocale[stringTokenizer.countTokens()];
        while (stringTokenizer.hasMoreTokens()) {
            final UserLocale userLocale = new UserLocale(stringTokenizer.nextToken());
            final int n2 = n + 1;
            array[n] = userLocale;
            if (Log.isLoggable()) {
                Log.d("nf_loc", "Preffered locale " + n2 + ":" + userLocale);
            }
            n = n2;
        }
        return array;
    }
    
    public UserLocale getCurrentAppLocale() {
        return this.currentAppLocale;
    }
    
    public UserLocale getDefaultAppLocale() {
        return this.defaultAppLocale;
    }
    
    public String getPreferredLanguages() {
        return this.preferredLanguages;
    }
    
    public String[] getPreferredLanguagesAsArray() {
        if (this.preferredLanguages == null || "".equals(this.preferredLanguages.trim())) {
            return new String[0];
        }
        return this.toArray(this.preferredLanguages);
    }
    
    public UserLocale[] getSupportedLocales() {
        return this.supportedLocales;
    }
    
    public boolean isApkMissingSupportForLocale(final List<UserLocale> list, final List<UserLocale> list2) {
        final Locale default1 = Locale.getDefault();
        if (list == null) {
            return false;
        }
        final Iterator<UserLocale> iterator = list.iterator();
        while (true) {
            while (iterator.hasNext()) {
                if (StringUtils.safeEquals(iterator.next().getLanguage(), default1.getLanguage())) {
                    final boolean b = true;
                    final UserLocale[] supportedLocales = this.supportedLocales;
                    while (true) {
                        for (int length = supportedLocales.length, i = 0; i < length; ++i) {
                            if (StringUtils.safeEquals(supportedLocales[i].getLanguage(), default1.getLanguage())) {
                                final boolean b2 = true;
                            Label_0136:
                                while (true) {
                                    if (list2 != null) {
                                        final Iterator<UserLocale> iterator2 = list2.iterator();
                                        while (iterator2.hasNext()) {
                                            if (StringUtils.safeEquals(iterator2.next().getLanguage(), default1.getLanguage())) {
                                                final boolean b3 = true;
                                                break Label_0136;
                                            }
                                        }
                                    }
                                    Label_0223: {
                                        break Label_0223;
                                        final boolean b3;
                                        final boolean b4 = b && !b2 && !b3;
                                        Log.d("nf_loc", String.format("nf_config_locale deviceLocale:%s in Nflx: %b, in apk: %b; wasAlerted: %b, isMissingSupport: %b", default1, b, b2, b3, b4));
                                        return b4;
                                    }
                                    final boolean b3 = false;
                                    continue Label_0136;
                                }
                            }
                        }
                        final boolean b2 = false;
                        continue;
                    }
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    public boolean isPreferredLanguagesSet() {
        return this.preferredLanguages != null && !"".equals(this.preferredLanguages.trim());
    }
    
    public void reset() {
        synchronized (this) {
            Log.d("nf_loc", "reset");
            this.preferredLanguages = null;
            this.currentAppLocale = null;
        }
    }
    
    public void setApplicationLanguage(final UserLocale currentAppLocale) {
        if (this.currentAppLocale != null && this.currentAppLocale.equals(currentAppLocale)) {
            if (Log.isLoggable()) {
                Log.d("nf_loc", "Keeping same application locale " + this.currentAppLocale.getRaw());
            }
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_loc", "Change locale from " + this.currentAppLocale + " to " + currentAppLocale);
        }
        this.currentAppLocale = currentAppLocale;
    }
    
    public void setPreferredLanguages(final String s) {
        String preferredLanguages = null;
        Label_0030: {
            if (s != null) {
                preferredLanguages = s;
                if (!"".equals(s.trim())) {
                    break Label_0030;
                }
            }
            Log.e("nf_loc", "Empty list of preferred languages, set to default");
            preferredLanguages = "";
        }
        this.preferredLanguages = preferredLanguages;
        UserLocale applicationLanguage;
        if ((applicationLanguage = this.findBestMatch(preferredLanguages)) == null) {
            Log.w("nf_loc", "Match is not found under application supported languages for prefered languages: " + preferredLanguages + ". Default to " + this.defaultAppLocale);
            applicationLanguage = this.defaultAppLocale;
        }
        this.setApplicationLanguage(applicationLanguage);
    }
}
