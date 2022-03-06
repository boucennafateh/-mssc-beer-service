package org.fate7.msscbeerservice.repositories;

import org.fate7.msscbeerservice.Domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
