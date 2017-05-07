// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.DialogInterface$OnCancelListener;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog$Builder;
import android.app.AlertDialog;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.app.Activity;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.view.MenuItem;
import android.animation.Animator;
import android.view.View$OnClickListener;
import android.support.v7.app.ActionBar;
import android.app.Dialog;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;

class TopPanel$3 implements LanguageSelector$LanguageSelectorCallback
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$3(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void languageChanged(final Language language, final boolean b) {
        Log.d("screen", "Language changed");
        if (this.this$0.processLanguageChange(language) && b) {
            this.this$0.context.doUnpause();
        }
        this.this$0.context.startScreenUpdateTask();
        this.this$0.context.reportUiModelessViewSessionEnded(IClientLogging$ModalView.audioSubtitlesSelector, this.this$0.mDialogLanguageId);
    }
    
    @Override
    public void updateDialog(final Dialog dialog) {
        this.this$0.context.updateVisibleDialog(dialog);
    }
    
    @Override
    public void userCanceled() {
        Log.d("screen", "User canceled selection");
        this.this$0.context.doUnpause();
        this.this$0.context.startScreenUpdateTask();
        this.this$0.context.reportUiModelessViewSessionEnded(IClientLogging$ModalView.audioSubtitlesSelector, this.this$0.mDialogLanguageId);
    }
    
    @Override
    public boolean wasPlaying() {
        return this.this$0.context.getPlayer().isPlaying();
    }
}
