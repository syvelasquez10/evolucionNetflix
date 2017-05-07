// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

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
import android.widget.SeekBar;
import com.netflix.mediaclient.ui.common.Social;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.widget.ImageButton;
import android.widget.Button;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.TextView;
import com.netflix.mediaclient.util.StringUtils;

class TopPanel$7 implements Runnable
{
    final /* synthetic */ TopPanel this$0;
    final /* synthetic */ String val$displayText;
    
    TopPanel$7(final TopPanel this$0, final String val$displayText) {
        this.this$0 = this$0;
        this.val$displayText = val$displayText;
    }
    
    @Override
    public void run() {
        final TextView access$400 = this.this$0.mTitleLabel;
        if (access$400 == null) {
            return;
        }
        if (StringUtils.isEmpty(this.val$displayText)) {
            access$400.setText((CharSequence)"");
            return;
        }
        access$400.setText((CharSequence)this.val$displayText);
    }
}
