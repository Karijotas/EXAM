package lt.techin.crud.api.dto.mapper;

import lt.techin.crud.api.dto.carShop.CarShopDto;
import lt.techin.crud.api.dto.carShop.CarShopEntityDto;
import lt.techin.crud.model.CarShop;

public class CarShopMapper {

    public static CarShopEntityDto toCarShopEntityDto(CarShop carShop) {
        var carshopEntity = new CarShopEntityDto();

        carshopEntity.setId(carShop.getId());
        carshopEntity.setName(carShop.getName());
        carshopEntity.setAdress(carShop.getAdress());
        carshopEntity.setOwner(carShop.getOwner());
        carshopEntity.setTechnics(carShop.getTechnics());

        return carshopEntity;

    }

    public static CarShop toCarShop(CarShopEntityDto carShopEntityDto) {
        var carShop = new CarShop();

        carShop.setName(carShopEntityDto.getName());
        carShop.setAdress(carShopEntityDto.getAdress());
        carShop.setOwner(carShopEntityDto.getOwner());
        carShop.setTechnics(carShopEntityDto.getTechnics());

        return carShop;
    }

    public static CarShop toCarShop(CarShopDto carShopDto) {
        var carShop = new CarShop();

        carShop.setName(carShopDto.getName());
        carShop.setAdress(carShopDto.getAdress());
        carShop.setOwner(carShopDto.getOwner());
        carShop.setTechnics(carShopDto.getTechnics());

        return carShop;
    }

}
