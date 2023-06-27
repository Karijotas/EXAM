package lt.techin.crud.service;

import lt.techin.crud.config.exception.CustomValidationException;
import lt.techin.crud.dao.CarShopRepository;
import lt.techin.crud.dao.TechnicRepository;
import lt.techin.crud.model.CarShop;
import lt.techin.crud.model.Technic;

public class FinderClass {

   public static CarShopRepository carShopRepository;
   private static TechnicRepository technicRepository;

    public static CarShop findCarShop(Long id) {
        return carShopRepository.findById(id)
                .orElseThrow(() -> new CustomValidationException("CarShop doesn't exist", "id", "CarShop not found", id.toString()));
    }

    public static Technic findTechnic(Long id) {
        return technicRepository.findById(id)
                .orElseThrow(() -> new CustomValidationException("Technic doesn't exist", "id", "Technic not found", id.toString()));
    }
}
