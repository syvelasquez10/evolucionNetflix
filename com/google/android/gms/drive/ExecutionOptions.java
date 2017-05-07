// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.q;
import com.google.android.gms.common.api.GoogleApiClient;

public final class ExecutionOptions
{
    public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
    public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
    public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
    private final String Nf;
    private final boolean Ng;
    private final int Nh;
    
    private ExecutionOptions(final String nf, final boolean ng, final int nh) {
        this.Nf = nf;
        this.Ng = ng;
        this.Nh = nh;
    }
    
    public static void a(final GoogleApiClient googleApiClient, final ExecutionOptions executionOptions) {
        final q q = googleApiClient.a(Drive.CU);
        if (executionOptions.hP() && !q.ib()) {
            throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to be notified on completion");
        }
    }
    
    public static boolean aV(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                return true;
            }
        }
    }
    
    public static boolean aW(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 0:
            case 1: {
                return true;
            }
        }
    }
    
    public static boolean bh(final String s) {
        return s != null && !s.isEmpty() && s.length() <= 65536;
    }
    
    public String hO() {
        return this.Nf;
    }
    
    public boolean hP() {
        return this.Ng;
    }
    
    public int hQ() {
        return this.Nh;
    }
}
