// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.drive.UserMetadata;

public class m extends j<UserMetadata>
{
    public m(final String s, final int n) {
        super(s, bm(s), Collections.emptyList(), n);
    }
    
    private String bl(final String s) {
        return r(this.getName(), s);
    }
    
    private static Collection<String> bm(final String s) {
        return Arrays.asList(r(s, "permissionId"), r(s, "displayName"), r(s, "picture"), r(s, "isAuthenticatedUser"), r(s, "emailAddress"));
    }
    
    private static String r(final String s, final String s2) {
        return s + "." + s2;
    }
    
    @Override
    protected boolean b(final DataHolder dataHolder, final int n, final int n2) {
        return !dataHolder.h(this.bl("permissionId"), n, n2);
    }
    
    protected UserMetadata j(final DataHolder dataHolder, final int n, final int n2) {
        final String c = dataHolder.c(this.bl("permissionId"), n, n2);
        if (c != null) {
            return new UserMetadata(c, dataHolder.c(this.bl("displayName"), n, n2), dataHolder.c(this.bl("picture"), n, n2), Boolean.valueOf(dataHolder.d(this.bl("isAuthenticatedUser"), n, n2)), dataHolder.c(this.bl("emailAddress"), n, n2));
        }
        return null;
    }
}
