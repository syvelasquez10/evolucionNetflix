// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.internal.ee;

public final class a implements AppState
{
    private final int jI;
    private final String jJ;
    private final byte[] jK;
    private final boolean jL;
    private final String jM;
    private final byte[] jN;
    
    public a(final AppState appState) {
        this.jI = appState.getKey();
        this.jJ = appState.getLocalVersion();
        this.jK = appState.getLocalData();
        this.jL = appState.hasConflict();
        this.jM = appState.getConflictVersion();
        this.jN = appState.getConflictData();
    }
    
    static int a(final AppState appState) {
        return ee.hashCode(appState.getKey(), appState.getLocalVersion(), appState.getLocalData(), appState.hasConflict(), appState.getConflictVersion(), appState.getConflictData());
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
                if (ee.equal(appState2.getKey(), appState.getKey()) && ee.equal(appState2.getLocalVersion(), appState.getLocalVersion()) && ee.equal(appState2.getLocalData(), appState.getLocalData()) && ee.equal(appState2.hasConflict(), appState.hasConflict()) && ee.equal(appState2.getConflictVersion(), appState.getConflictVersion())) {
                    b2 = b;
                    if (ee.equal(appState2.getConflictData(), appState.getConflictData())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final AppState appState) {
        return ee.e(appState).a("Key", appState.getKey()).a("LocalVersion", appState.getLocalVersion()).a("LocalData", appState.getLocalData()).a("HasConflict", appState.hasConflict()).a("ConflictVersion", appState.getConflictVersion()).a("ConflictData", appState.getConflictData()).toString();
    }
    
    public AppState aK() {
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    @Override
    public byte[] getConflictData() {
        return this.jN;
    }
    
    @Override
    public String getConflictVersion() {
        return this.jM;
    }
    
    @Override
    public int getKey() {
        return this.jI;
    }
    
    @Override
    public byte[] getLocalData() {
        return this.jK;
    }
    
    @Override
    public String getLocalVersion() {
        return this.jJ;
    }
    
    @Override
    public boolean hasConflict() {
        return this.jL;
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
