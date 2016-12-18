create table CART_ITEMS (
    ID int not null AUTO_INCREMENT,
    CART_ID int not null,
    PRODUCT_NAME varchar(100) not null,
    QUANTITY int not null,
    PRIMARY KEY(ID)
);
create index ITEMS_BY_CART on CART_ITEMS (CART_ID);
