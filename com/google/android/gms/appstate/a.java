// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.internal.m;

public final class a implements AppState
{
    private final int CO;
    private final String CP;
    private final byte[] CQ;
    private final boolean CR;
    private final String CS;
    private final byte[] CT;
    
    public a(final AppState appState) {
        this.CO = appState.getKey();
        this.CP = appState.getLocalVersion();
        this.CQ = appState.getLocalData();
        this.CR = appState.hasConflict();
        this.CS = appState.getConflictVersion();
        this.CT = appState.getConflictData();
    }
    
    static int a(final AppState appState) {
        return m.hashCode(appState.getKey(), appState.getLocalVersion(), appState.getLocalData(), appState.hasConflict(), appState.getConflictVersion(), appState.getConflictData());
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
                if (m.equal(appState2.getKey(), appState.getKey()) && m.equal(appState2.getLocalVersion(), appState.getLocalVersion()) && m.equal(appState2.getLocalData(), appState.getLocalData()) && m.equal(appState2.hasConflict(), appState.hasConflict()) && m.equal(appState2.getConflictVersion(), appState.getConflictVersion())) {
                    b2 = b;
                    if (m.equal(appState2.getConflictData(), appState.getConflictData())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final AppState appState) {
        return m.h(appState).a("Key", appState.getKey()).a("LocalVersion", appState.getLocalVersion()).a("LocalData", appState.getLocalData()).a("HasConflict", appState.hasConflict()).a("ConflictVersion", appState.getConflictVersion()).a("ConflictData", appState.getConflictData()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public AppState fp() {
        return this;
    }
    
    @Override
    public byte[] getConflictData() {
        return this.CT;
    }
    
    @Override
    public String getConflictVersion() {
        return this.CS;
    }
    
    @Override
    public int getKey() {
        return this.CO;
    }
    
    @Override
    public byte[] getLocalData() {
        return this.CQ;
    }
    
    @Override
    public String getLocalVersion() {
        return this.CP;
    }
    
    @Override
    public boolean hasConflict() {
        return this.CR;
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
