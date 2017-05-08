// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

abstract class $AutoValue_UmaCta extends UmaCta
{
    private final String action;
    private final String actionType;
    private final boolean autoLogin;
    private final String callback;
    private final boolean selected;
    private final String text;
    private final String trackingInfo;
    
    $AutoValue_UmaCta(final String text, final String action, final String actionType, final String callback, final String trackingInfo, final boolean selected, final boolean autoLogin) {
        this.text = text;
        this.action = action;
        this.actionType = actionType;
        this.callback = callback;
        this.trackingInfo = trackingInfo;
        this.selected = selected;
        this.autoLogin = autoLogin;
    }
    
    @Override
    public String action() {
        return this.action;
    }
    
    @Override
    public String actionType() {
        return this.actionType;
    }
    
    @Override
    public boolean autoLogin() {
        return this.autoLogin;
    }
    
    @Override
    public String callback() {
        return this.callback;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o instanceof UmaCta) {
                final UmaCta umaCta = (UmaCta)o;
                if (this.text == null) {
                    if (umaCta.text() != null) {
                        return false;
                    }
                }
                else if (!this.text.equals(umaCta.text())) {
                    return false;
                }
                if (this.action == null) {
                    if (umaCta.action() != null) {
                        return false;
                    }
                }
                else if (!this.action.equals(umaCta.action())) {
                    return false;
                }
                if (this.actionType == null) {
                    if (umaCta.actionType() != null) {
                        return false;
                    }
                }
                else if (!this.actionType.equals(umaCta.actionType())) {
                    return false;
                }
                if (this.callback == null) {
                    if (umaCta.callback() != null) {
                        return false;
                    }
                }
                else if (!this.callback.equals(umaCta.callback())) {
                    return false;
                }
                if (this.trackingInfo == null) {
                    if (umaCta.trackingInfo() != null) {
                        return false;
                    }
                }
                else if (!this.trackingInfo.equals(umaCta.trackingInfo())) {
                    return false;
                }
                if (this.selected == umaCta.selected() && this.autoLogin == umaCta.autoLogin()) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int n = 1231;
        int hashCode = 0;
        int hashCode2;
        if (this.text == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.text.hashCode();
        }
        int hashCode3;
        if (this.action == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.action.hashCode();
        }
        int hashCode4;
        if (this.actionType == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = this.actionType.hashCode();
        }
        int hashCode5;
        if (this.callback == null) {
            hashCode5 = 0;
        }
        else {
            hashCode5 = this.callback.hashCode();
        }
        if (this.trackingInfo != null) {
            hashCode = this.trackingInfo.hashCode();
        }
        int n2;
        if (this.selected) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        if (!this.autoLogin) {
            n = 1237;
        }
        return (n2 ^ ((hashCode5 ^ (hashCode4 ^ (hashCode3 ^ (hashCode2 ^ 0xF4243) * 1000003) * 1000003) * 1000003) * 1000003 ^ hashCode) * 1000003) * 1000003 ^ n;
    }
    
    @Override
    public boolean selected() {
        return this.selected;
    }
    
    @Override
    public String text() {
        return this.text;
    }
    
    @Override
    public String toString() {
        return "UmaCta{text=" + this.text + ", action=" + this.action + ", actionType=" + this.actionType + ", callback=" + this.callback + ", trackingInfo=" + this.trackingInfo + ", selected=" + this.selected + ", autoLogin=" + this.autoLogin + "}";
    }
    
    @Override
    public String trackingInfo() {
        return this.trackingInfo;
    }
}
