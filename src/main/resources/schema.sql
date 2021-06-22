
CREATE TABLE positionbook(
account_id VARCHAR(250) NOT NULL ,
  security_id VARCHAR(250) NOT NULL ,
PRIMARY KEY(account_id,security_id));


CREATE TABLE event(
event_id BIGINT NOT NULL,
account_id VARCHAR(250) NOT NULL ,
security_id VARCHAR(250) NOT NULL,
trade_action VARCHAR(10) NOT NULL,
trade_quantity INT(5) NOT NULL,
FOREIGN KEY(account_id) REFERENCES positionbook(account_id),
FOREIGN KEY(security_id) REFERENCES positionbook(security_id),
PRIMARY KEY(event_id, account_id, security_id,trade_action));





