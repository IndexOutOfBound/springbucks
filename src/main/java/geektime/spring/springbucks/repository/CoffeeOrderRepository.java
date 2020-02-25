package geektime.spring.springbucks.repository;

import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
