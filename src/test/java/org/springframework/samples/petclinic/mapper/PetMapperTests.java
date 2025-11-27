package org.springframework.samples.petclinic.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.rest.dto.PetDto;

class PetMapperTests {

    private final PetMapper mapper = Mappers.getMapper(PetMapper.class);

    @Test
    void testToPetDtoAndBackMapsOwnerId() {
        Pet pet = new Pet();
        pet.setId(100);
        pet.setName("Fido");
        PetType type = new PetType();
        type.setId(2);
        type.setName("dog");
        pet.setType(type);
        Owner owner = new Owner();
        owner.setId(5);
        pet.setOwner(owner);

        PetDto dto = mapper.toPetDto(pet);
        assertNotNull(dto);
        assertEquals(100, dto.getId());
        assertEquals("Fido", dto.getName());
        assertEquals(2, dto.getType().getId());
        assertEquals(5, dto.getOwnerId());

        Pet mappedBack = mapper.toPet(dto);
        assertNotNull(mappedBack.getOwner());
        assertEquals(5, mappedBack.getOwner().getId());
        assertEquals("Fido", mappedBack.getName());
    }
}
