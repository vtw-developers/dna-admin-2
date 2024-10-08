-- role

create table role
(
    id    serial
        constraint role_pk
            primary key,
    name  varchar(50),
    level integer
);


-- users

create table users
(
    id            varchar(50) not null
        constraint users_pk
            primary key,
    password      varchar(50) not null,
    name          varchar(50) not null,
    role_id       integer
        constraint users_role_id_fk
            references role,
    approval      varchar(1),
    approval_time timestamp
);


-- service_group

create table service_group
(
    id            serial
        constraint service_group_pk
            primary key,
    name          varchar(100),
    description   text,
    author_id     varchar(50) not null,
    modified_time timestamp   not null
);


-- api_info

create table api_info
(
    id                 serial
        constraint api_info_pk
            primary key,
    name               varchar(50) not null,
    http_method        varchar(10) not null,
    url                varchar(50) not null,
    author_id          varchar(50) not null,
    modified_time      timestamp   not null,
    enabled            char        not null,
    service_group_id   integer,
    flow_id            varchar(100),
    request_parameters text,
    response_elements  text,
    flow_meta_yaml     text
);


-- api_log

create table api_log
(
    message_id    varchar(100) not null
        constraint api_log_pk
            primary key,
    timestamp     timestamp    not null,
    result        char         not null,
    error_code    varchar(10),
    error_message varchar(1000),
    details       text,
    elapsed_time  integer      not null,
    flow_id       varchar(100) not null
);

create index api_log_timestamp_flow_id_result_index
    on api_log (timestamp desc, flow_id asc, result asc);


-- board_master

create table board_master
(
    id             integer     not null
        constraint board_master_pk
            primary key,
    name           varchar(50) not null,
    instruction    varchar(1000),
    file_attach_yn varchar(1)  not null,
    file_count     integer,
    file_max_size  varchar,
    reply_yn       varchar(1)  not null,
    use_yn         varchar(1)  not null,
    comment_yn     varchar(1)  not null,
    author_id      varchar(50) not null
        constraint board_master_users_id_fk
            references users,
    modified_time  timestamp   not null,
    pin_yn         varchar(1),
    popup_yn       varchar(1)
);

comment on table board_master is '게시판 관리';

comment on column board_master.name is '게시판이름';

comment on column board_master.instruction is '게시판설명';

comment on column board_master.file_attach_yn is '파일첨부여부';

comment on column board_master.file_count is '파일첨부최대갯수';

comment on column board_master.reply_yn is '답글 사용여부';

comment on column board_master.use_yn is '사용여부';

comment on column board_master.comment_yn is '댓글 사용여부';


-- board

create table board
(
    id               serial
        constraint board_pk
            primary key,
    board_master_id  integer      not null
        constraint board_board_master_id_fk
            references board_master,
    title            varchar(100) not null,
    content          text         not null,
    author_id        varchar(50)  not null,
    modified_time    timestamp    not null,
    board_no         integer      not null,
    parent_id        integer,
    view_count       integer,
    use_yn           varchar(1)   not null,
    pin_start_time   timestamp,
    pin_end_time     timestamp,
    popup_start_time timestamp,
    popup_end_time   timestamp,
    pin_yn           varchar(1)   not null,
    popup_yn         varchar(1)   not null
);

comment on table board is '게시글';

comment on column board.id is 'id';

comment on column board.board_master_id is '게시판 종류';

comment on column board.title is '제목';

comment on column board.content is '내용';

comment on column board.author_id is '작성자';

comment on column board.modified_time is '시간';

comment on column board.board_no is '게시글 번호';

comment on column board.parent_id is '답글 부모';

comment on column board.view_count is '조회수';

comment on column board.use_yn is '사용여부';


-- board_file

create table board_file
(
    id                 serial
        constraint board_file_pk
            primary key,
    board_id           integer
        constraint board_file_board_id_fk
            references board,
    original_file_name varchar(100)  not null,
    store_file_name    varchar(1000) not null,
    file_path          varchar(1000),
    author_id          varchar(50)   not null,
    modified_time      timestamp     not null,
    use_yn             varchar(1)    not null,
    size               bigint
);


-- comment

create table comment
(
    id            serial
        constraint comment_pk
            primary key,
    board_id      integer     not null
        constraint comment_board_id_fk
            references board,
    comment_no    integer,
    author_id     varchar(50) not null,
    modified_time timestamp   not null,
    content       varchar(1000),
    use_yn        varchar(1)  not null
);

comment on column comment.board_id is '게시글 번호';

comment on column comment.comment_no is '댓글번호';

comment on column comment.content is '댓글';

comment on column comment.use_yn is '사용여부';


-- page_info

create table page_info
(
    id            serial
        constraint page_info_pk
            primary key,
    name          varchar(50)  not null,
    path          varchar(100) not null,
    read_role_id  integer
        constraint page_info_role_id_fk
            references role,
    write_role_id integer
        constraint page_info_role_id_fk_2
            references role
);


-- menu

create table menu
(
    id            serial
        constraint menu_id_pk
            primary key,
    menu_id       varchar(100) not null,
    upper_menu_id varchar(100) not null,
    icon          varchar(50),
    name          varchar(50)  not null,
    type          varchar(10),
    page_info_id  integer
        constraint menu_page_info_id_fk
            references page_info,
    index         integer
);


-- flow_template

create table flow_template
(
    sid         serial
        constraint flow_template_pk
            primary key,
    template_id varchar(100) not null,
    name        varchar(100) not null,
    parameters  text,
    flow_type   varchar(10)
);



-- templated_flow

-- create table public.templated_flow
-- (
--     sid                serial
--         constraint templated_flow_pk
--             primary key,
--     flow_id            varchar(100) not null,
--     name               varchar(100) not null,
--     http_method        varchar(10)  not null,
--     url                varchar(100) not null,
--     request_parameters text,
--     response_body      text,
--     template_sid       integer
--         constraint templated_flow_flow_template_id_fk
--             references flow_template,
--     parameters         text
-- );

-- flow
create table flow
(
    sid                serial
        constraint flow_pk
            primary key,
    flow_type          varchar(10)  not null,
    flow_id            varchar(100) not null,
    name               varchar(100) not null,
    templated          boolean,
    template_sid       integer
        constraint flow_flow_template_id_fk
            references flow_template,
    template_id        varchar(100),
    parameters         text,
    http_method        varchar(10),
    url                varchar(100),
    request_parameters text,
    response_body      text
);


-- datasource

create table datasource
(
    id       serial
        constraint datasource_pk
            primary key,
    name     varchar(100),
    database varchar(100),
    url      varchar(100),
    username varchar(100),
    password varchar(100)
);

-- schedule
create table schedule
(
    id        serial
        constraint schedule_pk
            primary key,
    flow_sid  integer,
    cron_expr varchar
);






