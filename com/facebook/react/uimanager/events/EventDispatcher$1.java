// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import java.util.Comparator;

final class EventDispatcher$1 implements Comparator<Event>
{
    @Override
    public int compare(final Event event, final Event event2) {
        if (event != null || event2 != null) {
            if (event == null) {
                return -1;
            }
            if (event2 == null) {
                return 1;
            }
            final long n = event.getTimestampMs() - event2.getTimestampMs();
            if (n != 0L) {
                if (n < 0L) {
                    return -1;
                }
                return 1;
            }
        }
        return 0;
    }
}
