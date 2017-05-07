// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.Log;
import android.content.DialogInterface$OnKeyListener;
import android.content.IntentFilter;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.SeekBar;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class VolumeDialogFrag extends NetflixDialogFrag implements SeekBar$OnSeekBarChangeListener, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    private static final String TAG = "VolumeDialogFrag";
    private BroadcastReceiver mVolumeBroadcastReceiver;
    private NetflixSeekBar seekBar;
    
    private int getMostRecentVolume() {
        final MdxMiniPlayerFrag mdxMiniPlayerFrag = ((NetflixActivity)this.getActivity()).getMdxMiniPlayerFrag();
        if (mdxMiniPlayerFrag != null) {
            return mdxMiniPlayerFrag.getVolume();
        }
        return 100;
    }
    
    public static VolumeDialogFrag newInstance() {
        final VolumeDialogFrag volumeDialogFrag = new VolumeDialogFrag();
        volumeDialogFrag.setStyle(1, 0);
        return volumeDialogFrag;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903216, viewGroup, false);
        (this.seekBar = (NetflixSeekBar)inflate.findViewById(2131427859)).setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)this);
        this.seekBar.setMax(100);
        return inflate;
    }
    
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
    }
    
    public void onResume() {
        super.onResume();
        this.seekBar.setProgress(this.getMostRecentVolume());
    }
    
    public void onStart() {
        super.onStart();
        this.mVolumeBroadcastReceiver = new VolumeDialogFrag$1(this);
        LocalBroadcastManager.getInstance((Context)this.getActivity()).registerReceiver(this.mVolumeBroadcastReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.MDX_SETVOLUME"));
        this.getDialog().setOnKeyListener((DialogInterface$OnKeyListener)new VolumeDialogFrag$2(this));
    }
    
    public void onStartTrackingTouch(final SeekBar seekBar) {
    }
    
    public void onStop() {
        super.onStop();
        if (this.getActivity() != null && this.mVolumeBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance((Context)this.getActivity()).unregisterReceiver(this.mVolumeBroadcastReceiver);
        }
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
        final int progress = seekBar.getProgress();
        Log.v("VolumeDialogFrag", "Setting mdx volume to: " + progress);
        ((NetflixActivity)this.getActivity()).getMdxMiniPlayerFrag().setVolume(progress);
    }
}
