// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import com.netflix.msl.io.LZWInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import com.netflix.msl.io.LZWOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.netflix.msl.MslException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslConstants$CompressionAlgorithm;

public class MslUtils
{
    public static byte[] compress(final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm, final byte[] array) {
        while (true) {
            Label_0132: {
            Label_0099:
                while (true) {
                    Label_0167: {
                        try {
                            switch (MslUtils$1.$SwitchMap$com$netflix$msl$MslConstants$CompressionAlgorithm[mslConstants$CompressionAlgorithm.ordinal()]) {
                                case 1: {}
                                case 2: {
                                    break Label_0132;
                                }
                                default: {
                                    break Label_0167;
                                }
                            }
                            throw new MslException(MslError.UNSUPPORTED_COMPRESSION, mslConstants$CompressionAlgorithm.name());
                        }
                        catch (IOException ex) {
                            try {
                                throw new MslException(MslError.COMPRESSION_ERROR, "algo " + mslConstants$CompressionAlgorithm.name() + " data " + Base64.encode(array), ex);
                            }
                            finally {}
                        }
                        break Label_0099;
                    }
                    continue;
                }
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(array.length);
                final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gzipOutputStream.write(array);
                gzipOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            final ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(array.length);
            final LZWOutputStream lzwOutputStream = new LZWOutputStream(byteArrayOutputStream2);
            lzwOutputStream.write(array);
            lzwOutputStream.close();
            return byteArrayOutputStream2.toByteArray();
        }
    }
    
    public static boolean safeEquals(final byte[] array, final byte[] array2) {
        if (array.length == array2.length) {
            int i = 0;
            byte b = 0;
            while (i < array.length) {
                b |= (byte)(array[i] ^ array2[i]);
                ++i;
            }
            if (b == 0) {
                return true;
            }
        }
        return false;
    }
    
    public static byte[] uncompress(final MslConstants$CompressionAlgorithm mslConstants$CompressionAlgorithm, final byte[] array) {
        while (true) {
            while (true) {
                Label_0256: {
                    Label_0176: {
                        Label_0096: {
                            try {
                                switch (MslUtils$1.$SwitchMap$com$netflix$msl$MslConstants$CompressionAlgorithm[mslConstants$CompressionAlgorithm.ordinal()]) {
                                    case 1: {
                                        break Label_0096;
                                    }
                                    case 2: {
                                        break Label_0176;
                                    }
                                    default: {
                                        break Label_0256;
                                    }
                                }
                                throw new MslException(MslError.UNSUPPORTED_COMPRESSION, mslConstants$CompressionAlgorithm.name());
                            }
                            catch (IOException ex) {
                                throw new MslException(MslError.UNCOMPRESSION_ERROR, "algo " + mslConstants$CompressionAlgorithm.name() + " data " + Base64.encode(array), ex);
                            }
                        }
                        final GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(array));
                        try {
                            final byte[] array2 = new byte[array.length];
                            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(array.length);
                            while (true) {
                                final int read = gzipInputStream.read(array2);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(array2, 0, read);
                            }
                            return byteArrayOutputStream.toByteArray();
                        }
                        finally {
                            gzipInputStream.close();
                        }
                    }
                    final LZWInputStream lzwInputStream = new LZWInputStream(new ByteArrayInputStream(array));
                    try {
                        final byte[] array3 = new byte[array.length];
                        final ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(array.length);
                        while (true) {
                            final int read2 = lzwInputStream.read(array3);
                            if (read2 == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(array3, 0, read2);
                        }
                        return byteArrayOutputStream2.toByteArray();
                    }
                    finally {
                        lzwInputStream.close();
                    }
                }
                continue;
            }
        }
    }
}
