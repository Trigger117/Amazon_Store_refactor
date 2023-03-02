package it.omicron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.omicron.entities.RuoloUtente;
import it.omicron.repository.RuoloRepository;

@Service("ruoloUtenteService")
public class RuoloUtenteServiceImpl implements RuoloUtenteService {

	@Autowired
	private RuoloRepository ruoloUtenteRepository;

	@Override
	public RuoloUtente findByRuolo(String ruolo) {
		return ruoloUtenteRepository.findByRuolo(ruolo);
	}

}
