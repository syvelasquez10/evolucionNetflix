// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

public class NetflixUrlProviderFactory
{
    public static NetflixUrlProvider of(final NetflixEnvironment netflixEnvironment) {
        switch (NetflixUrlProviderFactory$1.$SwitchMap$com$netflix$msl$client$NetflixEnvironment[netflixEnvironment.ordinal()]) {
            default: {
                throw new IllegalStateException("Unknown environment");
            }
            case 1: {
                return new NetflixUrlProviderFactory$ProductionNetflixUrlProvider();
            }
            case 2: {
                return new NetflixUrlProviderFactory$TestNetflixUrlProvider();
            }
        }
    }
}
