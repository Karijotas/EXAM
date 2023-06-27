package lt.techin.crud.api.dto.mapper;

import lt.techin.crud.api.dto.technic.TechnicDto;
import lt.techin.crud.api.dto.technic.TechnicEntityDto;
import lt.techin.crud.model.Technic;

public class TechnicMapper {




    public static TechnicDto toTechnicDto(Technic technic) {
        var technicDto = new TechnicDto();

        technicDto.setName(technic.getName());
        technicDto.setSurname(technic.getSurname());
        technicDto.setSpeciality(technic.getSpeciality());
        technicDto.setCity(technic.getCity());
        technicDto.setReview(technicDto.getReview());

        return technicDto;
    }

    public static TechnicEntityDto toTechnicEntityDto(Technic technic) {
        var technicDto = new TechnicEntityDto();

        technicDto.setId(technic.getId());
        technicDto.setName(technic.getName());
        technicDto.setSurname(technic.getSurname());
        technicDto.setSpeciality(technic.getSpeciality());
        technicDto.setCity(technic.getCity());
        technicDto.setReview(technic.getReview());

        return technicDto;
    }

    public static Technic toTechnic(TechnicEntityDto technicEntityDto) {
        var technic = new Technic();

        technic.setName(technicEntityDto.getName());
        technic.setSurname(technicEntityDto.getSurname());
        technic.setSpeciality(technicEntityDto.getSpeciality());
        technic.setCity(technicEntityDto.getCity());
        technic.setReview(technicEntityDto.getReview());

        return technic;
    }

    public static Technic toTechnic(TechnicDto technicDto) {
        var technic = new Technic();

        technic.setName(technicDto.getName());
        technic.setSurname(technicDto.getSurname());
        technic.setSpeciality(technicDto.getSpeciality());
        technic.setCity(technicDto.getCity());
        technic.setReview(technicDto.getReview());

        return technic;
    }
}
