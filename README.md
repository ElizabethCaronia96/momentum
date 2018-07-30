### The Momentum Team
---
* Elizabeth Caronia
* Karl Xu
* David Chen

### Project Pipeline (http://neueda.conygre.com/citi/nyc/project/index.html)
---
1. User makes a strategy request on frontend.
2. Frontend calls REST API and connects to service layer, and puts this strategy into the DB.
3. Service layer constantly checks stock prices (fake feed) to see if previous strategies need to be triggered.
4. When a strategy is triggered to either buy or sell, convert this strategy into Order object.
5. Send this Order object to the broker using OrderSender.
6. MomentumMsgReceiver (running in background) will receive any broker replies (linked by the ID).
7. We update the database entry in based on the reply (set it to either fulfilled, partially fulfilled, rejected, etc.)

### Proposed Database Schema
---

Table Name: orders

**id**|**stock**|**strategy**|**strategy\_id**|**datetime\_added**|**status**|**execution\_type**|**datetime\_executed**|**price**|**pl\_percentage**
:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:
1|APPL|2mv|1|07/29/2018 08:42:52.511|waiting|-|-|-| 
2|GOOG|bb|1|07/29/2018 09:23:45.122|waiting|-|-|-| 
3|NSC|2mv|2|07/30/2018 10:23:12:182|completed|sell|07/30/2018 14:23:12.444|-|10.15
4|MSFT|bb|2|07/30/2018 10:23:12.231|completed|buy|07/30/2018 15:23:51.621|-|-25.62
5|GOOG|2mv|3|07/30/2018 14:23:12.444|waiting|-|-|-| 
6|NSC|bb|3|07/30/2018 15:23:51.621|waiting|-|-|-| 

Table Name: 2mv

**strategy\_id**|**long\_average\_range\_minutes**|**short\_average\_range\_minutes**
:-----:|:-----:|:-----:
1|400|30
2|300|20
3|350|15

Table Name: bb

**strategy\_id**|**moving\_avg\_range\_days**|**std\_dev\_multiple\_trigger**
:-----:|:-----:|:-----:
1|20|2
2|12|2.2
3|15|1.65
