package org.springframework.samples.petclinic.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.rest.dto.OwnerDto;

class OwnerMapperTests {

    private final OwnerMapper mapper = Mappers.getMapper(OwnerMapper.class);

    @Test
    void testToOwnerDtoAndBack() {
        Owner owner = new Owner();
        owner.setId(77);
        owner.setFirstName("Jane");
        owner.setLastName("Doe");
        owner.setAddress("1 Test St");
        owner.setCity("City");
        owner.setTelephone("123456789");

        OwnerDto dto = mapper.toOwnerDto(owner);
        assertNotNull(dto);
        assertEquals(77, dto.getId());
        assertEquals("Jane", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());

        Owner mapped = mapper.toOwner(dto);
        assertEquals(77, mapped.getId());
        assertEquals("Jane", mapped.getFirstName());
    }
}
