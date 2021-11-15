DROP TABLE categories IF EXISTS;
DROP TABLE customers IF EXISTS;
DROP TABLE employees IF EXISTS;
DROP TABLE orders IF EXISTS;
DROP TABLE order_details IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE shippers IF EXISTS;
DROP TABLE suppliers IF EXISTS;

CREATE TABLE categories (
    CategoryID int(11) NOT NULL,
    CategoryName varchar(255) DEFAULT NULL,
    Description varchar(255) DEFAULT NULL,
    PRIMARY KEY (CategoryID)
);

CREATE TABLE customers (
    CustomerID int(11) NOT NULL,
    CustomerName varchar(255) DEFAULT NULL,
    ContactName varchar(255) DEFAULT NULL,
    Address varchar(255) DEFAULT NULL,
    City varchar(255) DEFAULT NULL,
    PostalCode varchar(255) DEFAULT NULL,
    Country varchar(255) DEFAULT NULL,
    PRIMARY KEY (CustomerID)
);

CREATE TABLE employees (
    EmployeeID int(11) NOT NULL,
    LastName varchar(255) DEFAULT NULL,
    FirstName varchar(255) DEFAULT NULL,
    BirthDate date DEFAULT NULL,
    Photo varchar(255) DEFAULT NULL,
    Notes varchar(2000) DEFAULT NULL,
    PRIMARY KEY (EmployeeID)
);

CREATE TABLE shippers (
    ShipperID int(11) NOT NULL,
    ShipperName varchar(255) DEFAULT NULL,
    Phone varchar(255) DEFAULT NULL,
    PRIMARY KEY (ShipperID)
);

CREATE TABLE suppliers (
    SupplierID int(11) NOT NULL,
    SupplierName varchar(255) DEFAULT NULL,
    ContactName varchar(255) DEFAULT NULL,
    Address varchar(255) DEFAULT NULL,
    City varchar(255) DEFAULT NULL,
    PostalCode varchar(255) DEFAULT NULL,
    Country varchar(255) DEFAULT NULL,
    Phone varchar(255) DEFAULT NULL,
    PRIMARY KEY (SupplierID)
);

CREATE TABLE products (
    ProductID int(11) NOT NULL,
    ProductName varchar(255) DEFAULT NULL,
    SupplierID int(11) DEFAULT NULL,
    CategoryID int(11) DEFAULT NULL,
    Unit varchar(255) DEFAULT NULL,
    Price double DEFAULT NULL,
    PRIMARY KEY (ProductID),
    CONSTRAINT products_ibfk_1 FOREIGN KEY (CategoryID) REFERENCES categories (CategoryID),
    CONSTRAINT products_ibfk_2 FOREIGN KEY (SupplierID) REFERENCES suppliers (SupplierID)
);

CREATE TABLE orders (
    OrderID int(11) NOT NULL,
    CustomerID int(11) DEFAULT NULL,
    EmployeeID int(11) DEFAULT NULL,
    OrderDate date DEFAULT NULL,
    ShipperID int(11) DEFAULT NULL,
    PRIMARY KEY (OrderID),
    CONSTRAINT orders_ibfk_1 FOREIGN KEY (CustomerID) REFERENCES customers (CustomerID),
    CONSTRAINT orders_ibfk_2 FOREIGN KEY (EmployeeID) REFERENCES employees (EmployeeID),
    CONSTRAINT orders_ibfk_3 FOREIGN KEY (ShipperID) REFERENCES shippers (ShipperID)
);

CREATE TABLE order_details (
    OrderDetailID int(11) NOT NULL,
    OrderID int(11) DEFAULT NULL,
    ProductID int(11) DEFAULT NULL,
    Quantity int(11) DEFAULT NULL,
    PRIMARY KEY (OrderDetailID),
    CONSTRAINT order_details_ibfk_1 FOREIGN KEY (OrderID) REFERENCES orders (OrderID),
    CONSTRAINT order_details_ibfk_2 FOREIGN KEY (ProductID) REFERENCES products (ProductID)
);