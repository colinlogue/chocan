-- ChocAn Database schema

CREATE TABLE service (
	ServiceCode char(6) PRIMARY KEY,
	Label varchar(25) UNIQUE, -- what the service shows up as on the terminal
	Fee int -- number of cents, so a $22.50 fee would be 2250
);

CREATE TABLE address (
	AddressID int PRIMARY KEY,
	Street varchar(25),
	City varchar(14),
	State char(2),
	ZIP char(5)
);

CREATE TABLE member (
	MemberID char(9) PRIMARY KEY,
	Name varchar(25),
	IsActive bool,
	AddressID int,
	FOREIGN KEY (AddressID) REFERENCES address (AddressID)
);

CREATE TABLE provider (
	ProviderID char(9) PRIMARY KEY,
	Name varchar(25),
	AddressID int,
	FOREIGN KEY (AddressID) REFERENCES address (AddressID)
);

CREATE TABLE session (
	SessionID int PRIMARY KEY,
	ProviderID char(9),
	MemberID char(9),
	ServiceCode char(6),
	ServiceDate date, -- when the service was performed
	LogTime datetime, -- when the data center received the info
	Comments varchar(100),
	FOREIGN KEY (ServiceCode) REFERENCES service (ServiceCode),
	FOREIGN KEY (ProviderID) REFERENCES provider (ProviderID),
	FOREIGN KEY (MemberID) REFERENCES member (MemberID)
);

