// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import com.google.android.gms.games.Player;
import java.util.List;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface GameRequest extends Parcelable, Freezable<GameRequest>
{
    public static final int RECIPIENT_STATUS_ACCEPTED = 1;
    public static final int RECIPIENT_STATUS_PENDING = 0;
    public static final int STATUS_ACCEPTED = 1;
    public static final int STATUS_PENDING = 0;
    public static final int TYPE_ALL = 65535;
    public static final int TYPE_GIFT = 1;
    public static final int TYPE_WISH = 2;
    
    long getCreationTimestamp();
    
    byte[] getData();
    
    long getExpirationTimestamp();
    
    Game getGame();
    
    int getRecipientStatus(final String p0);
    
    List<Player> getRecipients();
    
    String getRequestId();
    
    Player getSender();
    
    int getStatus();
    
    int getType();
    
    boolean isConsumed(final String p0);
}
