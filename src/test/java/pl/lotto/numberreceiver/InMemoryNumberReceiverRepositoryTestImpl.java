package pl.lotto.numberreceiver;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryNumberReceiverRepositoryTestImpl implements NumberReceiverRepository {

    Map<String, TicketEntity> tickets = new HashMap<>();

    @Override
    public TicketEntity save(TicketEntity ticket) {
        tickets.put(ticket.lotteryId(), ticket);
        return ticket;
    }

    @Override
    public <S extends TicketEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TicketEntity> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<TicketEntity> findAll() {
        return tickets.values()
                .stream()
                .toList();
    }

    @Override
    public List<TicketEntity> findAllById(Iterable<String> strings) {
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
    public void delete(TicketEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends TicketEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TicketEntity> findByDrawDate(LocalDateTime drawDate) {
        return tickets.values()
                .stream()
                .filter(t -> t.drawDate().equals(drawDate))
                .collect(Collectors.toList());
    }

    @Override
    public <S extends TicketEntity> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends TicketEntity> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends TicketEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TicketEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TicketEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TicketEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TicketEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TicketEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TicketEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<TicketEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TicketEntity> findAll(Pageable pageable) {
        return null;
    }
}
