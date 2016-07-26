package im.actor.core.api;
/*
 *  Generated by the Actor API Scheme generator.  DO NOT EDIT!
 */

import im.actor.runtime.bser.*;
import im.actor.runtime.collections.*;
import static im.actor.runtime.bser.Utils.*;
import im.actor.core.network.parser.*;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.google.j2objc.annotations.ObjectiveCName;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ApiRawValueUnsupported extends ApiRawValue {

    private int key;
    private byte[] content;

    public ApiRawValueUnsupported(int key, byte[] content) {
        this.key = key;
        this.content = content;
    }

    @Override
    public int getHeader() {
        return this.key;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        throw new IOException("Parsing is unsupported");
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeRaw(content);
    }

}
