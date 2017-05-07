// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

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
        int i = 0;
        this.supportedLocales = new UserLocale[16];
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
        for (UserLocale[] supportedLocales = this.supportedLocales; i < supportedLocales.length; ++i) {
            Log.d("nf_loc", "" + supportedLocales[i]);
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
        Label_0029: {
            if (s != null) {
                preferredLanguages = s;
                if (!"".equals(s.trim())) {
                    break Label_0029;
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
