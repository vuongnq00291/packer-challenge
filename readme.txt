Algorithm 
 step 1 : convert text file to a package object containing weight limit and list of items
 step 2 : for each package , creating all possible combinations of items
 step 3 : from a list of combinations , select a best match combination with weigh < weigh limit and highest cost
 step 4 : reduce the list and collect string
 
 Design 
 - Dependency injection with Factory : increase maintainability and extensibility , make object loose coupling to each other .
 - Singleton : help system manage object better and save the resource
 - map and reduce java 8 stream
 - message resource bundle supporting multiple language
 - Follow Domain Driven Design 