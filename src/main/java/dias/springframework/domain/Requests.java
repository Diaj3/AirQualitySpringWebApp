package dias.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Requests {

    private @Id Integer id;
    private Integer count = 0;
    private Integer hit = 0;
    private Integer miss = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit() {
        this.hit += 1;
    }

    public Integer getMiss() {
        return miss;
    }

    public void setMiss() {
        this.miss += 1;
    }

    public void setCount() {
        this.count += 1;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "id=" + id +
                ", count=" + count +
                ", hit=" + hit +
                ", miss=" + miss +
                '}';
    }
}
