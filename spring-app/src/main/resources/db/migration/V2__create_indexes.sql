
CREATE INDEX user_mail_i
    ON public.users(email);

CREATE INDEX user_login_i
    ON public.users(login);