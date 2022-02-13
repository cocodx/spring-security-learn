select * from t_permission where id in
(
	select permission_id from t_role_permission where role_id in
	(
		select role_id from t_user_role where user_id="1"
	)
)