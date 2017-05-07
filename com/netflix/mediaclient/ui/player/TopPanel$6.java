// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import android.media.AudioManager;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.widget.ImageView;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Language;
import android.app.Activity;
import android.widget.TextView;
import android.widget.SeekBar;
import com.netflix.mediaclient.ui.common.Social;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.widget.Button;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.ImageButton;

class TopPanel$6 implements Runnable
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$6(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        final ImageButton access$300 = this.this$0.mLanguage;
        if (access$300 != null && !((ImageView)access$300).isShown()) {
            ((ImageView)access$300).setVisibility(0);
        }
    }
}
