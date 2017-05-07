// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Pair;

public final class MdxTargetSelection
{
    private final String TAG;
    private MdxTarget[] mdxTargets;
    private MdxTarget selectedTarget;
    
    public MdxTargetSelection(final Pair<String, String>[] array, final String s) {
        this.TAG = "nf_mdx";
        (this.mdxTargets = new MdxTarget[array.length + 1])[0] = MdxTarget.createLocalTarget();
        for (int i = 0; i < array.length; ++i) {
            this.mdxTargets[i + 1] = MdxTarget.createRemoteTarget(array[i]);
            if (this.mdxTargets[i + 1].getUUID().equals(s)) {
                this.selectedTarget = this.mdxTargets[i + 1];
            }
        }
        if (this.selectedTarget == null) {
            this.selectedTarget = this.mdxTargets[0];
        }
    }
    
    private static List<String> toAdapterList(final Context context, final MdxTarget[] array) {
        final ArrayList<String> list = new ArrayList<String>();
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                final MdxTarget mdxTarget = array[i];
                if (mdxTarget.isLocal()) {
                    list.add(context.getString(2131493257));
                }
                else {
                    list.add(mdxTarget.getFriendlyName());
                }
            }
        }
        return list;
    }
    
    public int getDevicePositionByUUID(final String s) {
        if (s == null) {
            Log.e("nf_mdx", "getDevicePositionByUUID:: Given UUID is null!");
            return 0;
        }
        for (int i = 0; i < this.mdxTargets.length; ++i) {
            if (s.equals(this.mdxTargets[i].getUUID())) {
                Log.d("nf_mdx", "getDevicePositionByUUID:: given device found on position " + i);
                return i;
            }
        }
        Log.e("nf_mdx", "Selected device not found!");
        return 0;
    }
    
    public int getLocalDevicePosition() {
        for (int i = 0; i < this.mdxTargets.length; ++i) {
            if (this.mdxTargets[i].isLocal()) {
                Log.d("nf_mdx", "Local device found on position " + i);
                return i;
            }
        }
        Log.e("nf_mdx", "We do NOT have local device from Mobile UI");
        return 0;
    }
    
    public MdxTarget[] getMdxTargets() {
        return this.mdxTargets;
    }
    
    public int getSelectedDevicePosition() {
        for (int i = 0; i < this.mdxTargets.length; ++i) {
            if (this.mdxTargets[i] == this.selectedTarget) {
                Log.d("nf_mdx", "Selected device found on position " + i);
                return i;
            }
        }
        Log.e("nf_mdx", "Selected device not found!");
        return 0;
    }
    
    public MdxTarget getSelectedTarget() {
        return this.selectedTarget;
    }
    
    public List<String> getTargets(final Context context) {
        if (this.mdxTargets == null) {
            Log.e("nf_mdx", "We should never be here. No targets!");
        }
        return toAdapterList(context, this.mdxTargets);
    }
    
    public MdxTarget setTarget(final int n) {
        if (Log.isLoggable("nf_mdx", 3)) {
            Log.d("nf_mdx", "User selected as target on position " + n);
        }
        if (this.mdxTargets != null && this.mdxTargets.length > n) {
            if (Log.isLoggable("nf_mdx", 3)) {
                Log.d("nf_mdx", "User selected as target " + this.mdxTargets[n]);
            }
            return this.selectedTarget = this.mdxTargets[n];
        }
        Log.e("nf_mdx", "Target NOT found! This should NOT happen!");
        return null;
    }
}
