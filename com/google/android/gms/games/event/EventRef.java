// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.event;

import android.os.Parcel;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.Player;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class EventRef extends d implements Event
{
    EventRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return EventEntity.a(this, o);
    }
    
    public Event freeze() {
        return new EventEntity(this);
    }
    
    @Override
    public String getDescription() {
        return this.getString("description");
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.a("description", charArrayBuffer);
    }
    
    @Override
    public String getEventId() {
        return this.getString("external_event_id");
    }
    
    @Override
    public String getFormattedValue() {
        return this.getString("formatted_value");
    }
    
    @Override
    public void getFormattedValue(final CharArrayBuffer charArrayBuffer) {
        this.a("formatted_value", charArrayBuffer);
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.aR("icon_image_uri");
    }
    
    @Override
    public String getIconImageUrl() {
        return this.getString("icon_image_url");
    }
    
    @Override
    public String getName() {
        return this.getString("name");
    }
    
    @Override
    public void getName(final CharArrayBuffer charArrayBuffer) {
        this.a("name", charArrayBuffer);
    }
    
    @Override
    public Player getPlayer() {
        return new PlayerRef(this.IC, this.JQ);
    }
    
    @Override
    public long getValue() {
        return this.getLong("value");
    }
    
    @Override
    public int hashCode() {
        return EventEntity.a(this);
    }
    
    @Override
    public boolean isVisible() {
        return this.getBoolean("visibility");
    }
    
    @Override
    public String toString() {
        return EventEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((EventEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
