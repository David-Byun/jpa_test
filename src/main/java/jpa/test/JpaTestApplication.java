package jpa.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class JpaTestApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		//데이터 트랜잭션 시작
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {

			Team team = new Team();
			team.setTeamName("teamA");
			em.persist(team);

			Member member = new Member();
			member.setName("kim");
			//연관관계의 주인에 값 설정
			member.setTeam(team); //중요!!

			/*
				내부 함수로 team 생성시에 멤버추가
				연관관계의 주인은 외래 키를 기준으로 정해야함(비즈니스 기준 X)
			 */
			em.persist(member);

			em.flush();
			em.clear();

			List<Member> findMembers = team.getMembers();
			for (Member a : findMembers) {
				System.out.println("member = " + a.getName());
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();

		SpringApplication.run(JpaTestApplication.class, args);
	}

}
