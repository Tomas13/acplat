update dialogs d set last_read_at = now(), owner_last_received_at = now() where d.peer_type = 2 and d.peer_id not in (select group_id from group_users gu where gu.user_id = d.user_id);