# trading-app
The application will maintain the total quantity of a traded security held at any point of time. It will also update the position book which holds the AccountIdentifier and Securityidentifier and its series of events(BUY, SELL AND CANCEL) executed on the security or a traded asset for a given quantity. 

Assumptions
1. Quantity is always an Integer and will always hold a value
2. No complex validations are taken into account i.e. assuming all the input values will be non null and passed
   as per the design. 
3. BUY and SELL will have different eventIds as suggested in the example. 
4. CANCEL event applies to BUY as well as SELL trades 
5. eventId will be part of the payload and is not random generated.
