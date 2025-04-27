package com.example.api.data;

import com.example.repository.BasePersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component //@Repository
public interface PersonRepository extends BasePersonRepository {
    //Not public methods for others to use

}
