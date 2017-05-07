// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class LanguageSelectorTablet extends LanguageSelector
{
    public LanguageSelectorTablet(final NetflixActivity netflixActivity, final LanguageSelector$LanguageSelectorCallback languageSelector$LanguageSelectorCallback) {
        super(netflixActivity, languageSelector$LanguageSelectorCallback);
    }
    
    @Override
    protected int calculateListViewHeight() {
        Log.d("nf_language_selector", "Tablet calculate height");
        int n = (int)this.mController.getResources().getDimension(2131296352);
        final int n2 = (int)this.mController.getResources().getDimension(2131296311);
        final int n3 = (int)this.mController.getResources().getDimension(2131296543);
        final Language language = this.getLanguage();
        int n4;
        if (language == null) {
            n4 = 0;
        }
        else {
            n4 = Math.max(language.getAltAudios().length, language.getSubtitles().length + 1) * n3;
        }
        if (Log.isLoggable()) {
            Log.d("nf_language_selector", "Max height " + n2 + " px, item height " + n3 + " px, proposed list height " + n4 + " px, item divider height" + " px");
        }
        if (n4 > n && (n = n4) >= n2) {
            return n2;
        }
        return n;
    }
    
    @Override
    protected int getDialogLayoutId() {
        Log.d("nf_language_selector", "R.layout.language_selector_tablet_dialog");
        return 2130903134;
    }
}
