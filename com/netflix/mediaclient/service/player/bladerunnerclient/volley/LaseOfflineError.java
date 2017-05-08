// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

public enum LaseOfflineError
{
    AggregateError(21001), 
    ClientUsageError(25003), 
    DataMissError(24004), 
    DataWriteError(24005), 
    DependencyCommandError(25002), 
    DeviceAggregateError(23001), 
    DeviceNotActiveError(24006), 
    EventStoreError(21002), 
    IOError(25001), 
    LicenseAggregateError(22001), 
    LicenseIdMismatch(24001), 
    LicenseNotActive(26008), 
    LicenseNotMarkedPlayable(24000), 
    LicenseReleasedError(24002), 
    LicenseTooOld(24003), 
    MembershipError(22000), 
    NOT_KNOWN_TO_CLIENT(-7777), 
    OfflineDeviceLimitReached(23000), 
    PackageRevokedError(24008), 
    PlayableAggregateError(26001), 
    RequestTypeError(21003), 
    ServerError(25000), 
    StudioOfflineTitleLimitReached(22005), 
    TitleNotAvailableForOffline(22004), 
    TotalLicensesPerAccountReached(22003), 
    TotalLicensesPerDeviceReached(22002), 
    Undefined(21000), 
    ViewableNotAvailableInRegion(24007), 
    YearlyStudioDownloadLimitReached(22006), 
    YearlyStudioLicenseLimitReached(22007), 
    viewingWindowExpired(22008);
    
    private final int mValue;
    
    private LaseOfflineError(final int mValue) {
        this.mValue = mValue;
    }
    
    public static LaseOfflineError getByValue(final int n) {
        final LaseOfflineError[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final LaseOfflineError laseOfflineError = values[i];
            if (laseOfflineError.getIntValue() == n) {
                return laseOfflineError;
            }
        }
        return LaseOfflineError.NOT_KNOWN_TO_CLIENT;
    }
    
    private int getIntValue() {
        return this.mValue;
    }
}
