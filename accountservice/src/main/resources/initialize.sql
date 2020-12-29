CREATE KEYSPACE ticketsystem
    WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

use ticketsystem;

CREATE TABLE account(
                          id text PRIMARY KEY,
                          username text,
                          email text,
                          passwd text,
);

select * from account