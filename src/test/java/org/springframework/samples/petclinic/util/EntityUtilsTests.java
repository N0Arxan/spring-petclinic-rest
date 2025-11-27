package org.springframework.samples.petclinic.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Owner;

class EntityUtilsTests {

    @Test
    void testGetByIdFound() {
        Collection<Owner> owners = new ArrayList<>();
        Owner owner = new Owner();
        owner.setId(10);
        owners.add(owner);

        Owner result = EntityUtils.getById(owners, Owner.class, 10);
        assertEquals(owner, result);
    }

    @Test
    void testGetByIdNotFound() {
        Collection<Owner> owners = new ArrayList<>();
        Owner owner = new Owner();
        owner.setId(10);
        owners.add(owner);

        assertThrows(ObjectRetrievalFailureException.class, () -> EntityUtils.getById(owners, Owner.class, 20));
    }
}
