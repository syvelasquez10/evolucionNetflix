// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.internal.fo;

public final class a implements AppState
{
    private final int wr;
    private final String ws;
    private final byte[] wt;
    private final boolean wu;
    private final String wv;
    private final byte[] ww;
    
    public a(final AppState appState) {
        this.wr = appState.getKey();
        this.ws = appState.getLocalVersion();
        this.wt = appState.getLocalData();
        this.wu = appState.hasConflict();
        this.wv = appState.getConflictVersion();
        this.ww = appState.getConflictData();
    }
    
    static int a(final AppState appState) {
        return fo.hashCode(appState.getKey(), appState.getLocalVersion(), appState.getLocalData(), appState.hasConflict(), appState.getConflictVersion(), appState.getConflictData());
    }
    
    static boolean a(final AppState appState, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof AppState)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (appState != o) {
                final AppState appState2 = (AppState)o;
                if (fo.equal(appState2.getKey(), appState.getKey()) && fo.equal(appState2.getLocalVersion(), appState.getLocalVersion()) && fo.equal(appState2.getLocalData(), appState.getLocalData()) && fo.equal(appState2.hasConflict(), appState.hasConflict()) && fo.equal(appState2.getConflictVersion(), appState.getConflictVersion())) {
                    b2 = b;
                    if (fo.equal(appState2.getConflictData(), appState.getConflictData())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final AppState appState) {
        return fo.e(appState).a("Key", appState.getKey()).a("LocalVersion", appState.getLocalVersion()).a("LocalData", appState.getLocalData()).a("HasConflict", appState.hasConflict()).a("ConflictVersion", appState.getConflictVersion()).a("ConflictData", appState.getConflictData()).toString();
    }
    
    public AppState dt() {
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public byte[] getConflictData() {
        return this.ww;
    }
    
    @Override
    public String getConflictVersion() {
        return this.wv;
    }
    
    @Override
    public int getKey() {
        return this.wr;
    }
    
    @Override
    public byte[] getLocalData() {
        return this.wt;
    }
    
    @Override
    public String getLocalVersion() {
        return this.ws;
    }
    
    @Override
    public boolean hasConflict() {
        return this.wu;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
}
