// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import android.os.Bundle;
import android.app.ListFragment;

@Deprecated
public abstract class NetflixListFrag extends ListFragment
{
    public void onCreate(final Bundle bundle) {
        throw new IllegalAccessError("GKB: Don't use ListFragments - they kill our ability to maintain a single fragment hierarchy (e.g. NetflixFragment)");
    }
}
