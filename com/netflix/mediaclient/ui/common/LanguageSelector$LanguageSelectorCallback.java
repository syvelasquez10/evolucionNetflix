// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.app.Dialog;
import com.netflix.mediaclient.media.Language;

public interface LanguageSelector$LanguageSelectorCallback
{
    void languageChanged(final Language p0, final boolean p1);
    
    void updateDialog(final Dialog p0);
    
    void userCanceled();
    
    boolean wasPlaying();
}
