// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Context;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;

final class Coppola1Utils$1 implements Runnable
{
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ ServiceManager val$manager;
    final /* synthetic */ PlayerFragment val$playerFragment;
    final /* synthetic */ Status val$res;
    
    Coppola1Utils$1(final Activity val$activity, final PlayerFragment val$playerFragment, final ServiceManager val$manager, final Status val$res) {
        this.val$activity = val$activity;
        this.val$playerFragment = val$playerFragment;
        this.val$manager = val$manager;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.val$activity) && this.val$playerFragment != null && this.val$playerFragment.getView() != null) {
            this.val$playerFragment.getView().getLayoutParams().height = 0;
            this.val$playerFragment.onManagerReady(this.val$manager, this.val$res);
        }
    }
}
