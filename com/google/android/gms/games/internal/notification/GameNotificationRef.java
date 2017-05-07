// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.notification;

import com.google.android.gms.internal.fo;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class GameNotificationRef extends b implements GameNotification
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
    
    public String hp() {
        return this.getString("notification_id");
    }
    
    public String hq() {
        return this.getString("ticker");
    }
    
    public String hr() {
        return this.getString("coalesced_text");
    }
    
    public boolean hs() {
        return this.getInteger("acknowledged") > 0;
    }
    
    public boolean ht() {
        return this.getInteger("alert_level") == 0;
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("Id", this.getId()).a("NotificationId", this.hp()).a("Type", this.getType()).a("Title", this.getTitle()).a("Ticker", this.hq()).a("Text", this.getText()).a("CoalescedText", this.hr()).a("isAcknowledged", this.hs()).a("isSilent", this.ht()).toString();
    }
}
