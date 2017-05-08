// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public enum ApplicationPerformanceMetricsLogging$UiStartupTrigger
{
    appSwitch(23), 
    bannerAd(9), 
    channelNumber(18), 
    dedicatedOnScreenIcon(2), 
    dial(12), 
    epgGrid(17), 
    externalApp(8), 
    externalControlProtocol(10), 
    itemInApplicationList(3), 
    liveFolder(7), 
    metaDiscovery(11), 
    powerOnFromNetflixButton(19), 
    remoteButton(1), 
    searchContinuation(5), 
    searchResult(4), 
    suspendAfterAppRestart(22), 
    suspendAtPowerOn(22), 
    testing(99), 
    titleRecommend(21), 
    touchGesture(15), 
    virtualRemote(16), 
    visualGesture(14), 
    voiceControl(13), 
    webBrowser(6);
    
    private int sourceType;
    
    private ApplicationPerformanceMetricsLogging$UiStartupTrigger(final int sourceType) {
        this.sourceType = sourceType;
    }
    
    public static ApplicationPerformanceMetricsLogging$UiStartupTrigger lookup(final int n) {
        final ApplicationPerformanceMetricsLogging$UiStartupTrigger[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final ApplicationPerformanceMetricsLogging$UiStartupTrigger applicationPerformanceMetricsLogging$UiStartupTrigger = values[i];
            if (applicationPerformanceMetricsLogging$UiStartupTrigger.getSourceType() == n) {
                return applicationPerformanceMetricsLogging$UiStartupTrigger;
            }
        }
        return null;
    }
    
    public int getSourceType() {
        return this.sourceType;
    }
}
