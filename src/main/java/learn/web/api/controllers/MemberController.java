package learn.web.api.controllers;

import learn.web.api.facades.MemberFacade;
import learn.web.api.facades.dtos.MemberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "true")
public class MemberController {

    @Autowired
    private MemberFacade memberFacade;

    @GetMapping("/members")
    public ResponseEntity<List<MemberData>> handleGetMembers() {
        return ResponseEntity.ok(memberFacade.getMemberData());
    }

    @GetMapping("/member")
    public ResponseEntity<MemberData> createMember() {
        return ResponseEntity.ok(memberFacade.createMember("Peter"));
    }
}
