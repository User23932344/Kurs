CREATE TABLE subscribtion( 
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    client_id INTEGER REFERENCES client(id),
    periodicals_id INTEGER REFERENCES periodicals(id),
    status BOOLEAN, 
    created datetime not NULL,
    price DOUBLE
    );

CREATE TABLE client ( 
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR, 
    email VARCHAR NOT NULL UNIQUE,
    address VARCHAR NOT NULL,
    phone INTEGER, 
    balance DOUBLE
    );

CREATE TABLE periodicals (
    id INTEGER PRIMARY KEY, 
    name VARCHAR NOT NULL,
    synopsis VARCHAR, 
    price DOUBLE, 
     publishing_house_id INTEGER REFERENCES publiching_house(id)
    );

CREATE TABLE publiching_house(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email VARCHAR NOT NULL UNIQUE,
    address VARCHAR NOT NULL,
     phone INTEGER
    );