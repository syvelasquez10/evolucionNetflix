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
import com.netflix.mediaclient.media.Language;
import android.app.Activity;
import android.widget.TextView;
import android.widget.SeekBar;
import com.netflix.mediaclient.ui.common.Social;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.widget.ImageButton;
import android.widget.Button;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class TopPanel$3 implements View$OnClickListener
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$3(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        Log.d("screen", "Display language dialog");
        final PlayerActivity context = this.this$0.context;
        if (context != null) {
            context.extendTimeoutTimer();
        }
        this.this$0.mLanguageSelector.display(this.this$0.context.getLanguage());
        this.this$0.context.stopScreenUpdateTask();
        this.this$0.mDialogLanguageId = this.this$0.context.reportUiModelessViewSessionStart(IClientLogging$ModalView.audioSubtitlesSelector);
    }
}
