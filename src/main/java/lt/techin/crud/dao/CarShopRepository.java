package lt.techin.crud.dao;

import lt.techin.crud.model.CarShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarShopRepository extends JpaRepository<CarShop, Long> {
}
