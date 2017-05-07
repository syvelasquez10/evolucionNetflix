// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcelable;
import android.os.Bundle;

public final class t
{
    public static void a(final Bundle bundle, final String s, final Parcelable parcelable) {
        bundle.setClassLoader(t.class.getClassLoader());
        Bundle bundle2;
        if ((bundle2 = bundle.getBundle("map_state")) == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(t.class.getClassLoader());
        bundle2.putParcelable(s, parcelable);
        bundle.putBundle("map_state", bundle2);
    }
}
