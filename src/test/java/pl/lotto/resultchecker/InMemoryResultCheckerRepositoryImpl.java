package pl.lotto.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryResultCheckerRepositoryImpl implements PlayerResultRepository {

    HashMap<String, PlayerResult> playersResultByTicketId = new HashMap<>();

    @Override
    public <S extends PlayerResult> S save(S entity) {
        return null;
    }

    @Override
    public <S extends PlayerResult> List<S> saveAll(Iterable<S> entities) {
        for(PlayerResult playerResult : entities) {
            playersResultByTicketId.put(playerResult.ticketId(), playerResult);
        }
        return (List<S>) entities;
    }

    @Override
    public List<PlayerResult> findAll() {
        return playersResultByTicketId.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerResult> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(PlayerResult entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends PlayerResult> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<PlayerResult> findById(String lotteryId) {
        return Optional.ofNullable(playersResultByTicketId.get(lotteryId));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public <S extends PlayerResult> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends PlayerResult> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends PlayerResult> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends PlayerResult> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends PlayerResult> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends PlayerResult> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends PlayerResult> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends PlayerResult> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends PlayerResult, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<PlayerResult> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<PlayerResult> findAll(Pageable pageable) {
        return null;
    }
}
