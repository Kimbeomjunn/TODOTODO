package org.zerock.b01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.service.BoardService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
public class BoardRestController {

    private final BoardService boardService;

    // 목록 조회
    @GetMapping("/list")
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO){

        return boardService.list(pageRequestDTO);
    }

    // 상세 조회
    @GetMapping("/{bno}")
    public BoardDTO read(@PathVariable(name = "bno") Long bno){

        return boardService.readOne(bno);
    }

    // 등록
    @PostMapping("/")
    public Map<String, Long> register(@Valid @RequestBody BoardDTO boardDTO){

        log.info(boardDTO);

        Long bno = boardService.register(boardDTO);

        return Map.of("bno", bno);
    }

    // 수정
    @PutMapping("/{bno}")
    public Map<String, String> modify(
            @PathVariable(name = "bno") Long bno,
            @RequestBody BoardDTO boardDTO){

        boardDTO.setBno(bno);

        boardService.modify(boardDTO);

        return Map.of("RESULT", "SUCCESS");
    }

    // 삭제
    @DeleteMapping("/{bno}")
    public Map<String, String> remove(@PathVariable(name = "bno") Long bno){

        boardService.remove(bno);

        return Map.of("RESULT", "SUCCESS");
    }
}