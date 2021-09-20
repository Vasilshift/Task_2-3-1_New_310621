insert ignore into users set id=1, username='Admin', password='$2y$12$GyJcjdfJMKjf7C7kTXyRouZmI3PgUTqO1b/dwmGGN6CpWy/fBNqJS';
insert ignore into roles set id=1, role='ROLE_USER';
insert ignore into roles set id=2, role='ROLE_ADMIN';
insert ignore into users_roles set user_id=1, role_id=2;