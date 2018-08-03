package com.momentum.rest.service;

import com.momentum.rest.dao.OrderRepository;
import com.momentum.rest.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orRp;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void doSomething() {
        System.out.println("didn't fail yet");
      /*  Order o = new Order();
        o.setStrategy_id(2);


        o.setCrossStartDate(Timestamp.valueOf("2018-08-01 09:35:00.0"));
        o.setCrossStartPrice(99.99);
        o.setCrossStartType("buy"); */

        List<Order> list = orRp.findAll();
        System.out.println("worked potentially, list strategy: " + list.get(0).getCrossoverStartType());
    }

    @Override
    public List<Order> getAllOrders() {
        return orRp.findAll();
    }

    public List<Order> getAllOpenPositions() {

        String q = "SELECT o FROM Order o WHERE o.crossoverEndType IS NULL ORDER BY o.orderId";
        Query query = em.createQuery(q);

        List orders = query.getResultList();
        return orders;
    }

    //to update a property of an order, please take the order, order.setProperty(new thing) , and then call updateOrder(order), which will save it
    @Override
    public void updateOrder(Order orderr) {

        orRp.save(orderr);
    }

    @Override
    public void addOrder(Order order) {
        orRp.save(order);
    }

    @Override
    public Order createOrderFromCross1(int strategy_id, String crossType, Timestamp crossTime1, Double crossPrice1) {
        Order x1 = new Order(strategy_id, crossType, crossTime1, crossPrice1);
        orRp.save(x1);
        return x1;
    }

 

    @Override
    public void updateOrderFromCross2(Order o, String crossType2, Timestamp crossTime2, Double crossPrice2, Double profit_loss) {
        o.setCrossoverEndType(crossType2);
        o.setCrossoverEndDatetime(crossTime2);
        o.setCrossoverEndPrice(crossPrice2);
        o.setProfitLoss(profit_loss);
        orRp.save(o);
    }





  /*  @Override
    public List<Order> insertStrategyId(Integer id) {
        return orRp.findOrdersByStrategyId(id);
    }

    @Override
    public List<Order> insertStartPrice(Double price){
        return orRp.saveOrdersByCrossoverStartPrice(price);
    }

    @Override
    public List<Order> insertStartDatetime(Timestamp date){
        return orRp.saveOrdersByCrossoverStartDatetime(date);
    }

    @Override
    public List<Order> insertStartType(String type){
        return orRp.saveOrdersByCrossoverStartType(type);
    }

    @Override
    public List<Order> insertEndPrice(Double price) {
        return orRp.saveOrdersByCrossoverEndPrice(price);
    }

    @Override
    public List<Order> insertEndDatetime(Timestamp date) {
        return orRp.saveOrdersByCrossoverEndDatetime(date);
    }

/*    @Override
    public List<Order> insertEndType(String type, Order o) {
        return orRp.saveOrdersByCrossoverEndType(type, o.getStrategyId());
    }
*/


}
