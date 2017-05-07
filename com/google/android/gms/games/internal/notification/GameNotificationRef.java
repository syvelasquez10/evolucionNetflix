// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.notification;

import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class GameNotificationRef extends d implements GameNotification
{
    GameNotificationRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public long getId() {
        return this.getLong("_id");
    }
    
    public String getText() {
        return this.getString("text");
    }
    
    public String getTitle() {
        return this.getString("title");
    }
    
    public int getType() {
        return this.getInteger("type");
    }
    
    public String li() {
        return this.getString("notification_id");
    }
    
    public String lj() {
        return this.getString("ticker");
    }
    
    public String lk() {
        return this.getString("coalesced_text");
    }
    
    public boolean ll() {
        return this.getInteger("acknowledged") > 0;
    }
    
    public boolean lm() {
        return this.getInteger("alert_level") == 0;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("Id", this.getId()).a("NotificationId", this.li()).a("Type", this.getType()).a("Title", this.getTitle()).a("Ticker", this.lj()).a("Text", this.getText()).a("CoalescedText", this.lk()).a("isAcknowledged", this.ll()).a("isSilent", this.lm()).toString();
    }
}
