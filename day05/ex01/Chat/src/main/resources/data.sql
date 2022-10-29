INSERT INTO users (user_login, user_pass)
VALUES  ('login1', 'pass1'),
        ('login2', 'pass2'),
        ('login3', 'pass3'),
        ('login4', 'pass4'),
        ('login5', 'pass5');

INSERT INTO chatrooms (chatroom_name, owner_id)
VALUES  ('chatroom1', (SELECT user_id FROM users WHERE user_login = 'login1' LIMIT 1)),
        ('chatroom2', (SELECT user_id FROM users WHERE user_login = 'login2' LIMIT 1)),
        ('chatroom3', (SELECT user_id FROM users WHERE user_login = 'login3' LIMIT 1)),
        ('chatroom4', (SELECT user_id FROM users WHERE user_login = 'login4' LIMIT 1)),
        ('chatroom5', (SELECT user_id FROM users WHERE user_login = 'login5' LIMIT 1));

INSERT INTO chatroom_user (user_id, chatroom_id)
VALUES  ((SELECT user_id FROM users WHERE user_login = 'login1' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom5' LIMIT 1)),
        ((SELECT user_id FROM users WHERE user_login = 'login2' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom4' LIMIT 1)),
        ((SELECT user_id FROM users WHERE user_login = 'login3' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom3' LIMIT 1)),
        ((SELECT user_id FROM users WHERE user_login = 'login4' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom2' LIMIT 1)),
        ((SELECT user_id FROM users WHERE user_login = 'login5' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom1' LIMIT 1));

INSERT INTO messages (author_id, chatroom_id, message, message_date)
VALUES  ((SELECT user_id FROM users WHERE user_login = 'login1' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom3' LIMIT 1), 'message one', NOW()),
        ((SELECT user_id FROM users WHERE user_login = 'login2' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom4' LIMIT 1), 'message two', CURRENT_TIMESTAMP),
        ((SELECT user_id FROM users WHERE user_login = 'login3' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom5' LIMIT 1), 'message three', '2021-06-19 18:04:58'),
        ((SELECT user_id FROM users WHERE user_login = 'login4' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom1' LIMIT 1), 'message four', '2021-08-24 12:30:14'),
        ((SELECT user_id FROM users WHERE user_login = 'login5' LIMIT 1),
            (SELECT chatroom_id FROM chatrooms WHERE chatroom_name = 'chatroom2' LIMIT 1), 'message five', '2022-01-21 00:17:45');





