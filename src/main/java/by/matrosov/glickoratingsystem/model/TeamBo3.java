package by.matrosov.glickoratingsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "teams_bo3")
public class TeamBo3 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "rating")
    private double rating;

    @Column(name = "deviation")
    private double deviation;

    @Column(name = "volatility")
    private double volatility;

    @Column(name = "count")
    private int count;

    public TeamBo3() {
    }

    public TeamBo3(double rating, double deviation, double volatility) {
        this.rating = rating;
        this.deviation = deviation;
        this.volatility = volatility;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
