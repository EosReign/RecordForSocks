package skypro.recordforsocks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.recordforsocks.dto.RecordSocks;
import skypro.recordforsocks.service.SocksService;

@RestController
@RequestMapping("/api")
public class SocksController {

    private SocksService socksService;
    private SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/socks/income")
    public ResponseEntity<RecordSocks> incomeSocks(@RequestBody RecordSocks recordSocks) {
        return socksService.addSocks(recordSocks);
    }

    @PostMapping("/socks/outcome")
    public ResponseEntity<RecordSocks> outcomeSocks(@RequestBody RecordSocks recordSocks) {
        return socksService.outcomeSocks(recordSocks);
    }

    @GetMapping("/socks")
    public ResponseEntity<Integer> getSocksByColorAndCottonPart(@RequestParam("color") String color,
                                                     @RequestParam("operation") String operation,
                                                     @RequestParam("cottonPart") int cottonPart) {
        return socksService.getSocks(color, operation, cottonPart);
    }



}
