package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.AirplaneType;
import com.udea.vueloudea.model.Type;
import com.udea.vueloudea.service.AirplaneTypeService;
import com.udea.vueloudea.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AirplaneTypeMutationController {

    @Autowired
    private AirplaneTypeService airplaneTypeService;
    @Autowired
    private TypeService typeService;

    // Método para crear un nuevo tipo de avión
    @MutationMapping
    public AirplaneType createAirplaneType(
            @Argument Long typeId,  // Solo necesitas el ID del tipo existente
            @Argument int maxSeats,
            @Argument String seatsDistribution) {

        // Recuperar el objeto Type existente desde la base de datos
        Type existingType = typeService.getTypeById(typeId); // Asegúrate de tener este servicio

        // Crear una nueva instancia de AirplaneType
        AirplaneType airplaneType = new AirplaneType();
        airplaneType.setType(existingType); // Asigna el objeto Type recuperado

        airplaneType.setMaxSeats(maxSeats);
        airplaneType.setSeatsDistribution(seatsDistribution);

        return airplaneTypeService.createOrUpdateAirplaneType(airplaneType);
    }

    // Método para actualizar un tipo de avión existente
    @MutationMapping
    public AirplaneType updateAirplaneType(
            @Argument Long id,  // ID del tipo de avión a actualizar
            @Argument Long typeId,  // ID del tipo (referencia existente)
            @Argument int maxSeats,
            @Argument String seatsDistribution) {
        // Recupero el tipo de Avión
        Type existingType = typeService.getTypeById(typeId);

        // Buscar el tipo de avión existente por ID
        AirplaneType existingAirplaneType = airplaneTypeService.getAirplaneTypeById(id);

        // Actualizar los campos del tipo de avión
        existingAirplaneType.setType(existingType); // Recuperar el tipo existente si es necesario

        existingAirplaneType.setMaxSeats(maxSeats);
        existingAirplaneType.setSeatsDistribution(seatsDistribution);

        // Guardar el tipo de avión actualizado usando el servicio correspondiente
        return airplaneTypeService.createOrUpdateAirplaneType(existingAirplaneType);
    }

    // Método para eliminar un tipo de avión por su ID
    @MutationMapping
    public boolean deleteAirplaneType(@Argument Long id) {
        airplaneTypeService.deleteAirplaneType(id);
        return true; // Retorna true si la eliminación fue exitosa
    }
}