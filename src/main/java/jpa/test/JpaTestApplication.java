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

		Member member = new Member();

		//데이터 트랜잭션 시작
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
//			Member findMember = em.find(Member.class, 1L);

			List<Member> result = em.createQuery("select m from Member as m", Member.class)
					//페이징 하고 싶으면 아래 처럼 하면 됨
					.setFirstResult(1)
					.setMaxResults(8)
					.getResultList();

			for (Member a : result) {
				System.out.println("member = " + a);
			}

//			테이블 대상으로 쿼리를 짜는게 아니라 멤버 객체를 대상으로 쿼리를 짠다고 생각하면 됨

			//persist 안해도 업데이트 쿼리가 실행됨, jpa 통해서 객체를 가져오면 관리가 된다.
//			findMember.setName("HelloJPA");

			//삭제
//			em.remove(findMember)

			//저장
//			em.persist(member);
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
