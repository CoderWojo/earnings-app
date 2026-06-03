package org.wojo.earnings_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wojo.earnings_app.entity.Earning;


@Repository
public interface EarningsRepository extends JpaRepository<Earning, Integer> {
}
