// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message;

import java.util.HashSet;

final class MdxMessage$1 extends HashSet<String>
{
    MdxMessage$1() {
        this.add("DIALOG_RESPONSE");
        this.add("PLAYER_PAUSE");
        this.add("PLAYER_PLAY");
        this.add("PLAYER_RESUME");
        this.add("PLAYER_SET_AUTO_ADVANCE");
        this.add("PLAYER_SET_CURRENT_TIME");
        this.add("PLAYER_SKIP");
        this.add("PLAYER_STOP");
        this.add("SET_AUDIO_SUBTITLES");
    }
}
