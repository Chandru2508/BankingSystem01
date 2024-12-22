create database Banking_System;

use Banking_System;
create table if not exists accounts(
	account_id bigint auto_increment primary key,
    account_type varchar(20) not null,
    balance double not null);

alter table accounts auto_increment = 1001;

SELECT * FROM banking_system.accounts;
 
-- ==============================================
-- ==============================================

use Banking_System;
create table if not exists transactions(
	transaction_id bigint auto_increment primary key,
    amount double not null,
    type varchar(20) not null,
    date timestamp not null,
    account_id bigint not null,
    foreign key(account_id) references accounts(account_id)
    );
    alter table transactions auto_increment = 45879001;

truncate table transactions; 

select * from transactions; 