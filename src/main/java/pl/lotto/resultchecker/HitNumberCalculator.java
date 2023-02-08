package pl.lotto.resultchecker;

import java.util.List;

class HitNumberCalculator {

    PlayerResult calculateHitNumber(Ticket ticket, List<Integer> winningNumbers) {
        int hitNumber = (int) ticket.userNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
        return PlayerResult.builder()
                .ticketId(ticket.lotteryId())
                .drawDate(ticket.drawDate())
                .userNumbers(ticket.userNumbers())
                .winningNumbers(winningNumbers)
                .hitNumber(hitNumber)
                .build();
    }

}
