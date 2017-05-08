// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

public class NetflixUserAuthenticationScheme extends UserAuthenticationScheme
{
    public static final UserAuthenticationScheme MDX;
    public static final UserAuthenticationScheme NETFLIXID;
    public static final UserAuthenticationScheme SSO;
    public static final UserAuthenticationScheme SWITCH_PROFILE;
    
    static {
        NETFLIXID = new NetflixUserAuthenticationScheme("NETFLIXID");
        SSO = new NetflixUserAuthenticationScheme("SSO");
        SWITCH_PROFILE = new NetflixUserAuthenticationScheme("SWITCH_PROFILE");
        MDX = new NetflixUserAuthenticationScheme("MDX");
    }
    
    protected NetflixUserAuthenticationScheme(final String s) {
        super(s);
    }
}
