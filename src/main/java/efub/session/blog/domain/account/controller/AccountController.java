package efub.session.blog.domain.account.controller;

import efub.session.blog.domain.account.domain.Account;
import efub.session.blog.domain.account.dto.AccountResponseDto;
import efub.session.blog.domain.account.dto.SignUpRequestDto;
import efub.session.blog.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
        Long id = accountService.signUp(requestDto);
        Account findAccount = accountService.findAccountById(id);
        return AccountResponseDto.from(findAccount);
    }

    @GetMapping
    @ResponseStatus(value=HttpStatus.OK)
    public AccountResponseDto getAccount(@PathVariable Long accountId){
        Account findAccount = accountService.findAccountById(accountId);
        return AccountResponseDto.from(findAccount);
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable long accountId){
        accountService.delete(accountId);
        return "성공적으로 탈퇴가 완료되었습니다";
    }
}
