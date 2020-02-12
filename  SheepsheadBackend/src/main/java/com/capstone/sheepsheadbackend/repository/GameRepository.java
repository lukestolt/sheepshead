package com.capstone.sheepsheadbackend.repository;

import com.capstone.sheepsheadbackend.model.Game;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class GameRepository implements CommonRepository<Game>{

    private Map<String, Game> games = new HashMap<>();

    @Override
    public Game save(Game domain) {
        Game result = games.get(domain.getUID());
        if(result != null) {

        }
        return null;
    }

    @Override
    public Iterable<Game> save(Collection<Game> domains) {
        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(Game domain) {
        games.remove(domain.getUID());
    }

    @Override
    public Game findByid(String id) {
        return games.get(id);
    }

    @Override
    public Iterable<Game> findAll() {
        return games.entrySet().stream()
                .sorted(entryComparator)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private Comparator<Map.Entry<String, Game>> entryComparator =
            (Map.Entry<String, Game> o1, Map.Entry<String, Game> o2) -> {
                return o1.getValue().getCreated().compareTo(o2.getValue().getCreated());
            };
}
