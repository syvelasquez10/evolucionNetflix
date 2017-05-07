// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.Log;
import android.widget.SeekBar;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class VolumeDialogFrag extends NetflixDialogFrag implements SeekBar$OnSeekBarChangeListener, MdxMiniPlayerDialog
{
    private static final String TAG = "VolumeDialogFrag";
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
        volumeDialogFrag.setStyle(1, 2131558710);
        return volumeDialogFrag;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903194, viewGroup, false);
        (this.seekBar = (NetflixSeekBar)inflate.findViewById(2131165659)).setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)this);
        this.seekBar.setMax(100);
        return inflate;
    }
    
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
    }
    
    public void onResume() {
        super.onResume();
        this.seekBar.setProgress(this.getMostRecentVolume());
    }
    
    public void onStartTrackingTouch(final SeekBar seekBar) {
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
        final int progress = seekBar.getProgress();
        Log.v("VolumeDialogFrag", "Setting mdx volume to: " + progress);
        ((NetflixActivity)this.getActivity()).getMdxMiniPlayerFrag().setVolume(progress);
    }
}
