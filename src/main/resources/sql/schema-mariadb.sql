DROP TABLE IF EXISTS TB_USER_C;

create table IF NOT EXISTS TB_USER_C
(
    user_id              int auto_increment comment '고유키'
        primary key,
    user_identity        varchar(45)                           not null comment '아이디',
    user_name            varchar(45)                           not null,
    user_password        varchar(200)                          not null comment '패스워드',
    user_email           varchar(45)                           null comment '이메일',
    user_affiliate       varchar(45)                           null comment '유저 소속',
    fk_sub_code_id       int                                   not null comment '유저 구분(타입) 코드',
    user_create_datetime timestamp default current_timestamp() not null comment '생성일',
    user_update_datetime timestamp default current_timestamp() not null comment '수정일',
    user_delete_datetime timestamp                             null comment '삭제일',
    constraint TB_USER_user_identity_uindex
        unique (user_identity)
)
    comment '유저 테이블';

