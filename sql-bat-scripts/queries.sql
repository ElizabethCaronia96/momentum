use momentum;

select * from Strategies s left outer join strat_2ma tMA on s.type ='2ma' where s.status<>'finished' order by s.strategy_id desc;

select * from strategies s, strat_2ma tMA, strat_bb bb where s.status<> 'finished' and  s.type = '2ma' or s.type='bb'  order by s.strategy_id desc;


select * from strategies;
