// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.event;

import com.google.android.gms.games.Player;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Event extends Parcelable, Freezable<Event>
{
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    String getEventId();
    
    String getFormattedValue();
    
    void getFormattedValue(final CharArrayBuffer p0);
    
    Uri getIconImageUri();
    
    @Deprecated
    String getIconImageUrl();
    
    String getName();
    
    void getName(final CharArrayBuffer p0);
    
    Player getPlayer();
    
    long getValue();
    
    boolean isVisible();
}
