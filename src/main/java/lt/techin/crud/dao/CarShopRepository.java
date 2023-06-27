package lt.techin.crud.dao;

import lt.techin.crud.model.CarShop;
import lt.techin.crud.model.Technic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarShopRepository extends JpaRepository<CarShop, Long> {
}
