-- role

INSERT INTO role (id, name, level) VALUES (1, '관리자', 1);
INSERT INTO role (id, name, level) VALUES (2, '일반사용자', 2);
INSERT INTO role (id, name, level) VALUES (3, '익명사용자', 3);


-- users

INSERT INTO users (id, password, name, role_id, approval, approval_time) VALUES ('level2', '1234', '김일반', 2, 'Y', '2024-08-20 16:38:03.864048');
INSERT INTO users (id, password, name, role_id, approval, approval_time) VALUES ('level1', '1234', '김관리', 1, 'Y', '2024-08-20 16:41:48.136824');


-- page_info

INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (1, 'API 관리', '/manage/api', 2, 1);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (2, '서비스 그룹 관리', '/manage/service-group', 2, 2);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (3, '스케줄 관리', '/manage/schedule', 2, 1);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (4, '대시보드', '/dashboard', 3, 3);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (5, 'API 통계', '/monitor/stats/api', 2, 1);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (6, 'API 로그', '/monitor/log/api', 2, 2);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (7, '사용자 관리', '/manage/user', 1, 1);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (8, '메뉴 관리', '/manage/menu', 1, 1);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (9, '페이지 관리', '/manage/page-info', 1, 1);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (10, '게시판 관리', '/manage/board', 1, 1);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (11, '자유게시판', '/boards/free', 3, 2);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (12, '공지사항', '/boards/notice', 3, 1);
INSERT INTO page_info (id, name, path, read_role_id, write_role_id) VALUES (13, 'QnA', '/boards/qna', 3, 2);


-- board_master

INSERT INTO board_master (id, name, instruction, file_attach_yn, file_count, file_max_size, reply_yn, use_yn, comment_yn, author_id, modified_time, pin_yn, popup_yn) VALUES (3, 'Q&A', 'Q&A 입니다', 'N', 0, '', 'Y', 'Y', 'N', 'level1', '2024-06-03 10:57:08.811058', null, null);
INSERT INTO board_master (id, name, instruction, file_attach_yn, file_count, file_max_size, reply_yn, use_yn, comment_yn, author_id, modified_time, pin_yn, popup_yn) VALUES (2, '자유게시판', '자유게시판입니다.', 'N', 0, '', 'N', 'Y', 'Y', 'level1', '2024-06-03 10:56:37.425349', null, null);
INSERT INTO board_master (id, name, instruction, file_attach_yn, file_count, file_max_size, reply_yn, use_yn, comment_yn, author_id, modified_time, pin_yn, popup_yn) VALUES (1, '공지사항', '공지사항 게시판입니다.', 'Y', null, null, 'N', 'Y', 'N', 'level1', '2024-06-19 14:21:26.870796', 'Y', 'Y');


-- menu

INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (4, 'e268be6c-2215-ea9a-a5b8-671aaadb8632', '9387d9a8-0103-dcc6-6d58-935d1acacc84', 'calendar', '스케줄 관리', 'page', 3, 3);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (9, '26185156-f26d-29ba-cfc5-e24d2aed2c88', '0', 'folder', '관리자', 'group', null, 8);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (6, '1561c359-3dd1-18bd-e546-6bc3e75fdc66', 'f59264be-4fdb-bef2-5cfb-634da224df62', 'dashboard', '대시보드', 'page', 4, 5);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (13, 'ad939f06-7d81-48ba-af6c-8f61ffbf1a48', '26185156-f26d-29ba-cfc5-e24d2aed2c88', 'kanban', '게시판 관리', 'page', 10, 12);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (17, '3fa8ef36-4890-c0c3-35d9-c5c7596473ba', 'c54c1abb-b64f-2b87-e821-4de7e2cfeaee', 'banking', 'Q&A', 'page', 13, 16);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (15, 'e03e1db4-7bce-c3eb-22f0-35d13b0ae553', 'c54c1abb-b64f-2b87-e821-4de7e2cfeaee', 'chat', '자유게시판', 'page', 11, 14);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (1, '9387d9a8-0103-dcc6-6d58-935d1acacc84', '0', 'folder', '연계관리', 'group', null, 0);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (5, 'f59264be-4fdb-bef2-5cfb-634da224df62', '0', 'external', '연계현황', 'group', null, 4);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (10, 'bf3bb526-74d9-49f5-9708-985de7c60630', '26185156-f26d-29ba-cfc5-e24d2aed2c88', 'user', '사용자 관리', 'page', 7, 9);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (14, 'c54c1abb-b64f-2b87-e821-4de7e2cfeaee', '0', 'folder', '게시판', 'group', null, 13);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (12, '76233941-e5e3-2c83-b230-5761c9f21c3e', '26185156-f26d-29ba-cfc5-e24d2aed2c88', 'job', '페이지 관리', 'page', 9, 11);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (3, '9a867293-11da-2d16-1cfd-8bc28e4e2418', '9387d9a8-0103-dcc6-6d58-935d1acacc84', 'menuItem', '서비스 그룹 관리', 'page', 2, 2);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (2, 'cb4c7359-b939-97b6-beac-7e6c700218c0', '9387d9a8-0103-dcc6-6d58-935d1acacc84', 'blog', 'API관리', 'page', 1, 1);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (16, '08233f3a-bcca-e136-aa37-2d49862efb3e', 'c54c1abb-b64f-2b87-e821-4de7e2cfeaee', 'calendar', '공지사항', 'page', 12, 15);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (11, 'aeeb4f6b-e7d3-a765-0172-c38eade3b148', '26185156-f26d-29ba-cfc5-e24d2aed2c88', 'menuItem', '메뉴관리', 'page', 8, 10);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (7, '8eed68be-80fd-67a1-a86d-534e332643a1', 'f59264be-4fdb-bef2-5cfb-634da224df62', 'file', 'API 로그', 'page', 6, 6);
INSERT INTO menu (id, menu_id, upper_menu_id, icon, name, type, page_info_id, index) VALUES (8, 'a3bdb548-cd1c-a0a9-6451-5a7e1e54cdba', 'f59264be-4fdb-bef2-5cfb-634da224df62', 'analytics', 'API 통계', 'page', 5, 7);


