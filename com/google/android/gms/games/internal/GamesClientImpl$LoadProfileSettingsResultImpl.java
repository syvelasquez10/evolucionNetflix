// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Players$LoadProfileSettingsResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadProfileSettingsResultImpl extends a implements Players$LoadProfileSettingsResult
{
    private final boolean WP;
    private final boolean We;
    
    GamesClientImpl$LoadProfileSettingsResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        try {
            if (dataHolder.getCount() > 0) {
                final int ar = dataHolder.ar(0);
                this.We = dataHolder.d("profile_visible", 0, ar);
                this.WP = dataHolder.d("profile_visibility_explicitly_set", 0, ar);
            }
            else {
                this.We = true;
                this.WP = false;
            }
        }
        finally {
            dataHolder.close();
        }
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    @Override
    public boolean isProfileVisible() {
        return this.We;
    }
    
    @Override
    public boolean isVisibilityExplicitlySet() {
        return this.WP;
    }
}
