// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.UUID;
import android.util.Pair;

public final class PsshAtomUtil
{
    private static Pair<UUID, byte[]> parsePsshAtom(final byte[] array) {
        final ParsableByteArray parsableByteArray = new ParsableByteArray(array);
        if (parsableByteArray.limit() >= 32) {
            parsableByteArray.setPosition(0);
            if (parsableByteArray.readInt() == parsableByteArray.bytesLeft() + 4 && parsableByteArray.readInt() == Atom.TYPE_pssh) {
                final int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                if (fullAtomVersion > 1) {
                    Log.w("PsshAtomUtil", "Unsupported pssh version: " + fullAtomVersion);
                    return null;
                }
                final UUID uuid = new UUID(parsableByteArray.readLong(), parsableByteArray.readLong());
                if (fullAtomVersion == 1) {
                    parsableByteArray.skipBytes(parsableByteArray.readUnsignedIntToInt() * 16);
                }
                final int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                if (unsignedIntToInt == parsableByteArray.bytesLeft()) {
                    final byte[] array2 = new byte[unsignedIntToInt];
                    parsableByteArray.readBytes(array2, 0, unsignedIntToInt);
                    return (Pair<UUID, byte[]>)Pair.create((Object)uuid, (Object)array2);
                }
            }
        }
        return null;
    }
    
    public static UUID parseUuid(final byte[] array) {
        final Pair<UUID, byte[]> psshAtom = parsePsshAtom(array);
        if (psshAtom == null) {
            return null;
        }
        return (UUID)psshAtom.first;
    }
}
