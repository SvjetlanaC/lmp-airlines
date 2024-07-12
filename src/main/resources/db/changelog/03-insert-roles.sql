INSERT INTO public.role (role_id,name) VALUES (1,'UNKNOWN') ON CONFLICT (role_id) DO UPDATE SET name=EXCLUDED.name;
INSERT INTO public.role (role_id,name) VALUES (2,'CUSTOMER') ON CONFLICT (role_id) DO UPDATE SET name=EXCLUDED.name;
INSERT INTO public.role (role_id,name) VALUES (3,'SUPERVISOR') ON CONFLICT (role_id) DO UPDATE SET name=EXCLUDED.name;
INSERT INTO public.role (role_id,name) VALUES (4,'ADMINISTRATOR') ON CONFLICT (role_id) DO UPDATE SET name=EXCLUDED.name;