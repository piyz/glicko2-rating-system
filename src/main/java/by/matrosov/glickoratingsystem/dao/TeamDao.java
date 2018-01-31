package by.matrosov.glickoratingsystem.dao;

import by.matrosov.glickoratingsystem.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamDao extends JpaRepository<Team,Long>{
}
