package lt.techin.crud.dao;

import lt.techin.crud.model.CarShopTechnics;
import lt.techin.crud.model.Technic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CarShopTechnicsRepository extends JpaRepository<CarShopTechnics, Long> {
    Set<Technic> findByCarShop_Id(Long id);

}
