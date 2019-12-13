package com.onlineshopping.itemsservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {
	
	@Query("SELECT item FROM Item item WHERE item.item_name=(:itemNm)")
    Item findItemByName(@Param("itemNm") String itemNm);
}
