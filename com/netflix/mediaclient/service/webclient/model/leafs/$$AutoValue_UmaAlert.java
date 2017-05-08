// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

abstract class $$AutoValue_UmaAlert extends UmaAlert
{
    private final int abTestCell;
    private final int abTestId;
    private final boolean blocking;
    private final String body;
    private final UmaCta cta1;
    private final UmaCta cta2;
    private final String locale;
    private final long messageId;
    private final String messageName;
    private final long timestamp;
    private final String title;
    private final String viewType;
    
    $$AutoValue_UmaAlert(final int abTestCell, final int abTestId, final String locale, final String messageName, final long messageId, final String viewType, final boolean blocking, final String title, final String body, final UmaCta cta1, final UmaCta cta2, final long timestamp) {
        this.abTestCell = abTestCell;
        this.abTestId = abTestId;
        this.locale = locale;
        this.messageName = messageName;
        this.messageId = messageId;
        this.viewType = viewType;
        this.blocking = blocking;
        this.title = title;
        this.body = body;
        this.cta1 = cta1;
        this.cta2 = cta2;
        this.timestamp = timestamp;
    }
    
    @Override
    public int abTestCell() {
        return this.abTestCell;
    }
    
    @Override
    public int abTestId() {
        return this.abTestId;
    }
    
    @Override
    public boolean blocking() {
        return this.blocking;
    }
    
    @Override
    public String body() {
        return this.body;
    }
    
    @Override
    public UmaCta cta1() {
        return this.cta1;
    }
    
    @Override
    public UmaCta cta2() {
        return this.cta2;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o instanceof UmaAlert) {
                final UmaAlert umaAlert = (UmaAlert)o;
                if (this.abTestCell == umaAlert.abTestCell() && this.abTestId == umaAlert.abTestId()) {
                    if (this.locale == null) {
                        if (umaAlert.locale() != null) {
                            return false;
                        }
                    }
                    else if (!this.locale.equals(umaAlert.locale())) {
                        return false;
                    }
                    if (this.messageName == null) {
                        if (umaAlert.messageName() != null) {
                            return false;
                        }
                    }
                    else if (!this.messageName.equals(umaAlert.messageName())) {
                        return false;
                    }
                    if (this.messageId == umaAlert.messageId()) {
                        if (this.viewType == null) {
                            if (umaAlert.viewType() != null) {
                                return false;
                            }
                        }
                        else if (!this.viewType.equals(umaAlert.viewType())) {
                            return false;
                        }
                        if (this.blocking == umaAlert.blocking()) {
                            if (this.title == null) {
                                if (umaAlert.title() != null) {
                                    return false;
                                }
                            }
                            else if (!this.title.equals(umaAlert.title())) {
                                return false;
                            }
                            if (this.body == null) {
                                if (umaAlert.body() != null) {
                                    return false;
                                }
                            }
                            else if (!this.body.equals(umaAlert.body())) {
                                return false;
                            }
                            if (this.cta1 == null) {
                                if (umaAlert.cta1() != null) {
                                    return false;
                                }
                            }
                            else if (!this.cta1.equals(umaAlert.cta1())) {
                                return false;
                            }
                            if (this.cta2 == null) {
                                if (umaAlert.cta2() != null) {
                                    return false;
                                }
                            }
                            else if (!this.cta2.equals(umaAlert.cta2())) {
                                return false;
                            }
                            if (this.timestamp == umaAlert.timestamp()) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int abTestCell = this.abTestCell;
        final int abTestId = this.abTestId;
        int hashCode2;
        if (this.locale == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.locale.hashCode();
        }
        int hashCode3;
        if (this.messageName == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.messageName.hashCode();
        }
        final int n = (int)((hashCode3 ^ (hashCode2 ^ ((abTestCell ^ 0xF4243) * 1000003 ^ abTestId) * 1000003) * 1000003) * 1000003 ^ (this.messageId >>> 32 ^ this.messageId));
        int hashCode4;
        if (this.viewType == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = this.viewType.hashCode();
        }
        int n2;
        if (this.blocking) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        int hashCode5;
        if (this.title == null) {
            hashCode5 = 0;
        }
        else {
            hashCode5 = this.title.hashCode();
        }
        int hashCode6;
        if (this.body == null) {
            hashCode6 = 0;
        }
        else {
            hashCode6 = this.body.hashCode();
        }
        int hashCode7;
        if (this.cta1 == null) {
            hashCode7 = 0;
        }
        else {
            hashCode7 = this.cta1.hashCode();
        }
        if (this.cta2 != null) {
            hashCode = this.cta2.hashCode();
        }
        return (int)(((hashCode7 ^ (hashCode6 ^ (hashCode5 ^ (n2 ^ (hashCode4 ^ n * 1000003) * 1000003) * 1000003) * 1000003) * 1000003) * 1000003 ^ hashCode) * 1000003 ^ (this.timestamp >>> 32 ^ this.timestamp));
    }
    
    @Override
    public String locale() {
        return this.locale;
    }
    
    @Override
    public long messageId() {
        return this.messageId;
    }
    
    @Override
    public String messageName() {
        return this.messageName;
    }
    
    @Override
    public long timestamp() {
        return this.timestamp;
    }
    
    @Override
    public String title() {
        return this.title;
    }
    
    @Override
    public String toString() {
        return "UmaAlert{abTestCell=" + this.abTestCell + ", abTestId=" + this.abTestId + ", locale=" + this.locale + ", messageName=" + this.messageName + ", messageId=" + this.messageId + ", viewType=" + this.viewType + ", blocking=" + this.blocking + ", title=" + this.title + ", body=" + this.body + ", cta1=" + this.cta1 + ", cta2=" + this.cta2 + ", timestamp=" + this.timestamp + "}";
    }
    
    @Override
    public String viewType() {
        return this.viewType;
    }
}
