    
insert into accounts(account_type, balance) values("Savings", 5000.0);
insert into accounts(account_type, balance) values("Checkings", 15000.0);

-- ====================================================
-- ====================================================


insert into transactions(amount, type, date, account_id) 
	values(5000, "Credit", now(), 1001);
    
insert into transactions(amount, type, date, account_id) 
	values(500, "Debiit", now(), 1001);
    
insert into transactions(amount, type, date, account_id) 
	values(5000, "Credit", now(), 1002);   
