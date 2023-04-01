package skypro.recordforsocks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.recordforsocks.dto.RecordSocksBatch;
import skypro.recordforsocks.service.SocksService;

@RestController
@RequestMapping("/api")
public class SocksController {

    private SocksService socksService;
    private SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/socks/income")
    public ResponseEntity<RecordSocksBatch> incomeSocks(@RequestBody RecordSocksBatch recordSocksBatch) {
        return socksService.addSocksBatch(recordSocksBatch);
    }

    @PostMapping("/socks/outcome")
    public ResponseEntity<RecordSocksBatch> outcomeSocks(@RequestBody RecordSocksBatch recordSocksBatch) {
        return socksService.outcomeSocks(recordSocksBatch);
    }

    @GetMapping("/socks")
    public ResponseEntity<Integer> getSocksByColorAndCottonPart(@RequestParam("color") String color,
                                                     @RequestParam("operation") String operation,
                                                     @RequestParam("cottonPart") int cottonPart) {
        return socksService.getSocks(color, operation, cottonPart);
    }



}
