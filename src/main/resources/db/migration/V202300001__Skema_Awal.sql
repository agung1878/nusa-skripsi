create table course
(
    id                 varchar(36)  not null,
    created            timestamp(6) not null,
    created_by         varchar(255) not null,
    deleted            bigint,
    record_status      varchar(255) not null,
    updated            timestamp(6),
    updated_by         varchar(255),
    description        varchar(255) not null,
    location           varchar(150) not null,
    name               varchar(100) not null,
    proposer           varchar(100) not null,
    status             varchar(255),
    id_user_instructor varchar(36)  not null,
    id_syllabus        varchar(36),
    primary key (id)
);

create table detail_syllabus
(
    id                 varchar(36)  not null,
    created            timestamp(6) not null,
    created_by         varchar(255) not null,
    deleted            bigint,
    record_status      varchar(255) not null,
    updated            timestamp(6),
    updated_by         varchar(255),
    name               varchar(255),
    id_header_syllabus varchar(36),
    primary key (id)
);

create table header_syllabus
(
    id            varchar(36)  not null,
    created       timestamp(6) not null,
    created_by    varchar(255) not null,
    deleted       bigint,
    record_status varchar(255) not null,
    updated       timestamp(6),
    updated_by    varchar(255),
    name          varchar(255),
    id_syllabus   varchar(36),
    primary key (id)
);

create table invoice
(
    id             varchar(36)  not null,
    created        timestamp(6) not null,
    created_by     varchar(255) not null,
    deleted        bigint,
    record_status  varchar(255) not null,
    updated        timestamp(6),
    updated_by     varchar(255),
    invoice_number varchar(255),
    paid           boolean      not null,
    total          numeric(38, 2),
    id_course      varchar(36),
    primary key (id)
);

create table member_course
(
    id            varchar(36)  not null,
    created       timestamp(6) not null,
    created_by    varchar(255) not null,
    deleted       bigint,
    record_status varchar(255) not null,
    updated       timestamp(6),
    updated_by    varchar(255),
    name          varchar(255),
    position      varchar(255),
    id_course     varchar(36),
    primary key (id)
);

create table presence
(
    id                  varchar(36)  not null,
    created             timestamp(6) not null,
    created_by          varchar(255) not null,
    deleted             bigint,
    record_status       varchar(255) not null,
    updated             timestamp(6),
    updated_by          varchar(255),
    present             boolean      not null,
    id_course           varchar(36),
    id_user_participant varchar(36),
    id_schedule         varchar(36),
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

create table syllabus
(
    id            varchar(36)  not null,
    created       timestamp(6) not null,
    created_by    varchar(255) not null,
    deleted       bigint,
    record_status varchar(255) not null,
    updated       timestamp(6),
    updated_by    varchar(255),
    description   varchar(255),
    name          varchar(255),
    ppn           float(53)    not null,
    price         numeric(38, 2),
    primary key (id)
);
