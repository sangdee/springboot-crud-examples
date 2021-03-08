package com.gwell.crudtemplate.repository;

import com.gwell.crudtemplate.domain.Board;
import com.gwell.crudtemplate.repository.custom.CustomBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-07
 */

/**
 * Spring Data JPA BoardRepository
 * JpaRepository<T, ID>
 *     T : table
 *     ID : primary key type
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, CustomBoardRepository {


    /**
     * Receiving the id value and processing it removed
     *
     * @param id
     */
    @Modifying
    @Query("UPDATE Board b SET  b.removed = true WHERE b.id IN(:id)")
    void updateRemovedById(@Param("id") Long id);

}
