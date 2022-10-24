package com.importcsv.demo.repo;

import com.importcsv.demo.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvRepo extends JpaRepository<UploadFile, Long> {
}
