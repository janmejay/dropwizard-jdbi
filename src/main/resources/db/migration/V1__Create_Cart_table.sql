create table CARTS (
    ID int not null,
    EMAIL varchar(100) not null
);
create unique index CARTS_BY_EMAIL on CARTS (EMAIL);
