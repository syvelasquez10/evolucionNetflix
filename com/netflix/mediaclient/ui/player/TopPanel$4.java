// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.CoppolaUtils;
import android.app.Dialog;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.DialogInterface$OnCancelListener;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog$Builder;
import android.app.AlertDialog;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.animation.Animator;
import android.view.View$OnClickListener;
import android.support.v7.app.ActionBar;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.Log;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class TopPanel$4 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$4(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        Log.d("screen", "Display language dialog");
        this.this$0.updateLastPanelInteractionTime();
        this.this$0.mLanguageSelector.display(this.this$0.playerFragment.getLanguage());
        this.this$0.playerFragment.stopScreenUpdateTask();
        this.this$0.mDialogLanguageId = this.this$0.playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.audioSubtitlesSelector);
        return true;
    }
}
