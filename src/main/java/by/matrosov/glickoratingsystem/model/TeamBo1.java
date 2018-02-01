package by.matrosov.glickoratingsystem.model;


import javax.persistence.*;

@Entity
@Table(name = "teams")
public class TeamBo1 {

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private TeamBo3 teamBo3;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private double rating;

    @Column(name = "deviation")
    private double deviation;

    @Column(name = "volatility")
    private double volatility;

    @Column(name = "count")
    private int count;

    public TeamBo1() {
    }

    public TeamBo1(double rating, double deviation, double volatility) {
        this.rating = rating;
        this.deviation = deviation;
        this.volatility = volatility;
    }

    public TeamBo3 getTeamBo3() {
        return teamBo3;
    }

    public void setTeamBo3(TeamBo3 teamBo3) {
        this.teamBo3 = teamBo3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getDeviation() {
        return deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
