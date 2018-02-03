package by.matrosov.glickoratingsystem.model;


import javax.persistence.*;

@Entity
@Table(name = "teams_bo1")
public class Team1 {

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Team3 team3;

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

    public Team1() {
    }

    public Team1(double rating, double deviation, double volatility) {
        this.rating = rating;
        this.deviation = deviation;
        this.volatility = volatility;
    }

    public Team3 getTeamBo3() {
        return team3;
    }

    public void setTeamBo3(Team3 team3) {
        this.team3 = team3;
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

    @Override
    public String toString() {
        return "Team1{" +
                "team3=" + team3 +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", deviation=" + deviation +
                ", volatility=" + volatility +
                ", count=" + count +
                '}';
    }
}
