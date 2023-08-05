INSERT INTO public.s_permission (id, created, created_by, deleted, record_status, updated, updated_by, description, label, value)
VALUES ('p_member', '2023-05-13 14:25:50.193977000', 'admin', '0','ACTIVE', '2023-05-13 14:25:50.193977000','admin', 'Member Basic Permission', 'Member Basic Permission', 'MEMBER_BASIC');

INSERT INTO public.s_role (id, created, created_by, deleted, record_status, updated, updated_by, code, name)
VALUES ('r_member', '2023-05-13 14:25:50.193977000', 'admin', '0','ACTIVE', '2023-05-13 14:25:50.193977000','admin','MEMBER-BASIC', 'MEMBER');

INSERT INTO public.s_role_permission (id_role, id_permission) VALUES ('r_member', 'p_member');