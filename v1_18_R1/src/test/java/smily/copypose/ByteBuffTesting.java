package smily.copypose;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import static org.junit.Assert.*;

@Testable
public class ByteBuffTesting {
    FriendlyByteBuf friendlyByteBuf = new FriendlyByteBuf(Unpooled.buffer());

    @Test
    public void learningReaderIndex(){
        friendlyByteBuf.writeInt(20);
        friendlyByteBuf.writeInt(10);
        friendlyByteBuf.writeInt(10);
        friendlyByteBuf.writeByte(20);
        friendlyByteBuf.writeShort(10);
        friendlyByteBuf.writeShort(10);

        assertEquals(20, friendlyByteBuf.readInt());
        assertEquals(10, friendlyByteBuf.readInt());
        assertEquals(10, friendlyByteBuf.readInt());

        assertEquals(21, friendlyByteBuf.readByte() | 1);

        assertEquals(10, friendlyByteBuf.readShort());

        friendlyByteBuf.readByte();
        friendlyByteBuf.readByte();

        System.out.println(friendlyByteBuf.readerIndex());


        /*what reader index is it is an index per byte than has been read

        * Example above show you that lastly stop at reader index 11
        * How this works that it increase the index based on how many byte has been read

        Integer = 4 byte
        Short = 2 byte
        Byte = 1 byte

        * So in the above example, we read integer + integer + byte + short
        equals to: 4 + 4 + 1 + 2 = 11 byte
        */

        friendlyByteBuf.writeInt(10);
        friendlyByteBuf.readerIndex(friendlyByteBuf.readerIndex() + 3);
        assertEquals(10, friendlyByteBuf.readByte());

        friendlyByteBuf.readerIndex(3);
        assertEquals(20, friendlyByteBuf.readByte());

        // The other reader index method with parameter is setting the reader index to the specified index

        // My Comment on this is... please do better naming gess, how could i know another reader index is set to that reader index
        // OOP is created so code is much more understandable, and lack of human communication make that beauty dissapear
    }


    @Test
    public void learningHowByteRead(){
        friendlyByteBuf.writeInt(20);

        assertEquals(0, friendlyByteBuf.readByte());
        assertEquals(0, friendlyByteBuf.readByte());
        assertEquals(0, friendlyByteBuf.readByte());
        assertEquals(20, friendlyByteBuf.readByte());
        assertEquals(4, friendlyByteBuf.writerIndex());
    }
}
