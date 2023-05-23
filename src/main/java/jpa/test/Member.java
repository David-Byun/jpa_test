package jpa.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//jpa 사용하는 애 인식
@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 10, name="USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
