// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

public class NetflixEntityAuthenticationScheme extends EntityAuthenticationScheme
{
    public static final EntityAuthenticationScheme ANYCAST;
    public static final EntityAuthenticationScheme ECC;
    public static final EntityAuthenticationScheme MGK;
    public static final EntityAuthenticationScheme MGK_PROFILE;
    public static final EntityAuthenticationScheme NPTICKET;
    
    static {
        MGK = new NetflixEntityAuthenticationScheme("MGK", true, true);
        MGK_PROFILE = new NetflixEntityAuthenticationScheme("MGK_PROFILE", true, true);
        NPTICKET = new NetflixEntityAuthenticationScheme("NPTICKET", false, true);
        ECC = new NetflixEntityAuthenticationScheme("ECC", false, true);
        ANYCAST = new NetflixEntityAuthenticationScheme("ANYCAST", false, true);
    }
    
    protected NetflixEntityAuthenticationScheme(final String s, final boolean b, final boolean b2) {
        super(s, b, b2);
    }
}
