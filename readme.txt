I Request filter: handling REST requests (RestAuthenticationFilter)
1. Login successful
1.1. User logins successfully;
1.2. User updates his personal data,
1.3. User performs CUD with his ideas;
1.4. User tries to block/unblock himself (fail);
1.5. User views personal info about another user and ideas of another user;
	1.5.1. getAll() method removed;
1.6. User tries to update personal info abut another user (fail);
1.7. User tries to perform CUD with ideas of another user (fail);
1.8. User tries to block/unblock another user (fail);
1.9. User tries to create/delete user (fail);

2. Login unsuccessful
2.1. User was not found;
2.2. Incorrect password;
2.3. User is being blocked after 3 attempts of
		inserting an incorrect password;
2.4. User is already blocked;

3. Login with admin priveleges
3.1. Admin views personal info and ideas of all users;
3.2. Admin updates personal info of all users;
3.3. Admin performs CRUD with ideas of all users;
3.4. Admin deletes users, but can not delete himself;
3.5. Admin blocks/unblocks users;
3.6. Admin creates users (email demo);

II Selenide + JMS
4. Sending an e-mail example;

III Primefaces
5. Managed beans + REST vs Services layer;
6. Client for GET requests;
7. Validation with JSF validators;
7. Sorting with JSF;