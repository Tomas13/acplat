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

public class UpdateGroupTitleChanged extends Update {

    public static final int HEADER = 0xa31;
    public static UpdateGroupTitleChanged fromBytes(byte[] data) throws IOException {
        return Bser.parse(new UpdateGroupTitleChanged(), data);
    }

    private int groupId;
    private String title;

    public UpdateGroupTitleChanged(int groupId, @NotNull String title) {
        this.groupId = groupId;
        this.title = title;
    }

    public UpdateGroupTitleChanged() {

    }

    public int getGroupId() {
        return this.groupId;
    }

    @NotNull
    public String getTitle() {
        return this.title;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.groupId = values.getInt(1);
        this.title = values.getString(2);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeInt(1, this.groupId);
        if (this.title == null) {
            throw new IOException();
        }
        writer.writeString(2, this.title);
    }

    @Override
    public String toString() {
        String res = "update GroupTitleChanged{";
        res += "groupId=" + this.groupId;
        res += ", title=" + this.title;
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}