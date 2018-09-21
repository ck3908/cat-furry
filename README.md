## Final Project Done for Java Developer Program at PerScholas By Andrew Gaw

### Case Study: Brokerage Trading Platform using Java/Spring MVC/Oracle DB/JDBC/JSP/HTML/CSS

This is a basic stock trading program where users can register, login and establish cash position then start trading. Login 
password uses Bcrypt encription for security.

The program will keep track of positions, showing history of trades for the user and display current outstanding
position. The program also keeps track of aggregate profit and loss and net cash available to trade. Cash balances can
be updated and changes if trades are made. There are also a number of business rules enforced:

- Only purchase the value of shares within cash account limit. 
- Cannot sell more shares than he or she owns. Cannot short sell.

The stock price currently is not live and are static prices updated via database from one of the major exchanges. A random number generator on stock prices is currently used for testing purposes as temporary pseudo plug for live feed onto the system. The stock symbols are all pre-validated as they are populated from a major stock exchange database.

[flow chart](https://github.com/ck3908/cat-furry/blob/master/brokerage_flow_chart.pdf)


### Prerequisites

- Apache Tomcat server 9
- Eclipse IDE
- Java 8 SDK * JRE
- Oracle 11g Database –must set on autoincrement of keys on the DB itself.
- JUnit 4 JUnit 4 tests (Unit testing for DAO methods)
- Test were done on all DAO classes.

### Known Bugs

- Registration page does not provide all the prompts to show why a user may have made a mistake in registration – 
although validation is fine.
- Not all figures show proper decimal places or dollar signs. 
- Logout credentials appear to not work properly at the moment, paging back after logout on some pages appear possible.
- Certain pages may retain information when it should be cleared.

### Uncompleted

- Live stock price feeds into the system.
- Live graphs of major indices.
- Stock news section.
- User update personal info.
- Admin user – to log in and make changes.
- Allow more user functionality, view historical trades by date, symbol, etc..

### Problems faced:

- The navigation bar buttons are disabled depending on the state the user is in. Ideally like user to have session attributes determine user accessibity to pages.
- Navigating and understanding so many ways Spring MVC can do things and stick to something workable.
- Passing information from controllers to controllers - what is best way to do it, currently using session setAttributes a lot.
- Customizing non-model based information to be passed to the view. I think Spring MVC puts lots of emphasis on models (Java classes)
as predominant way to pass info from controller to view. 
- Persisting information between controllers - which is best way? setAttributes?
- A controller accessing certain parts of the logic found in another controller without needed to exit first? since all controllers renders view?
- Getting around users having access to sensitive pages without logging in.
- Basic Login credentials did not anticipate the need to initialize additional user information to access other parts of the program if user choosers to skip to certain tabs after logging in.

### Built with

- Java 8 – Programming Language
- Spring MVC – Spring framework 5.0
- JSP – Java Server Pages
- JDBC – interact with Oracle DB
- HTML/CSS
- JUNIT 4 - Testing
- Maven – Dependency Management

### User Stories

- As a user, I can register an account to get access to the platform.
- As a user, I can confirm my registration detail before registering.
- As a user, I can create a cash position to start trading.
- As a user, I can log in with username and password.
- As a user, I can log out.
- As a user, I can access any stock symbol to potentially trade the ticker.
- As a user, I can check the price of the stock.
- As a user, I can place a buy or sell on the stock.
- As a user, I can input the number of shares to buy.
- As a user, I can confirm my trade before actual order is executed.
- As a user, I can check my trading history.
- As a user, I can check my current portfolio.
- As a user, I can check my cash balance.
- As a user, I can check my profit and loss figure.
- As a user, I can deduct cash from my account.
- As a user, I can add cash from my account.
- As a user, I can add cash from my account.
