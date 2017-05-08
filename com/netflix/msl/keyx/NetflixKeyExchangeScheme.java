// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

public class NetflixKeyExchangeScheme extends KeyExchangeScheme
{
    public static final KeyExchangeScheme ANYCAST;
    public static final KeyExchangeScheme AUTHENTICATED_DH;
    public static final KeyExchangeScheme CDM;
    public static final KeyExchangeScheme WIDEVINE;
    
    static {
        AUTHENTICATED_DH = new NetflixKeyExchangeScheme("AUTHENTICATED_DH");
        WIDEVINE = new NetflixKeyExchangeScheme("WIDEVINE");
        ANYCAST = new NetflixKeyExchangeScheme("ANYCAST");
        CDM = new NetflixKeyExchangeScheme("CDM");
    }
    
    protected NetflixKeyExchangeScheme(final String s) {
        super(s);
    }
}
