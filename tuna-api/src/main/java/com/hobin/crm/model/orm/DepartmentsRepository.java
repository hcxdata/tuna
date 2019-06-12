
package com.hobin.crm.model.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hobin.crm.model.Departments;

public interface DepartmentsRepository extends JpaRepository<Departments, Long> {

	Departments findOneByDepartmentName(String departmentName);
}