-- service_group

insert into service_group (id, name, description, author_id, modified_time) values (1, '그룹1', '', 'level1', '2024-08-30 14:54:31.870481');
insert into service_group (id, name, description, author_id, modified_time) values (2, '그룹2', '', 'level1', '2024-08-30 14:54:39.205728');
insert into service_group (id, name, description, author_id, modified_time) values (3, '그룹3', '', 'level1', '2024-08-30 14:54:44.996208');



-- api_info

insert into api_info (id, name, http_method, url, author_id, modified_time, enabled, service_group_id, flow_id, request_parameters, response_elements, flow_meta_yaml) values (1, '샘플 API 1', 'GET', '/sample/api-1', 'level1', '2024-08-30 14:57:03.973462', 'Y', 1, 'SampleApi1', '[{"name":"id","type":"String","length":0,"required":false,"description":null,"defaultValue":null},{"name":"","type":"String","length":0,"required":false,"description":null,"defaultValue":null}]', '[{"name":"name","type":"String","defaultValue":null},{"name":"","type":"String","defaultValue":null}]', '');
insert into api_info (id, name, http_method, url, author_id, modified_time, enabled, service_group_id, flow_id, request_parameters, response_elements, flow_meta_yaml) values (2, '샘플 API 2', 'GET', '/sample/api-2', 'level1', '2024-08-30 14:57:49.257127', 'Y', 1, 'SampleApi2', '[]', '[]', '');
insert into api_info (id, name, http_method, url, author_id, modified_time, enabled, service_group_id, flow_id, request_parameters, response_elements, flow_meta_yaml) values (3, '샘플 API 3', 'GET', '/sample/api-3', 'level1', '2024-08-30 14:58:10.805375', 'Y', 2, 'SampleApi3', '[]', '[]', '');
insert into api_info (id, name, http_method, url, author_id, modified_time, enabled, service_group_id, flow_id, request_parameters, response_elements, flow_meta_yaml) values (4, '샘플 API 4', 'GET', '/sample/api-4', 'level1', '2024-08-30 14:58:32.914946', 'Y', 2, 'SampleApi4', '[]', '[]', '');
insert into api_info (id, name, http_method, url, author_id, modified_time, enabled, service_group_id, flow_id, request_parameters, response_elements, flow_meta_yaml) values (5, '샘플 API 5', 'GET', '/sample/api-5', 'level1', '2024-08-30 14:58:50.309653', 'Y', 3, 'SampleApi5', '[]', '[]', '');
insert into api_info (id, name, http_method, url, author_id, modified_time, enabled, service_group_id, flow_id, request_parameters, response_elements, flow_meta_yaml) values (6, '샘플 API 6', 'GET', '/sample/api-6', 'level1', '2024-08-30 14:59:20.227761', 'Y', 3, 'SampleApi6', '[]', '[]', '');



-- api_log

insert into public.api_log (message_id, timestamp, result, error_code, error_message, details, elapsed_time, flow_id) values ('sample_message_id_1', '2024-08-30 15:12:53.000000', 'S', null, null, null, 123, 'SampleApi1');
insert into public.api_log (message_id, timestamp, result, error_code, error_message, details, elapsed_time, flow_id) values ('sample_message_id_2', '2023-08-30 15:12:53.000000', 'S', null, null, null, 123, 'SampleApi1');
insert into public.api_log (message_id, timestamp, result, error_code, error_message, details, elapsed_time, flow_id) values ('sample_message_id_3', '2023-11-30 15:12:53.000000', 'E', '샘플 에러', null, null, 123, 'SampleApi2');
insert into public.api_log (message_id, timestamp, result, error_code, error_message, details, elapsed_time, flow_id) values ('sample_message_id_4', '2024-08-30 15:12:53.000000', 'S', null, null, null, 123, 'SampleApi3');
insert into public.api_log (message_id, timestamp, result, error_code, error_message, details, elapsed_time, flow_id) values ('sample_message_id_5', '2021-10-30 15:12:53.000000', 'S', null, null, null, 123, 'SampleApi4');
insert into public.api_log (message_id, timestamp, result, error_code, error_message, details, elapsed_time, flow_id) values ('sample_message_id_6', '2024-08-30 15:12:53.000000', 'S', null, null, null, 123, 'SampleApi5');
insert into public.api_log (message_id, timestamp, result, error_code, error_message, details, elapsed_time, flow_id) values ('sample_message_id_7', '2024-08-30 15:12:53.000000', 'S', null, null, null, 123, 'SampleApi6');
insert into public.api_log (message_id, timestamp, result, error_code, error_message, details, elapsed_time, flow_id) values ('sample_message_id_8', '2024-08-30 15:12:53.000000', 'S', null, null, null, 123, 'SampleApi1');



--alter sequence

ALTER SEQUENCE role_id_seq restart with 4;
ALTER SEQUENCE page_info_id_seq restart with 14;
ALTER SEQUENCE menu_id_seq restart with 18;

ALTER SEQUENCE service_group_id_seq restart with 4;
ALTER SEQUENCE api_info_id_seq restart with 7;