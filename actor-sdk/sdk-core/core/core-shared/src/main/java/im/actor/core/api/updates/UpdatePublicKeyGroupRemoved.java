package im.actor.core.api.updates;
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
import im.actor.core.api.*;

public class UpdatePublicKeyGroupRemoved extends Update {

    public static final int HEADER = 0x69;
    public static UpdatePublicKeyGroupRemoved fromBytes(byte[] data) throws IOException {
        return Bser.parse(new UpdatePublicKeyGroupRemoved(), data);
    }

    private int uid;
    private int keyGroupId;

    public UpdatePublicKeyGroupRemoved(int uid, int keyGroupId) {
        this.uid = uid;
        this.keyGroupId = keyGroupId;
    }

    public UpdatePublicKeyGroupRemoved() {

    }

    public int getUid() {
        return this.uid;
    }

    public int getKeyGroupId() {
        return this.keyGroupId;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.uid = values.getInt(1);
        this.keyGroupId = values.getInt(2);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeInt(1, this.uid);
        writer.writeInt(2, this.keyGroupId);
    }

    @Override
    public String toString() {
        String res = "update PublicKeyGroupRemoved{";
        res += "uid=" + this.uid;
        res += ", keyGroupId=" + this.keyGroupId;
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}
