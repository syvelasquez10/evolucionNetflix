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
        final UserLocale[] userLocales = this.toUserLocales(s);
        if (userLocales.length < 1) {
            Log.w("nf_loc", "Empty list of preferred languages, set default");
            return null;
        }
        int i = 0;
        UserLocale userLocale2 = userLocale;
        while (i < userLocales.length) {
            if (Log.isLoggable("nf_loc", 3)) {
                Log.d("nf_loc", "Choice #" + i + ": " + userLocales[i]);
            }
            UserLocale userLocale3;
            for (int j = 0; j < this.supportedLocales.length; ++j, userLocale2 = userLocale3) {
                if (Log.isLoggable("nf_loc", 3)) {
                    Log.d("nf_loc", "Try to match by locale with #" + j + ": " + this.supportedLocales[j]);
                }
                userLocale3 = userLocale2;
                if (userLocales[i] != null) {
                    if (!userLocales[i].equals(this.supportedLocales[j])) {
                        userLocale3 = userLocale2;
                        if (!userLocales[i].equalsByLanguage(this.supportedLocales[j])) {
                            continue;
                        }
                    }
                    if (Log.isLoggable("nf_loc", 3)) {
                        Log.d("nf_loc", "Match by locale with #" + j + ": " + this.supportedLocales[j]);
                    }
                    if (userLocales[i].equals(this.supportedLocales[j])) {
                        Log.d("nf_loc", "Perfect Match by locale with #" + j + ": " + this.supportedLocales[j]);
                        return this.supportedLocales[j];
                    }
                    if ((userLocale3 = userLocale2) == null) {
                        userLocale3 = this.supportedLocales[j];
                    }
                }
            }
            ++i;
        }
        Log.i("nf_loc", "findBestMatch: " + userLocale2);
        return userLocale2;
    }
    
    private void initSupportedLocales() {
        this.supportedLocales = new UserLocale[10];
        this.defaultAppLocale = new UserLocale(Locale.ENGLISH.getLanguage(), null, "English");
        this.supportedLocales[0] = this.defaultAppLocale;
        this.supportedLocales[1] = new UserLocale(Locale.FRENCH.getLanguage(), null, "Fran\u00e7ais");
        this.supportedLocales[2] = new UserLocale("es", null, "Espa\u00f1ol");
        this.supportedLocales[3] = new UserLocale("pt", null, "Portugu\u00eas");
        this.supportedLocales[4] = new UserLocale(Locale.UK.getLanguage(), Locale.UK.getCountry(), "English-GB");
        this.supportedLocales[5] = new UserLocale("sv", null, "Svenskt");
        this.supportedLocales[6] = new UserLocale("nb", null, "Norske");
        this.supportedLocales[7] = new UserLocale("da", null, "Dansk");
        this.supportedLocales[8] = new UserLocale("fi", null, "Suomi");
        this.supportedLocales[9] = new UserLocale("nl", null, "Nederlands");
        final UserLocale[] supportedLocales = this.supportedLocales;
        for (int length = supportedLocales.length, i = 0; i < length; ++i) {
            Log.d("nf_loc", "" + supportedLocales[i]);
        }
    }
    
    private String[] toArray(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (stringTokenizer.countTokens() < 1) {
            return new String[0];
        }
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = stringTokenizer.nextToken();
            ++n;
        }
        return array;
    }
    
    private UserLocale[] toUserLocales(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (stringTokenizer.countTokens() < 1) {
            return new UserLocale[0];
        }
        final UserLocale[] array = new UserLocale[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final UserLocale userLocale = new UserLocale(stringTokenizer.nextToken());
            final int n2 = n + 1;
            array[n] = userLocale;
            if (Log.isLoggable("nf_loc", 3)) {
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
            if (Log.isLoggable("nf_loc", 3)) {
                Log.d("nf_loc", "Keeping same application locale " + this.currentAppLocale.getRaw());
            }
            return;
        }
        if (Log.isLoggable("nf_loc", 3)) {
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
