use erp;

create table MEMBER_TBL (
MASTER_ID INT,
MEMBER_ID INT not null auto_increment,
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(40),
MOBILE_NUMBER VARCHAR(15),
EMAIL_ID VARCHAR(30),
MEMBER_TYPE VARCHAR(10),
CREATED_ON DATE,
EXPIRES_ON DATE,
MEMBER_STATUS VARCHAR(10),
MEMBER_PASSWORD CHAR(15),
MEMBER_PHOTO VARCHAR(30),
primary key (MEMBER_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update cascade
    on delete cascade
);

create table CATEGORY_TBL (
MASTER_ID INT,
CATEGORY_ID INT not null auto_increment,
CATEGORY_NAME VARCHAR(100),
CATEGORY_DESCRIPTION VARCHAR(255),
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (CATEGORY_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action
);

create table SERVICE_TBL (
MASTER_ID INT,
CATEGORY_ID INT,
SERVICE_ID INT not null auto_increment,
SERVICE_NAME VARCHAR(100),
SERVICE_DESCRIPTION VARCHAR(255),
SERVICE_COST VARCHAR(6),
SERVICE_DURATION VARCHAR(40),
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (SERVICE_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action,
    foreign key (CATEGORY_ID)
	references category_tbl (CATEGORY_ID)
    on update no action
    on delete no action
);

create table PLAN_TBL (
PLAN_ID INT not null auto_increment,
PLAN_NAME VARCHAR(10),
PLAN_MSG_COUNT INT,
primary key (PLAN_ID)
);

create table PRODUCT_TBL (
MASTER_ID INT,
PRODUCT_ID INT not null auto_increment,
PRODUCT_NAME VARCHAR(60),
PRODUCT_BRAND VARCHAR(80),
PRODUCT_BARCODE VARCHAR(255),
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (PRODUCT_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action
);

drop table SUPPLIER_TBL;

create table SUPPLIER_TBL (
MASTER_ID INT,
SUPPLIER_ID INT not null auto_increment,
SUPPLIER_NAME VARCHAR(255),
SUPPLIER_NUMBER VARCHAR(15),
SUPPLIER_PINCODE INT,
SUPPLIER_EMAIL VARCHAR(30),
SUPPLIER_GSTN_NO VARCHAR(40),
SUPPLIER_ADDRESS VARCHAR(255),
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (SUPPLIER_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action
);

drop table order_tbl;

drop table stock_tbl;

use erp;

create table ORDER_TBL (
MASTER_ID INT,
ORDER_ID INT not null auto_increment,
SUPPLIER_ID INT,
PRODUCT_ID INT,
QUANTITY INT,
COST_PRICE VARCHAR(10),
ORDER_TOTAL INT,
ORDER_DATE date,
ORDER_RECEIEVED_DATE date,
ORDER_STATUS VARCHAR(10),
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (ORDER_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action,
foreign key (SUPPLIER_ID)
	references supplier_tbl (SUPPLIER_ID)
    on update no action
    on delete no action,
foreign key (PRODUCT_ID)
	references product_tbl (PRODUCT_ID)
    on update no action
    on delete no action
);
 
create table APPOINTMENT_TBL (
MASTER_ID INT,
APPOINTMENT_ID INT not null auto_increment,
SERVICE_ID INT,
STAFF_ID INT,
CLIENT_ID INT,
APPOINTMENT_START_TIME VARCHAR(30),
APPOINTMENT_INVOICE VARCHAR(255),
APPOINTMENT_NOTES VARCHAR(255),
APPOINTMENT_DATE date,
APPOINTMENT_STATUS VARCHAR(10),
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (APPOINTMENT_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action,
foreign key (SERVICE_ID)
	references service_tbl (SERVICE_ID)
    on update no action
    on delete no action,
foreign key (STAFF_ID)
	references staff_tbl (STAFF_ID)
    on update no action
    on delete cascade,
foreign key (CLIENT_ID)
	references client_tbl (CLIENT_ID)
    on update no action
    on delete no action
);


create table STOCK_TBL (
MASTER_ID INT,
ORDER_ID INT,
STOCK_ID INT not null auto_increment,
PRODUCT_ID INT,
QUANTITY INT,
SUPPLIER_ID INT,
STOCK_DESCRIPTION VARCHAR(255),
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (STOCK_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action,
foreign key (ORDER_ID)
	references order_tbl (ORDER_ID)
    on update no action
    on delete cascade,
foreign key (PRODUCT_ID)
	references product_tbl (PRODUCT_ID)
    on update no action
    on delete no action,
foreign key (SUPPLIER_ID)
	references supplier_tbl (SUPPLIER_ID)
    on update no action
    on delete no action
);

create table SALES_TBL (
MASTER_ID INT,
SALES_ID INT not null auto_increment,
STOCK_ID INT,
PRODUCT_ID INT,
SALE_SELLING_PRICE FLOAT,
SALE_COST_PRICE FLOAT,
SALE_QUANTITY INT,
SALE_NOTES VARCHAR(255),
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (SALES_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action,
foreign key (STOCK_ID)
	references stock_tbl (STOCK_ID)
    on update no action
    on delete no action,
foreign key (PRODUCT_ID)
	references product_tbl (PRODUCT_ID)
    on update no action
    on delete no action
);


create table CLIENT_TBL (
MASTER_ID INT,
CLIENT_ID INT not null auto_increment,
FULL_NAME VARCHAR(60),
MOBILE_NUMBER VARCHAR(15),
CLIENT_ADDRESS VARCHAR(255),
GENDER VARCHAR(10),
BIRTHDAY DATE,
EMAIL_ID VARCHAR(30),
IS_MEMBER VARCHAR(3),
CLIENT_START_DATE DATE,
CLIENT_END_DATE DATE,
REVENUE_GENERATED INT,
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (CLIENT_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action
);

create table APPOINTMENT_TBL (
MASTER_ID INT,
APPOINTMENT_ID INT not null auto_increment,
FULL_NAME VARCHAR(60),
MOBILE_NUMBER VARCHAR(15),
CLIENT_ADDRESS VARCHAR(255),
GENDER VARCHAR(10),
BIRTHDAY DATE,
EMAIL_ID VARCHAR(30),
IS_MEMBER VARCHAR(3),
CLIENT_START_DATE DATE,
CLIENT_END_DATE DATE,
REVENUE_GENERATED INT,
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (CLIENT_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action
);

use erp;
ALTER TABLE erp.client_tbl ADD COLUMN CLIENT_PINCODE INT;

create table STAFF_TBL (
MASTER_ID INT,
STAFF_ID INT not null auto_increment,
FULL_NAME VARCHAR(60),
MOBILE_NUMBER VARCHAR(15),
STAFF_ADDRESS VARCHAR(255),
GENDER ENUM('Male','Female'),
STAFF_WORKING_DAYS ENUM('Mon','Tue','Wed','Thr','Fri','Sat','Sun'),
STAFF_WORKING_IN_TIME time,
STAFF_WORKING_OUT_TIME time, 
EMAIL_ID VARCHAR(30),
STAFF_DESIGNATION VARCHAR(30),
STAFF_START_DATE DATE,
STAFF_END_DATE DATE,
REVENUE_GENERATED INT,
ROW_INSERTED_DATE TIMESTAMP not null default now(),
primary key (STAFF_ID),
foreign key (MASTER_ID)
	references master_tbl (MASTER_ID)
    on update no action
    on delete no action
);

CREATE TABLE SPRING_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL,
	SESSION_ID CHAR(36) NOT NULL,
	CREATION_TIME BIGINT NOT NULL,
	LAST_ACCESS_TIME BIGINT NOT NULL,
	MAX_INACTIVE_INTERVAL INT NOT NULL,
	EXPIRY_TIME BIGINT NOT NULL,
	PRINCIPAL_NAME VARCHAR(100),
	CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL,
	ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
	ATTRIBUTE_BYTES BLOB NOT NULL,
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

DROP TRIGGER INSERT_AFTER_ORDER;

DROP TRIGGER DELETE_AFTER_ORDER;

DELIMITER $$

CREATE TRIGGER INSERT_AFTER_ORDER 
	AFTER UPDATE ON erp.order_tbl  FOR EACH ROW
	BEGIN
		IF NEW.ORDER_STATUS = 'Received'
        THEN
			INSERT INTO erp.stock_tbl (MASTER_ID,ORDER_ID,PRODUCT_ID,QUANTITY,SUPPLIER_ID)
			VALUES (NEW.MASTER_ID,NEW.ORDER_ID,NEW.PRODUCT_ID,NEW.QUANTITY,NEW.SUPPLIER_ID);
		END IF;
END;
$$


DELIMITER $$
CREATE TRIGGER DELETE_AFTER_ORDER
AFTER DELETE 
	ON erp.order_tbl FOR EACH ROW 
BEGIN
	DELETE FROM erp.stock_tbl where ORDER_ID = OLD.ORDER_ID;
END;
$$    
show triggers