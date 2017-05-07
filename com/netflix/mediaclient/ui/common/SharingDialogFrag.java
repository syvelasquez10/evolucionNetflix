// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.View$OnClickListener;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class SharingDialogFrag extends NetflixDialogFrag implements MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    public static SharingDialogFrag newInstance() {
        final SharingDialogFrag sharingDialogFrag = new SharingDialogFrag();
        sharingDialogFrag.setStyle(1, 0);
        return sharingDialogFrag;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903197, viewGroup, false);
        inflate.findViewById(2131165679).setOnClickListener((View$OnClickListener)new SharingDialogFrag$1(this));
        return inflate;
    }
}
