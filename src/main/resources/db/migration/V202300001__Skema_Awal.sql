create table course
(
    id                 varchar(36)  not null,
    created            timestamp(6) not null,
    created_by         varchar(255) not null,
    deleted            bigint,
    record_status      varchar(255) not null,
    updated            timestamp(6),
    updated_by         varchar(255),
    course_status      varchar(255),
    description        varchar(255) not null,
    location           varchar(150) not null,
    name               varchar(100) not null,
    id_user_instructor varchar(36)  not null,
    primary key (id)
);

create table participant
(
    id            varchar(36)  not null,
    created       timestamp(6) not null,
    created_by    varchar(255) not null,
    deleted       bigint,
    record_status varchar(255) not null,
    updated       timestamp(6),
    updated_by    varchar(255),
    paid_payment  boolean      not null,
    id_course     varchar(36),
    id_user       varchar(36),
    primary key (id)
);

create table s_permission
(
    id            varchar(36)  not null,
    created       timestamp(6) not null,
    created_by    varchar(255) not null,
    deleted       bigint,
    record_status varchar(255) not null,
    updated       timestamp(6),
    updated_by    varchar(255),
    description   TEXT,
    label         varchar(100) not null,
    value         varchar(100) not null,
    primary key (id)
);

create table s_role
(
    id            varchar(36)  not null,
    created       timestamp(6) not null,
    created_by    varchar(255) not null,
    deleted       bigint,
    record_status varchar(255) not null,
    updated       timestamp(6),
    updated_by    varchar(255),
    code          varchar(100) not null,
    name          varchar(100) not null,
    primary key (id)
);

create table s_role_permission
(
    id_role       VARCHAR(36) not null,
    id_permission VARCHAR(36) not null
);

create table s_user
(
    id            varchar(36)  not null,
    created       timestamp(6) not null,
    created_by    varchar(255) not null,
    deleted       bigint,
    record_status varchar(255) not null,
    updated       timestamp(6),
    updated_by    varchar(255),
    address       varchar(255),
    email         varchar(255),
    full_name     varchar(255),
    mobile_phone  varchar(255),
    username      varchar(255),
    id_s_role     varchar(36)  not null,
    primary key (id)
);

create table s_user_password
(
    id_s_user       varchar(255) not null,
    hashed_password varchar(100) not null,
    primary key (id_s_user)
);

create table schedule
(
    id              varchar(36)  not null,
    created         timestamp(6) not null,
    created_by      varchar(255) not null,
    deleted         bigint,
    record_status   varchar(255) not null,
    updated         timestamp(6),
    updated_by      varchar(255),
    local_date_time timestamp(6),
    id_course       varchar(36),
    primary key (id)
);



alter table if exists s_permission
    add constraint unq_permission_value unique (value, deleted);



alter table if exists s_role
    add constraint unq_role_code unique (code, deleted);



alter table if exists s_user
    add constraint unq_user_username unique (username, deleted);

alter table if exists course
    add constraint FKivt2mof4sjob4f8574nxoxov3
    foreign key (id_user_instructor)
    references s_user;

alter table if exists participant
    add constraint FKjtptbkatpco5lj4bx5e2t3yt9
    foreign key (id_course)
    references course;

alter table if exists participant
    add constraint FKhapaf0ct4lfl0c9s3qbg2ryj9
    foreign key (id_user)
    references s_user;

alter table if exists s_role_permission
    add constraint FKctdvc3x67tpy90xh905iiautx
    foreign key (id_permission)
    references s_permission;

alter table if exists s_role_permission
    add constraint FK9cj7fdg3hf41td1n0vebmfa5x
    foreign key (id_role)
    references s_role;

alter table if exists s_user
    add constraint FK1kphta9tu0hpdoes1eq9dygdk
    foreign key (id_s_role)
    references s_role;

alter table if exists s_user_password
    add constraint FKjvlbjysmuhinjxdwp48je04wg
    foreign key (id_s_user)
    references s_user;

alter table if exists schedule
    add constraint FKrmvag562fnoc08wxh64bv58e5
    foreign key (id_course)
    references course;