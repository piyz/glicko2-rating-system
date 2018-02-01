package by.matrosov.glickoratingsystem.dao;

import by.matrosov.glickoratingsystem.model.Team1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDao extends JpaRepository<Team1,Long>{
    Team1 getByName(String s);
}
