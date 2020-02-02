package org.unilever.fileupload;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unilever.fileupload.domain.UserLocationDataEntity;
import org.unilever.fileupload.repository.UserLocationDataRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserLocationDataRepositoryTests {
    @Autowired
    private UserLocationDataRepository userLocationDataRepository;

    @Test
    public void shouldBeAbleToSaveUserLocationData(){
        UserLocationDataEntity userLocationDataEntity=
                new UserLocationDataEntity(1l,"Hans","Amsterdam","Starbucks","Coffee");

        userLocationDataRepository.save(userLocationDataEntity);

        Optional<UserLocationDataEntity> userLocationDataEntityRetrieved = userLocationDataRepository.findById(1l);

        assertTrue(userLocationDataEntityRetrieved.isPresent());

        assertThat(userLocationDataEntity,is(userLocationDataEntityRetrieved.get()));
    }
}
