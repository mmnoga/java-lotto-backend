package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;

import java.util.List;
import java.util.stream.Collectors;

class ResultCheckerMapper {

    public static List<Ticket> mapTicketsDtoToTicket(List<TicketDto> ticketDto) {
        return ticketDto.stream()
                .map(ticket -> Ticket.builder()
                        .lotteryId(ticket.lotteryId())
                        .drawDate(ticket.drawDate())
                        .userNumbers(ticket.userNumbers())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<PlayerResultDto> mapPlayersResultToPlayersResultDto(List<PlayerResult> playerResult) {
        return playerResult.stream()
                .map(result -> PlayerResultDto.builder()
                        .ticketId(result.ticketId())
                        .drawDate(result.drawDate())
                        .playerNumbers(result.userNumbers())
                        .winningNumbers(result.winningNumbers())
                        .hitNumber(result.hitNumber())
                        .build())
                .collect(Collectors.toList());
    }

    public static PlayerResultDto mapPlayerResultToPlayersResultDto(PlayerResult playerResult) {
        return PlayerResultDto.builder()
                .ticketId(playerResult.ticketId())
                .playerNumbers(playerResult.userNumbers())
                .winningNumbers(playerResult.winningNumbers())
                .drawDate(playerResult.drawDate())
                .hitNumber(playerResult.hitNumber())
                .build();
    }

}
