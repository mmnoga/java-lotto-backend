package pl.lotto.resultchecker;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryResultCheckerRepositoryImpl implements PlayerResultRepository {

    HashMap<String, PlayerResult> playersResultByTicketId = new HashMap<>();

    @Override
    public List<PlayerResult> saveAll(List<PlayerResult> playersResults) {
        for (PlayerResult playerResult : playersResults) {
            playersResultByTicketId.put(playerResult.ticketId(), playerResult);
        }
        return playersResults;
    }

    @Override
    public List<PlayerResult> findAll() {
        return playersResultByTicketId.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlayerResult> findById(String lotteryId) {
        return Optional.ofNullable(playersResultByTicketId.get(lotteryId));
    }

}
