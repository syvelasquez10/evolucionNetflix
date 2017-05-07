// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.List;

class Session$PermissionsPair
{
    List<String> declinedPermissions;
    List<String> grantedPermissions;
    
    public Session$PermissionsPair(final List<String> grantedPermissions, final List<String> declinedPermissions) {
        this.grantedPermissions = grantedPermissions;
        this.declinedPermissions = declinedPermissions;
    }
    
    public List<String> getDeclinedPermissions() {
        return this.declinedPermissions;
    }
    
    public List<String> getGrantedPermissions() {
        return this.grantedPermissions;
    }
}
