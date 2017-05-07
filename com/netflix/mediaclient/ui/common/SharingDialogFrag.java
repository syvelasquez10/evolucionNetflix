// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class SharingDialogFrag extends NetflixDialogFrag implements MdxMiniPlayerDialog
{
    public static SharingDialogFrag newInstance() {
        final SharingDialogFrag sharingDialogFrag = new SharingDialogFrag();
        sharingDialogFrag.setStyle(1, 2131558594);
        return sharingDialogFrag;
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903142, viewGroup, false);
        inflate.findViewById(2131231053).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ((NetflixActivity)SharingDialogFrag.this.getActivity()).getMdxMiniPlayerFrag().unshareVideo();
                SharingDialogFrag.this.dismiss();
            }
        });
        return inflate;
    }
}
