### The Momentum Team
---
* Elizabeth Caronia
* Karl Xu
* David Chen

### Project Pipeline
---
1. User makes a strategy request on frontend.
2. Frontend calls REST API and connects to service layer, and puts this strategy into the DB.
3. Service layer constantly checks stock prices (fake feed) to see if previous strategies need to be triggered.
4. When a strategy is triggered to either buy or sell, convert this strategy into Order object.
5. Send this Order object to the broker using OrderSender.
6. MomentumMsgReceiver (running in background) will receive any broker replies (linked by the ID).
7. We update the database entry in based on the reply (set it to either fulfilled, partially fulfilled, rejected, etc.)

