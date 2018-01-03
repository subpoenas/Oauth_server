
-- USER
insert into TB_S_ACCOUNT(name, pwd)values ('alton', '$2a$10$UuWnM9YDG4/vPnJXLoaIee2WMU0DpHcTCvfDbGhNMMMSY.4sE17fC'); 
insert into TB_S_AUTHORITY (accountId, authority)values (1, 'ADMIN'); 
insert into TB_S_AUTHORITY (accountId, authority)values (1, 'USER'); 

-- CLIENT
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('alton', '', '$2a$10$UuWnM9YDG4/vPnJXLoaIee2WMU0DpHcTCvfDbGhNMMMSY.4sE17fC', 'read,write', 'password,client_credentials,implicit,refresh_token', NULL, "ADMIN, USER", 7200, 72000, NULL, 'true');