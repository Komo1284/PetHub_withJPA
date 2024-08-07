package pethub.with_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
