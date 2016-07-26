package im.actor.sdk.controllers.dialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import im.actor.core.entity.Dialog;
import im.actor.core.entity.PeerType;
import im.actor.core.viewmodel.CommandCallback;
import im.actor.core.viewmodel.GroupVM;
import im.actor.runtime.actors.messages.Void;
import im.actor.sdk.ActorSDK;
import im.actor.sdk.ActorSDKLauncher;
import im.actor.sdk.R;
import im.actor.sdk.controllers.Intents;

import static im.actor.sdk.util.ActorSDKMessenger.groups;
import static im.actor.sdk.util.ActorSDKMessenger.messenger;

public class DialogsDefaultFragment extends BaseDialogFragment {

    @Override
    protected void onItemClick(Dialog item) {
        Activity activity = getActivity();
        if (activity != null) {
            startActivity(Intents.openDialog(item.getPeer(), false, activity));
        }
    }

    @Override
    protected boolean onItemLongClick(Dialog dialog) {
        if (dialog.getPeer().getPeerType() == PeerType.PRIVATE) {
            new AlertDialog.Builder(getActivity())
                    .setItems(new CharSequence[]{
                            getString(R.string.dialogs_menu_contact_view),
                            getString(R.string.dialogs_menu_contact_rename),
                            getString(R.string.dialogs_menu_conversation_delete)
                    }, (d, which) -> {
                        if (which == 0) {
                            // View profile
                            ActorSDKLauncher.startProfileActivity(getActivity(), dialog.getPeer().getPeerId());
                        } else if (which == 1) {
                            // Rename user
                            startActivity(Intents.editUserName(dialog.getPeer().getPeerId(), getActivity()));
                        } else if (which == 2) {
                            // Delete chat
                            new AlertDialog.Builder(getActivity())
                                    .setMessage(getString(R.string.alert_delete_chat_message, dialog.getDialogTitle()))
                                    .setNegativeButton(R.string.dialog_cancel, null)
                                    .setPositiveButton(R.string.alert_delete_chat_yes, (d1, which1) -> {
                                        execute(messenger().deleteChat(dialog.getPeer()), R.string.progress_common,
                                                new CommandCallback<Void>() {
                                                    @Override
                                                    public void onResult(Void res) {

                                                    }

                                                    @Override
                                                    public void onError(Exception e) {
                                                        Toast.makeText(getActivity(), R.string.toast_unable_delete_chat, Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                    })
                                    .show();
                        }
                    })
                    .show();

            return true;
        } else if (dialog.getPeer().getPeerType() == PeerType.GROUP) {
            GroupVM groupVM = groups().get(dialog.getPeer().getPeerId());
            final boolean isMember = groupVM.isMember().get();

            new AlertDialog.Builder(getActivity())
                    .setItems(new CharSequence[]{
                            getString(R.string.dialogs_menu_group_view),
                            getString(R.string.dialogs_menu_group_rename),
                            isMember ? getString(R.string.dialogs_menu_group_leave)
                                    : getString(R.string.dialogs_menu_group_delete),
                    }, (d, which) -> {
                        if (which == 0) {
                            ActorSDK.sharedActor().startGroupInfoActivity(getActivity(), dialog.getPeer().getPeerId());
                        } else if (which == 1) {
                            startActivity(Intents.editGroupTitle(dialog.getPeer().getPeerId(), getActivity()));
                        } else if (which == 2) {
                            if (isMember) {
                                new AlertDialog.Builder(getActivity())
                                        .setMessage(getString(R.string.alert_leave_group_message, dialog.getDialogTitle()))
                                        .setNegativeButton(R.string.dialog_cancel, null)
                                        .setPositiveButton(R.string.alert_leave_group_yes, (d1, which1) -> {
                                            execute(messenger().leaveGroup(dialog.getPeer().getPeerId()), R.string.progress_common,
                                                    new CommandCallback<Void>() {
                                                        @Override
                                                        public void onResult(Void res) {

                                                        }

                                                        @Override
                                                        public void onError(Exception e) {
                                                            Toast.makeText(getActivity(), R.string.toast_unable_leave, Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                        })
                                        .show();
                            } else {
                                new AlertDialog.Builder(getActivity())
                                        .setMessage(getString(R.string.alert_delete_group_title, groupVM.getName().get()))
                                        .setNegativeButton(R.string.dialog_cancel, null)
                                        .setPositiveButton(R.string.alert_delete_group_yes, (d1, which1) -> {
                                            execute(messenger().deleteChat(dialog.getPeer()), R.string.progress_common,
                                                    new CommandCallback<Void>() {
                                                        @Override
                                                        public void onResult(Void res) {

                                                        }

                                                        @Override
                                                        public void onError(Exception e) {
                                                            Toast.makeText(getActivity(), R.string.toast_unable_delete_chat, Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                        })
                                        .show();
                            }
                        }
                    }).show();
            return true;
        }
        return false;
    }
}
