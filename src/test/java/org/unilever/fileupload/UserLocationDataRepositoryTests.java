package org.unilever.fileupload;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unilever.fileupload.domain.UserLocationDataEntity;
import org.unilever.fileupload.repository.UserLocationDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserLocationDataRepositoryTests {
    @Autowired
    private UserLocationDataRepository userLocationDataRepository;

    @Test
    public void shouldBeAbleToSaveUserLocationData(){

        List<UserLocationDataEntity> userLocationDataEntities = new ArrayList<>();
        UserLocationDataEntity userLocationDataEntity=
                new UserLocationDataEntity(1l,"Hans","Amsterdam","Starbucks","Coffee");
        UserLocationDataEntity userLocationDataEntityAgain=
                new UserLocationDataEntity(2l,"Zimmer","Den Haag","Mac Donals","Restaurant");
        userLocationDataEntities.add(userLocationDataEntity);
        userLocationDataEntities.add(userLocationDataEntityAgain);

        userLocationDataRepository.saveAll(userLocationDataEntities);
        List<UserLocationDataEntity> userLocationDataEntityRetrieved = userLocationDataRepository.findAll();

        assertFalse(userLocationDataEntityRetrieved.isEmpty());
        assertEquals(userLocationDataEntities,userLocationDataEntityRetrieved);
    }
}
