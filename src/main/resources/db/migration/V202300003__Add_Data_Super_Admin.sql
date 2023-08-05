INSERT INTO public.s_permission (id, created, created_by, deleted, record_status, updated, updated_by, description, label, value)
VALUES ('p_super_admin', '2023-05-13 14:25:50.193977000', 'admin', '0','ACTIVE', '2023-05-13 14:25:50.193977000','admin', 'Super Admin Permission', 'Super Admin Permission', 'SUPER_ADMIN');

INSERT INTO public.s_permission (id, created, created_by, deleted, record_status, updated, updated_by, description, label, value)
VALUES ('p_instructor', '2023-05-13 14:25:50.193977000', 'admin', '0','ACTIVE', '2023-05-13 14:25:50.193977000','admin', 'Instructor Permission', 'Instructor Permission', 'INSTRUCTOR');

INSERT INTO public.s_permission (id, created, created_by, deleted, record_status, updated, updated_by, description, label, value)
VALUES ('p_super_admin-001', '2023-05-13 14:25:50.193977000', 'admin', '0','ACTIVE', '2023-05-13 14:25:50.193977000','admin', 'Management Course', 'Management Course', 'MANAGEMENT_COURSE_VIEW');

INSERT INTO public.s_role (id, created, created_by, deleted, record_status, updated, updated_by, code, name)
VALUES ('r_super_admin', '2023-05-13 14:25:50.193977000', 'admin', '0','ACTIVE', '2023-05-13 14:25:50.193977000','admin','SUPER-ADMIN', 'SUPER ADMIN');

INSERT INTO public.s_role (id, created, created_by, deleted, record_status, updated, updated_by, code, name)
VALUES ('r_instructor', '2023-05-13 14:25:50.193977000', 'admin', '0','ACTIVE', '2023-05-13 14:25:50.193977000','admin','INSTRUCTOR', 'INSTRUCTOR');

INSERT INTO public.s_role_permission (id_role, id_permission) VALUES ('r_super_admin', 'p_super_admin');
INSERT INTO public.s_role_permission (id_role, id_permission) VALUES ('r_super_admin', 'p_super_admin-001');
INSERT INTO public.s_role_permission (id_role, id_permission) VALUES ('r_instructor', 'p_instructor');

INSERT INTO public.s_user(
    id, created, created_by, deleted, record_status, updated, updated_by, address, email, full_name, mobile_phone, username, id_s_role)
VALUES ('super_admin', '2023-05-13 14:25:50.193977000', 'admin', '0','ACTIVE', '2023-05-13 14:25:50.193977000','admin', 'unknown','superadmin@yopmail.com', 'Super Admin', '081312501996', 'superadmin', 'r_super_admin');

-- password : admin
INSERT INTO public.s_user_password(id_s_user, hashed_password)
VALUES ('super_admin', '$2a$10$aIzJfxlYmWwPjJKX2dSl1un0omQwVqKy7j5VMKBe2JzhC3X0yH0si');