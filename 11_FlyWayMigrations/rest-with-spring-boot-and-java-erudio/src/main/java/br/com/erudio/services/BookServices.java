package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.repositories.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository repository;
	
	
	
}
