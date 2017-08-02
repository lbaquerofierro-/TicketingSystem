# TicketingSystem
## Assumptions: 

Testing venue has 6 columns and 8 rows, the best row is the first one and the worst is the last one. 

The user has the possibility of confirming the reservation within 2 minutes, otherwise the "reservation" will be cancelled. The reservation will be cancelled as well if the user decides not to proceed. 

The main challenge with this problem was using Java, since it is not my main language. However I really enjoyed learning about Maven, JUnit, and Java itself. In fact I will start using the language for perosnal projects that involve OOP and need to be integrated with different technologies. 

Finally it is important to mention that the project could be improved by using a lock to lock the SeatHold object and avoid concurrency. Although the flag is effective with one thread it is not when more than one thread access the program. 
