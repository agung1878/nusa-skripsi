package com.mycourse.dao;

import com.mycourse.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceDao extends CrudRepository<Invoice, String> {
}
