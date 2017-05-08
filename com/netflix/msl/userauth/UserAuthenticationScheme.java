// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserAuthenticationScheme
{
    public static final UserAuthenticationScheme EMAIL_PASSWORD;
    public static final UserAuthenticationScheme USER_ID_TOKEN;
    private static Map<String, UserAuthenticationScheme> schemes;
    private final String name;
    
    static {
        UserAuthenticationScheme.schemes = new HashMap<String, UserAuthenticationScheme>();
        EMAIL_PASSWORD = new UserAuthenticationScheme("EMAIL_PASSWORD");
        USER_ID_TOKEN = new UserAuthenticationScheme("USER_ID_TOKEN");
    }
    
    protected UserAuthenticationScheme(final String name) {
        this.name = name;
        synchronized (UserAuthenticationScheme.schemes) {
            UserAuthenticationScheme.schemes.put(name, this);
        }
    }
    
    public static UserAuthenticationScheme getScheme(final String s) {
        return UserAuthenticationScheme.schemes.get(s);
    }
    
    public static Collection<UserAuthenticationScheme> values() {
        return UserAuthenticationScheme.schemes.values();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof UserAuthenticationScheme && this.name.equals(((UserAuthenticationScheme)o).name));
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    public String name() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
}
