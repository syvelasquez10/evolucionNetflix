// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.repository;

import android.content.Context;
import com.netflix.mediaclient.NetflixApplication;

public final class ApplicationRepository
{
    private PlayerRepository playerRepository;
    
    public ApplicationRepository(final NetflixApplication netflixApplication) {
        this.playerRepository = new PlayerRepository((Context)netflixApplication);
    }
    
    public void destroy() {
    }
    // monitorenter(this)
    // monitorexit(this)
    
    public PlayerRepository getPlayerRepository() {
        return this.playerRepository;
    }
}
