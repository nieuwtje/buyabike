package com.is.buyabike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is.buyabike.dto.IdeaDto;

public interface IdeaRepository  extends JpaRepository<IdeaDto, Integer> {

}
