package dao;

import com.lm.service.formation.dao.TimelineRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TimelineRepositoryTest {

  @Autowired
  private TimelineRepository repository;


  @Test
  @Transactional
  public void can_getAll() {
  }

}
