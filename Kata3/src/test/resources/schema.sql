create table if not exists bank_account (
  account_number varchar unique,
  balance double,
  open_timestamp timestamp
);

create table if not exists transaction (
  account_number varchar,
  timestamp timestamp,
  amount double,
  description varchar
)