// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import android.content.Context;

public enum OfflineUnavailableReason
{
    NA_DISABLED_FROM_END_POINT(3), 
    NA_MSL_CLIENT_DISABLED(2), 
    NA_NO_EXTERNAL_STORAGE(5), 
    NA_OFFLINE_STORAGE_NOT_AVAILABLE(1), 
    NA_WIDE_VINE_UNRECOVERABLE(4);
    
    private final int mValue;
    
    private OfflineUnavailableReason(final int mValue) {
        this.mValue = mValue;
    }
    
    public static String getDiagnosticErrorMsg(final OfflineUnavailableReason offlineUnavailableReason, final Context context) {
        final String format = String.format("(%s)", offlineUnavailableReason.getCodeForLogblob());
        switch (OfflineUnavailableReason$1.$SwitchMap$com$netflix$mediaclient$service$offline$agent$OfflineUnavailableReason[offlineUnavailableReason.ordinal()]) {
            default: {
                return "";
            }
            case 1: {
                return context.getString(2131296940, new Object[] { format });
            }
            case 2: {
                return context.getString(2131296948, new Object[] { format });
            }
            case 3: {
                return context.getString(2131296876, new Object[] { format });
            }
            case 4: {
                return context.getString(2131296947, new Object[] { format });
            }
            case 5: {
                return context.getString(2131296875, new Object[] { format });
            }
        }
    }
    
    public String getCodeForLogblob() {
        return "OF.NA." + this.mValue;
    }
}
