package by.matrosov.glickoratingsystem.dao;

import by.matrosov.glickoratingsystem.model.TeamBo1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDao extends JpaRepository<TeamBo1,Long>{
    TeamBo1 getByName(String s);
}
