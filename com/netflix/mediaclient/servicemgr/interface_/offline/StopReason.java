// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

public enum StopReason
{
    AccountInActive(11, false), 
    EncodesAreNotAvailableAnyMore(101, true), 
    GeoCheckError(103, true), 
    ManifestError(102, true), 
    NetworkError(2, true), 
    NoNetworkConnectivity(7, false), 
    NotAllowedOnCurrentNetwork(6, false), 
    NotEnoughSpace(4, true), 
    PlayerStreaming(10, false), 
    StoppedFromAgentAPI(5, false), 
    StorageError(3, true), 
    Unknown(0, false), 
    WaitingToBeStarted(1, false);
    
    private final boolean mShowBangIconErrorInUi;
    private final int mValue;
    
    private StopReason(final int mValue, final boolean mShowBangIconErrorInUi) {
        this.mValue = mValue;
        this.mShowBangIconErrorInUi = mShowBangIconErrorInUi;
    }
    
    public static StopReason getStopReasonByValue(final int n) {
        final StopReason[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final StopReason stopReason = values[i];
            if (stopReason.getIntValue() == n) {
                return stopReason;
            }
        }
        return StopReason.Unknown;
    }
    
    public boolean canResumeWithoutUserAction() {
        return this.getIntValue() < 100;
    }
    
    public int getIntValue() {
        return this.mValue;
    }
    
    public boolean showBangIconErrorInUi() {
        return this.mShowBangIconErrorInUi;
    }
}
