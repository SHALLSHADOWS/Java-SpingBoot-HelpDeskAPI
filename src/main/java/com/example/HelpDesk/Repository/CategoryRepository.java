package com.example.HelpDesk.Repository;

import com.example.HelpDesk.Model.Category;
import com.example.HelpDesk.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
