// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.event;

import com.google.android.gms.common.data.Freezable;
import android.os.Parcel;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class EventEntity implements SafeParcelable, Event
{
    public static final EventEntityCreator CREATOR;
    private final int BR;
    private final String Tg;
    private final Uri UW;
    private final PlayerEntity VW;
    private final String Vh;
    private final String Wb;
    private final long Wc;
    private final String Wd;
    private final boolean We;
    private final String mName;
    
    static {
        CREATOR = new EventEntityCreator();
    }
    
    EventEntity(final int br, final String wb, final String mName, final String tg, final Uri uw, final String vh, final Player player, final long wc, final String wd, final boolean we) {
        this.BR = br;
        this.Wb = wb;
        this.mName = mName;
        this.Tg = tg;
        this.UW = uw;
        this.Vh = vh;
        this.VW = new PlayerEntity(player);
        this.Wc = wc;
        this.Wd = wd;
        this.We = we;
    }
    
    public EventEntity(final Event event) {
        this.BR = 1;
        this.Wb = event.getEventId();
        this.mName = event.getName();
        this.Tg = event.getDescription();
        this.UW = event.getIconImageUri();
        this.Vh = event.getIconImageUrl();
        this.VW = ((Freezable<PlayerEntity>)event.getPlayer()).freeze();
        this.Wc = event.getValue();
        this.Wd = event.getFormattedValue();
        this.We = event.isVisible();
    }
    
    static int a(final Event event) {
        return m.hashCode(event.getEventId(), event.getName(), event.getDescription(), event.getIconImageUri(), event.getIconImageUrl(), event.getPlayer(), event.getValue(), event.getFormattedValue(), event.isVisible());
    }
    
    static boolean a(final Event event, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Event)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (event != o) {
                final Event event2 = (Event)o;
                if (m.equal(event2.getEventId(), event.getEventId()) && m.equal(event2.getName(), event.getName()) && m.equal(event2.getDescription(), event.getDescription()) && m.equal(event2.getIconImageUri(), event.getIconImageUri()) && m.equal(event2.getIconImageUrl(), event.getIconImageUrl()) && m.equal(event2.getPlayer(), event.getPlayer()) && m.equal(event2.getValue(), event.getValue()) && m.equal(event2.getFormattedValue(), event.getFormattedValue())) {
                    b2 = b;
                    if (m.equal(event2.isVisible(), event.isVisible())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Event event) {
        return m.h(event).a("Id", event.getEventId()).a("Name", event.getName()).a("Description", event.getDescription()).a("IconImageUri", event.getIconImageUri()).a("IconImageUrl", event.getIconImageUrl()).a("Player", event.getPlayer()).a("Value", event.getValue()).a("FormattedValue", event.getFormattedValue()).a("isVisible", event.isVisible()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Event freeze() {
        return this;
    }
    
    @Override
    public String getDescription() {
        return this.Tg;
    }
    
    @Override
    public void getDescription(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.Tg, charArrayBuffer);
    }
    
    @Override
    public String getEventId() {
        return this.Wb;
    }
    
    @Override
    public String getFormattedValue() {
        return this.Wd;
    }
    
    @Override
    public void getFormattedValue(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.Wd, charArrayBuffer);
    }
    
    @Override
    public Uri getIconImageUri() {
        return this.UW;
    }
    
    @Override
    public String getIconImageUrl() {
        return this.Vh;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    @Override
    public void getName(final CharArrayBuffer charArrayBuffer) {
        jv.b(this.mName, charArrayBuffer);
    }
    
    @Override
    public Player getPlayer() {
        return this.VW;
    }
    
    @Override
    public long getValue() {
        return this.Wc;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean isVisible() {
        return this.We;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        EventEntityCreator.a(this, parcel, n);
    }
}
