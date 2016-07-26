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

public class ApiStickerDescriptor extends BserObject {

    private int id;
    private String emoji;
    private ApiImageLocation image128;
    private ApiImageLocation image512;
    private ApiImageLocation image256;

    public ApiStickerDescriptor(int id, @Nullable String emoji, @NotNull ApiImageLocation image128, @Nullable ApiImageLocation image512, @Nullable ApiImageLocation image256) {
        this.id = id;
        this.emoji = emoji;
        this.image128 = image128;
        this.image512 = image512;
        this.image256 = image256;
    }

    public ApiStickerDescriptor() {

    }

    public int getId() {
        return this.id;
    }

    @Nullable
    public String getEmoji() {
        return this.emoji;
    }

    @NotNull
    public ApiImageLocation getImage128() {
        return this.image128;
    }

    @Nullable
    public ApiImageLocation getImage512() {
        return this.image512;
    }

    @Nullable
    public ApiImageLocation getImage256() {
        return this.image256;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.id = values.getInt(1);
        this.emoji = values.optString(2);
        this.image128 = values.getObj(3, new ApiImageLocation());
        this.image512 = values.optObj(4, new ApiImageLocation());
        this.image256 = values.optObj(5, new ApiImageLocation());
        if (values.hasRemaining()) {
            setUnmappedObjects(values.buildRemaining());
        }
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeInt(1, this.id);
        if (this.emoji != null) {
            writer.writeString(2, this.emoji);
        }
        if (this.image128 == null) {
            throw new IOException();
        }
        writer.writeObject(3, this.image128);
        if (this.image512 != null) {
            writer.writeObject(4, this.image512);
        }
        if (this.image256 != null) {
            writer.writeObject(5, this.image256);
        }
        if (this.getUnmappedObjects() != null) {
            SparseArray<Object> unmapped = this.getUnmappedObjects();
            for (int i = 0; i < unmapped.size(); i++) {
                int key = unmapped.keyAt(i);
                writer.writeUnmapped(key, unmapped.get(key));
            }
        }
    }

    @Override
    public String toString() {
        String res = "struct StickerDescriptor{";
        res += "id=" + this.id;
        res += ", emoji=" + this.emoji;
        res += ", image128=" + this.image128;
        res += ", image512=" + this.image512;
        res += ", image256=" + this.image256;
        res += "}";
        return res;
    }

}
