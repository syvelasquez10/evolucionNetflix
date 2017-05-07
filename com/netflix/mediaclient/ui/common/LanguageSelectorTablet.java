// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class LanguageSelectorTablet extends LanguageSelector
{
    public LanguageSelectorTablet(final NetflixActivity netflixActivity, final LanguageSelectorCallback languageSelectorCallback) {
        super(netflixActivity, languageSelectorCallback);
    }
    
    @Override
    protected int calculateListViewHeight() {
        Log.d("nf_language_selector", "Tablet calculate height");
        final int n = (int)this.mController.getResources().getDimension(2131361864);
        final int n2 = (int)this.mController.getResources().getDimension(2131361863);
        final int n3 = (int)this.mController.getResources().getDimension(2131361886);
        int intrinsicHeight;
        if ((intrinsicHeight = this.mController.getResources().getDrawable(2130837766).getIntrinsicHeight()) == -1) {
            intrinsicHeight = (int)this.mController.getResources().getDimension(2131361887);
        }
        final Language language = this.getLanguage();
        int n4;
        if (language == null) {
            n4 = 0;
        }
        else {
            n4 = Math.max(language.getAltAudios().length, language.getSubtitles().length + 1) * (n3 + intrinsicHeight);
        }
        if (Log.isLoggable("nf_language_selector", 3)) {
            Log.d("nf_language_selector", "Max height " + n2 + " px, item height " + n3 + " px, proposed list height " + n4 + " px, item divider height" + " px");
        }
        if (n4 <= n) {
            return n;
        }
        if (n4 >= n2) {
            n4 = n2;
        }
        return n4;
    }
    
    @Override
    protected int getDialogLayoutId() {
        Log.d("nf_language_selector", "R.layout.language_selector_tablet_dialog");
        return 2130903115;
    }
}
