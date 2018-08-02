package com.momentum.rest.service;


<<<<<<< HEAD
import com.momentum.rest.entities.BB;
import com.momentum.rest.entities.TwoMA;
=======
import com.momentum.rest.dao.BBRepository;
import com.momentum.rest.entities.BB;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 7a45dcaac37674aaf94cb5e167a6d30677053a7e
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BBService {

<<<<<<< HEAD
    public List<BB> getBBByStrategyID(int strategyId);
    public List<BB> getBBByMovingAvgRange(Integer movingAvgRange);
    public List<BB> getwoMAByStdDevMultiple(Double stdDevMultiple);
    public List<BB> getBBByPercentToExit(Double percentToExit);

=======
    public List<BB> getBBByStrategyId(int strategyId);
>>>>>>> 7a45dcaac37674aaf94cb5e167a6d30677053a7e
}
