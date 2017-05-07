// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.games.Player;
import java.util.ArrayList;
import com.google.android.gms.internal.eg;

public final class ParticipantUtils
{
    public static boolean am(final String s) {
        eg.b(s, "Participant ID must not be null");
        return s.startsWith("p_");
    }
    
    public static String getParticipantId(final ArrayList<Participant> list, final String s) {
        for (int size = list.size(), i = 0; i < size; ++i) {
            final Participant participant = list.get(i);
            final Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(s)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }
}
